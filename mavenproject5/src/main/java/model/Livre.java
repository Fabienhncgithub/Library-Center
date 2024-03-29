/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;
import java.util.List;
import model.dao.AbstractDaoFactory;
import model.dao.LivreDao;

/**
 *
 * @author Fabien
 */
public class Livre {

    private int idLivre;
    private String titre;
    private String auteur;
    private String editeur;
    private int page;
    private float prixAchat;
    private Double noteTotal;

    public Livre(int idLivre, String titre, String auteur, String editeur, int page, float prixAchat, Double noteTotal) {
        this.idLivre = idLivre;
        this.titre = titre;
        this.auteur = auteur;
        this.editeur = editeur;
        this.page = page;
        this.prixAchat = prixAchat;
        this.noteTotal = noteTotal;
    }

    public Livre(int idLivre, String titre, String editeur, int page, Double noteTotal) {
        this.idLivre = idLivre;
        this.titre = titre;
        this.editeur = editeur;
        this.page = page;
        this.noteTotal = noteTotal;
    }

    public Livre(int idLivre, String titre, String editeur, int page) {
        this.idLivre = idLivre;
        this.titre = titre;
        this.editeur = editeur;
        this.page = page;
    }

    public Livre() {
    }

    public int getIdLivre() {
        return idLivre;
    }

    public void setIdLivre(int idLivre) {
        this.idLivre = idLivre;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getAuteur() {
        return auteur;
    }

    public void setAuteur(String auteur) {
        this.auteur = auteur;
    }

    public String getEditeur() {
        return editeur;
    }

    public void setEditeur(String editeur) {
        this.editeur = editeur;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public float getPrixAchat() {
        return prixAchat;
    }

    public void setPrixAchat(float prixAchat) {
        this.prixAchat = prixAchat;
    }

    public Double getNoteTotal() {
        return noteTotal;
    }

    public void setNoteTotal(Double noteTotal) {
        this.noteTotal = noteTotal;
    }

    public List<Livre> getAllLivre() {
        AbstractDaoFactory factory = AbstractDaoFactory.getFactory();
        LivreDao livreDao = factory.createLivreDao();
        return livreDao.getAllLivre();

    }

    public List<Livre> getAllLocation(Bibliotheque bibliotheque, User user) {
        AbstractDaoFactory factory = AbstractDaoFactory.getFactory();
        LivreDao livreDao = factory.createLivreDao();
        return livreDao.getAllLocation(bibliotheque, user);
    }

    public Livre getLivreById(int idLivreAvis) {
        AbstractDaoFactory factory = AbstractDaoFactory.getFactory();
        LivreDao livreDao = factory.createLivreDao();
        return livreDao.getLivreById(idLivreAvis);
    }

    public Livre getLivreByNom(String titreLivreAvis) {
        AbstractDaoFactory factory = AbstractDaoFactory.getFactory();
        LivreDao livreDao = factory.createLivreDao();
        return livreDao.getLivreByNom(titreLivreAvis);
    }

    public void insertAvis(Avis avis) {
        AbstractDaoFactory factory = AbstractDaoFactory.getFactory();
        LivreDao livreDao = factory.createLivreDao();
        livreDao.insertAvis(avis);
    }

    public Exemplaire getExemplaireById(int idExemplaireRendu) {
        AbstractDaoFactory factory = AbstractDaoFactory.getFactory();
        LivreDao livreDao = factory.createLivreDao();
        return livreDao.getExemplaireById(idExemplaireRendu);
    }

    public Location getLocationById(int idLocationRendu) {
        AbstractDaoFactory factory = AbstractDaoFactory.getFactory();
        LivreDao livreDao = factory.createLivreDao();
        return livreDao.getLocationById(idLocationRendu);
    }

    public List<Avis> getAvisByIdUser(User user) {
        AbstractDaoFactory factory = AbstractDaoFactory.getFactory();
        LivreDao livreDao = factory.createLivreDao();
        return livreDao.getAvisByIdUser(user);
    }

    public boolean getAvisByIdUSerIdLivreSelected(User user, Livre livre) {
        AbstractDaoFactory factory = AbstractDaoFactory.getFactory();
        LivreDao livreDao = factory.createLivreDao();
        return livreDao.getAvisByIdUSerIdLivreSelected(user, livre);
    }



    public void registerPage(int pageSelect, int idLocation) {
         AbstractDaoFactory factory = AbstractDaoFactory.getFactory();
        LivreDao livreDao = factory.createLivreDao();
        livreDao.registerPage(pageSelect, idLocation);
    }

}
