/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author Fabien
 */
public class Facade {
    
    
    private Admin admin;
    private Bibliothecaire bibliotheciare;
    private Bibliotheque biblitoheque;
    private Centre centre;
    private Exemplaire exemplaire;
    private Livre livre;
    private User user;

    public Facade() {
        this.admin = new Admin();
        this.bibliotheciare = new Bibliothecaire();
        this.biblitoheque = new Bibliotheque();
        this.centre = new Centre();
        this.exemplaire = new Exemplaire();
        this.livre = new Livre();
        this.user = new User();
    }

    public Admin getAdmin() {
        return admin;
    }

    public void setAdmin(Admin admin) {
        this.admin = admin;
    }

    public Bibliothecaire getBibliotheciare() {
        return bibliotheciare;
    }

    public void setBibliotheciare(Bibliothecaire bibliotheciare) {
        this.bibliotheciare = bibliotheciare;
    }

    public Bibliotheque getBiblitoheque() {
        return biblitoheque;
    }

    public void setBiblitoheque(Bibliotheque biblitoheque) {
        this.biblitoheque = biblitoheque;
    }

    public Centre getCentre() {
        return centre;
    }

    public void setCentre(Centre centre) {
        this.centre = centre;
    }

    public Exemplaire getExemplaire() {
        return exemplaire;
    }

    public void setExemplaire(Exemplaire exemplaire) {
        this.exemplaire = exemplaire;
    }

    public Livre getLivre() {
        return livre;
    }

    public void setLivre(Livre livre) {
        this.livre = livre;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

   
    
    
   
    
    
    
    
    
}
