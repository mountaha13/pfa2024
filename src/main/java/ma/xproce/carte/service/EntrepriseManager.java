package ma.xproce.carte.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import ma.xproce.carte.dao.entities.Entreprise;

import java.util.List;

    @Component
    public interface EntrepriseManager {
        public Entreprise addEntreprise(Entreprise entreprise);
        public List<Entreprise> getAllEntreprises();


        List<Entreprise> searchEntreprisesByNom(String keyword);
        //public Page<Entreprise> findBySecteur(String secteur, PageRequest pageRequest);

        public List<Entreprise> findBySecteur(String secteurNom, Pageable pageable);


        public Page<Entreprise> getAllEntreprises(int page, int taille);
      //  public Page<Entreprise> searchEntreprises(String keyword, int page, int taille);
      //  public List<Entreprise> getByKeyword(String keyword);
        public Entreprise getEntrepriseById(Integer id);
        public Entreprise updateEntreprise(Entreprise entreprise);
        public boolean deleteEntreprise(Integer id);
        public Page<Entreprise> findAll(Pageable pageable);
    }


