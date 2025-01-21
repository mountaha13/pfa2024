package ma.xproce.carte.service;

import ma.xproce.carte.dao.entities.Carte;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import ma.xproce.carte.dao.entities.Carte;

import java.util.List;




    @Component
    public interface CarteManager {
        Carte addCarte(Carte carte);
        Page<Carte> getAllCartes(int page, int taille);
        List<Carte> getAllCartes();

        List<Carte> searchCartesByAdresse(String keyword);




        //Page<Carte> searchCartes(String keyword, int page, int taille);
        //List<Carte> getByKeyword(String keyword);
        Carte getCarteById(Integer id);
        Carte updateCarte(Carte carte);
        boolean deleteCarte(Integer id);
        Page<Carte> findAll(Pageable pageable);
    }


