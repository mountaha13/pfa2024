package ma.xproce.carte.dao.entities;

import jakarta.persistence.*;
import lombok.*;
import java.util.List;

    @Entity
    @Getter
    @Setter
    @ToString
    @NoArgsConstructor
    @AllArgsConstructor
    public class Secteur {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)

        private Integer id;
        private String nom;
        @OneToMany
        private List<Entreprise> entreprises;
}
