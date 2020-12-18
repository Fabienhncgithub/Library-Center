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
public class Avis {
    private int idLivre;
    private User user;
    private String commentaire;
    private int note;

    public Avis(int idLivre, User user, String commentaire, int note) {
        this.idLivre = idLivre;
        this.user = user;
        this.commentaire = commentaire;
        this.note = note;
    }

    public Avis(User user, String commentaire, int note) {
        this.user = user;
        this.commentaire = commentaire;
        this.note = note;
    }
    
    

    public Avis() {
    }

    public int getIdLivre() {
        return idLivre;
    }

    public void setIdLivre(int idLivre) {
        this.idLivre = idLivre;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }




    public String getCommentaire() {
        return commentaire;
    }

    public void setCommentaire(String comment) {
        this.commentaire = comment;
    }

    public int getNote() {
        return note;
    }

    public void setNote(int note) {
        this.note = note;
    }


    
    
    
}
