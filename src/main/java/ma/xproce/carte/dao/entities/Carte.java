package ma.xproce.carte.dao.entities;

import jakarta.persistence.*;
import lombok.*;



    @Entity

    @Getter
    @Setter
    @ToString
    @NoArgsConstructor
    @AllArgsConstructor
    public class Carte {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Integer id;
        private String adresse;
        private String heureOuverture;
        private String heureFermeture;
        private String numeroTelephone;
        private String emailProfessionnel;
        private String imageUrl;

        @OneToOne (mappedBy = "carte", cascade = CascadeType.ALL, orphanRemoval = true)

        private Entreprise entreprise;


    }
