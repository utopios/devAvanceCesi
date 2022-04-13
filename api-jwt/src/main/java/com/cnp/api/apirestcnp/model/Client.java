package com.cnp.api.apirestcnp.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "client")
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_client", nullable = false)
    private Integer id;

    @Column(name = "ric_numero", length = 100)
    private String ricNumero;

    @Column(name = "nom_client", length = 100)
    private String nomClient;

    @Column(name = "prenom_client", length = 100)
    private String prenomClient;

    @Column(name = "date_naissance")
    private Date dateNaissance;

    @Column(name = "lieu_naissance", length = 100)
    private String lieuNaissance;



}