package com.cnp.api.apirestcnp.model;

import javax.persistence.*;

@Entity
@Table(name = "reseau_distribution")
public class ReseauDistribution {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_reseau", nullable = false)
    private Integer id;

    @Column(name = "nom_reseau", length = 100)
    private String nomReseau;

    @Column(name = "adresse_reseau", length = 100)
    private String adresseReseau;

    @Column(name = "ville_reseau", length = 100)
    private String villeReseau;

    @Column(name = "code_postal_reseau", length = 6)
    private String codePostalReseau;

    public String getCodePostalReseau() {
        return codePostalReseau;
    }

    public void setCodePostalReseau(String codePostalReseau) {
        this.codePostalReseau = codePostalReseau;
    }

    public String getVilleReseau() {
        return villeReseau;
    }

    public void setVilleReseau(String villeReseau) {
        this.villeReseau = villeReseau;
    }

    public String getAdresseReseau() {
        return adresseReseau;
    }

    public void setAdresseReseau(String adresseReseau) {
        this.adresseReseau = adresseReseau;
    }

    public String getNomReseau() {
        return nomReseau;
    }

    public void setNomReseau(String nomReseau) {
        this.nomReseau = nomReseau;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}