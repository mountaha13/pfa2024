package ma.xproce.carte.dao.repositories;


import ma.xproce.carte.dao.entities.Entreprise;
import ma.xproce.carte.dao.entities.Secteur;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SecteurRepository extends JpaRepository<Secteur,Integer> {

    Page<Secteur> findByNomContaining(String nom, Pageable pageable);
    List<Secteur> findByNomContaining(String nom);
}
