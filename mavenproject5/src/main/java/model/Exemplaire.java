/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

public class Exemplaire {

    private int idExemplaire;
    private String type;
    private Livre livre;
    private Boolean disponible;
    private Boolean rendu;
    private Boolean verifier;
    private String path;

    public Exemplaire(int idExemplaire, String type, Livre livre, Boolean disponible, Boolean rendu, Boolean verifier) {
        this.idExemplaire = idExemplaire;
        this.type = type;
        this.livre = livre;
        this.disponible = disponible;
        this.rendu = rendu;
        this.verifier = verifier;
    }

    public Exemplaire(String type, Livre livre, String path) {
        this.type = type;
        this.livre = livre;
        this.path = path;
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

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    @Override
    public String toString() {
        return "Exemplaire{" + "idExemplaire=" + idExemplaire + ", type=" + type + ", livre=" + livre + ", disponible=" + disponible + ", rendu=" + rendu + ", verifier=" + verifier + ", path=" + path + '}';
    }


    
    
    
}
