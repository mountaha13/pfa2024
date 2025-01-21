package ma.xproce.carte.dao.repositories;


import ma.xproce.carte.dao.entities.Carte;
import ma.xproce.carte.dao.entities.Entreprise;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CarteRepository extends JpaRepository<Carte,Integer> {
    //Page<Carte> findByNomContaining(String nom, Pageable pageable);
    //List<Carte> findByNomContaining(String adresse);
    List<Carte> findByAdresseContainingIgnoreCase(String adresse);
}
