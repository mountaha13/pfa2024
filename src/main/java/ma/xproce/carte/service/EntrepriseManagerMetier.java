package ma.xproce.carte.service;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ma.xproce.carte.dao.entities.Entreprise;
import ma.xproce.carte.dao.repositories.EntrepriseRepository;

import java.util.List;

    @Service
    public class EntrepriseManagerMetier implements EntrepriseManager {

        @Autowired
        private EntrepriseRepository entrepriseRepository;

        @Override
        public Entreprise addEntreprise(Entreprise entreprise) {
            return entrepriseRepository.save(entreprise);
        }

        @Override
        public Page<Entreprise> getAllEntreprises(int page, int taille) {
            return entrepriseRepository.findAll(PageRequest.of(page, taille));
        }

       /* @Override
        public Page<Entreprise> searchEntreprises(String keyword, int page, int taille) {
            return entrepriseRepository.findByNomContaining(keyword, PageRequest.of(page, taille));
        }*/

       /* @Override
        public List<Entreprise> getByKeyword(String keyword) {
            return entrepriseRepository.findByNomContaining(keyword);
        }*/

        @Override
        public Entreprise getEntrepriseById(Integer id) {
            return entrepriseRepository.findById(id).get();
        }

        @Override
        public Entreprise updateEntreprise(Entreprise entreprise) {
            return entrepriseRepository.save(entreprise);
        }
       @Override
       public Page<Entreprise> findAll(Pageable pageable) {
            return entrepriseRepository.findAll(pageable);
        }
        @Override
        public boolean deleteEntreprise(Integer id) {
            try {
                entrepriseRepository.deleteById(id);
                return true;
            } catch (Exception exception) {
                return false;
            }
        }

        @Override
        public List<Entreprise> findBySecteur(String secteurNom, Pageable pageable) {
            return entrepriseRepository.findBySecteurNom(secteurNom);
        }
        /*@Override
        public Page<Entreprise> findBySecteur(String secteur, PageRequest pageRequest) {
            return entrepriseRepository.findBySecteur(secteur, pageRequest);
        }*/
        @Override
        public List<Entreprise> searchEntreprisesByNom(String keyword) {
            return entrepriseRepository.findByNomContainingIgnoreCase(keyword);
        }


@Override
        public List<Entreprise> getAllEntreprises() {
            return entrepriseRepository.findAll();
        }
    }





