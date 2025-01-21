package ma.xproce.carte.dao.repositories;

import ma.xproce.carte.dao.entities.Entreprise;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;


import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


@Transactional
public interface EntrepriseRepository extends JpaRepository<Entreprise,Integer> {
    Page<Entreprise> findByNomContaining(String nom, Pageable pageable);
    List<Entreprise> findByNomContaining(String nom);
    List<Entreprise> findByNomContainingIgnoreCase(String nom);
   // Page<Entreprise> findBySecteur(String secteur, PageRequest pageRequest);
    List<Entreprise> findBySecteurNom(String secteurNom);
}







