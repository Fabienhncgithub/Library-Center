/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Date;

/*TODO vérifier les attributs boolean disponible & rendu*/
public class Exemplaire {

    private int idExemplaire;
    private String type;
    private Livre livre;
    private Boolean disponible;
    private Boolean rendu;
    private Boolean verifier;

    public Exemplaire(int idExemplaire, String type, Livre livre, Boolean disponible, Boolean rendu, Boolean verifier) {
        this.idExemplaire = idExemplaire;
        this.type = type;
        this.livre = livre;
        this.disponible = disponible;
        this.rendu = rendu;
        this.verifier = verifier;
    }
    

    public Exemplaire(int idExemplaire, String type, Livre livre) {
        this.idExemplaire = idExemplaire;
        this.type = type;
        this.livre = livre;
    }

    
        public Exemplaire() {
    }


    public int getIdExemplaire() {
        return idExemplaire;
    }

    public void setIdExemplaire(int idExemplaire) {
        this.idExemplaire = idExemplaire;
    }



    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Livre getLivre() {
        return livre;
    }

    public void setLivre(Livre livre) {
        this.livre = livre;
    }

    public Boolean getDisponible() {
        return disponible;
    }

    public void setDisponible(Boolean disponible) {
        this.disponible = disponible;
    }

    public Boolean getRendu() {
        return rendu;
    }

    public void setRendu(Boolean rendu) {
        this.rendu = rendu;
    }

    public Boolean getVerifier() {
        return verifier;
    }

    public void setVerifier(Boolean verifier) {
        this.verifier = verifier;
    }
    
    
}
