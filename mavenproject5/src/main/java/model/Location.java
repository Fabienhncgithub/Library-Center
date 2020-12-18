/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.Date;

/**
 *
 * @author Fabien
 */
public class Location {

    private int idLocation;
    private Exemplaire exemplaire;
    private int idUser;
    private Date dateLocation;

    public Location(int idLocation, Exemplaire exemplaire, int idUser, Date dateLocation) {
        this.idLocation = idLocation;
        this.exemplaire = exemplaire;
        this.idUser = idUser;
        this.dateLocation = dateLocation;
    }

    public Location(Exemplaire exemplaire, int idUser, Date dateLocation) {
        this.exemplaire = exemplaire;
        this.idUser = idUser;
        this.dateLocation = dateLocation;
    }
    
    
    

    public Location() {
    }



    public int getIdLocation() {
        return idLocation;
    }

    public void setIdLocation(int idLocation) {
        this.idLocation = idLocation;
    }

    public Exemplaire getExemplaire() {
        return exemplaire;
    }

    public void setExemplaire(Exemplaire exemplaire) {
        this.exemplaire = exemplaire;
    }


    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }


    public Date getDateLocation() {
        return dateLocation;
    }

    public void setDateLocation(Date dateLocation) {
        this.dateLocation = dateLocation;
    }

}
