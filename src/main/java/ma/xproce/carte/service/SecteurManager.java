package ma.xproce.carte.service;

import ma.xproce.carte.dao.entities.Secteur;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface SecteurManager {


        Secteur addSecteur(Secteur secteur);
        Page<Secteur> getAllSecteurs(int page, int taille);
        Page<Secteur> searchSecteurs(String keyword, int page, int taille);
        List<Secteur> getByKeyword(String keyword);
        Secteur getSecteurById(Integer id);
        Secteur updateSecteur(Secteur secteur);
        boolean deleteSecteur(Integer id);
        Page<Secteur> findAll(Pageable pageable);
        List<Secteur> getAllSecteurs();  // Nouvelle méthode
    }


