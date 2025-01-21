package ma.xproce.carte.dao.entities;

import jakarta.persistence.*;
import lombok.*;


    @Entity

    @Getter
    @Setter
    @ToString
    @NoArgsConstructor
    @AllArgsConstructor
    public class Entreprise {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)

        private Integer id;
        private String nom;
        private String description;

        @ManyToOne

        private Secteur secteur;
        @OneToOne
        private Carte  carte;

    }
