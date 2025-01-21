package ma.xproce.carte.service;

import ma.xproce.carte.dao.entities.Carte;
import ma.xproce.carte.dao.repositories.CarteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
    public class CarteManagerMetier implements CarteManager {

        @Autowired
        private CarteRepository carteRepository;

        @Override
        public Carte addCarte(Carte carte) {
            return carteRepository.save(carte);
        }

        @Override
        public Page<Carte> getAllCartes(int page, int taille) {
            return carteRepository.findAll(PageRequest.of(page, taille));
        }
    @Override
    public List<Carte> searchCartesByAdresse(String keyword) {
        return carteRepository.findByAdresseContainingIgnoreCase(keyword);
    }


    @Override
    public List<Carte> getAllCartes() {
        return carteRepository.findAll();}

       /* @Override
        public Page<Carte> searchCartes(String keyword, int page, int taille) {
            return carteRepository.findByNomContaining(keyword, PageRequest.of(page, taille));
        } */

        /* @Override
        public List<Carte> getByKeyword(String keyword) {
            return carteRepository.findByNomContaining(keyword);
        }*/

        @Override
        public Carte getCarteById(Integer id) {
            return carteRepository.findById(id).orElse(null);
        }

        @Override
        public Carte updateCarte(Carte carte) {
            return carteRepository.save(carte);
        }

        @Override
        public boolean deleteCarte(Integer id) {
            try {
                carteRepository.deleteById(id);
                return true;
            } catch (Exception exception) {
                return false;
            }
        }

        @Override
        public Page<Carte> findAll(Pageable pageable) {
            return carteRepository.findAll(pageable);
        }
    }


