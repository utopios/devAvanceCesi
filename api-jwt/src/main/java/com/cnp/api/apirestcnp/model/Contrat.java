package com.cnp.api.apirestcnp.model;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@AllArgsConstructor
@Entity
@Table(name = "contrat")
@NoArgsConstructor
public class Contrat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_contrat", nullable = false)
    private Integer id;

    @Column(name = "type_contrat", length = 100)
    private String typeContrat;

    @Column(name = "prix_mensualite")
    private Double prixMensualite;

    @Column(name = "formule", length = 100)
    private String formule;

    public String getFormule() {
        return formule;
    }

    public void setFormule(String formule) {
        this.formule = formule;
    }

    public Double getPrixMensualite() {
        return prixMensualite;
    }

    public void setPrixMensualite(Double prixMensualite) {
        this.prixMensualite = prixMensualite;
    }

    public String getTypeContrat() {
        return typeContrat;
    }

    public void setTypeContrat(String typeContrat) {
        this.typeContrat = typeContrat;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}