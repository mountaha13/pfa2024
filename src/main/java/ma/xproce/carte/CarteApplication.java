package ma.xproce.carte;

import ma.xproce.carte.dao.entities.*;

import ma.xproce.carte.dao.repositories.*;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;

import java.util.ArrayList;
import java.util.List;


@SpringBootApplication
public class CarteApplication {
    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private CarteRepository carteRepository;

    @Autowired
    private EntrepriseRepository entrepriseRepository;

    @Autowired
    private SecteurRepository secteurRepository;


    public static void main(String[] args) {
        SpringApplication.run(CarteApplication.class, args);
    }


    @Bean
    public CommandLineRunner start() {
        return args -> {
            // Création d'un secteur
            Secteur secteur = new Secteur();
            secteur.setNom("Informatique");
            secteur.setEntreprises(new ArrayList<>());
            secteur = secteurRepository.save(secteur);


            // Création d'une carte
            Carte carte = new Carte();
            carte.setAdresse("123 Rue Principale");
            carte.setHeureOuverture("09:00");
            carte.setHeureFermeture("18:00");
            carte.setImageUrl("https://png.pngtree.com/thumb_back/fw800/background/20230817/pngtree-lotus-flower-jpg-pink-lotus-flower-image_13023952.jpg");
            carteRepository.save(carte);

            // Création d'une entreprise
            Entreprise entreprise = new Entreprise();
            entreprise.setNom("Entreprise XYZ");
            entreprise.setDescription("Une entreprise de développement logiciel");
            entreprise.setSecteur(secteur);
            entreprise.setCarte(carte);
            List<Entreprise> entreprises = secteur.getEntreprises();
            entreprises.add(entreprise);
            secteur.setEntreprises(entreprises);
            entrepriseRepository.save(entreprise);



            // Création d'un admin
            Admin admin = new Admin();
            admin.setEmail("admin@example.com");
            admin.setPassword("password");
            adminRepository.save(admin);
        };
    }
}


