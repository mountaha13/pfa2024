package ma.xproce.carte.service;
import lombok.AllArgsConstructor;
import ma.xproce.carte.dao.entities.Secteur;
import ma.xproce.carte.dao.repositories.SecteurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ma.xproce.carte.dao.entities.Entreprise;
import ma.xproce.carte.dao.repositories.EntrepriseRepository;

import java.util.List;

@Service
public class SecteurManagerMetier implements SecteurManager {

    @Autowired
    private SecteurRepository secteurRepository;

    @Override
    public Secteur addSecteur(Secteur secteur) {
        return secteurRepository.save(secteur);
    }

    @Override
    public Page<Secteur> getAllSecteurs(int page, int taille) {
        return secteurRepository.findAll(PageRequest.of(page, taille));
    }

    @Override
    public Page<Secteur> searchSecteurs(String keyword, int page, int taille) {
        return secteurRepository.findByNomContaining(keyword, PageRequest.of(page, taille));
    }

    @Override
    public List<Secteur> getByKeyword(String keyword) {
        return secteurRepository.findByNomContaining(keyword);
    }

    @Override
    public Secteur getSecteurById(Integer id) {
        return secteurRepository.findById(id).orElse(null);
    }

    @Override
    public Secteur updateSecteur(Secteur secteur) {
        return secteurRepository.save(secteur);
    }

    @Override
    public boolean deleteSecteur(Integer id) {
        try {
            secteurRepository.deleteById(id);
            return true;
        } catch (Exception exception) {
            return false;
        }
    }

    @Override
    public Page<Secteur> findAll(Pageable pageable) {
        return secteurRepository.findAll(pageable);
    }

    @Override
    public List<Secteur> getAllSecteurs() {
        return secteurRepository.findAll();
    }
}