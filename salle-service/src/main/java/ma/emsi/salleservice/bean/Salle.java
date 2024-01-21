package ma.emsi.salleservice.bean;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class Salle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String numero;
    private boolean climatisation;
    private String equipement;
    private int capacite;
    @ManyToOne
    private Type type;
}
