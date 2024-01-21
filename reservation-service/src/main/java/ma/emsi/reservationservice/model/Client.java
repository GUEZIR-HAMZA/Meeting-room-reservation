package ma.emsi.reservationservice.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data @NoArgsConstructor @AllArgsConstructor
public class Client {
    private Long id;
    private String nom;
    private String prenom;
    private String adresse;
    private String telephone;
    private String email;
}