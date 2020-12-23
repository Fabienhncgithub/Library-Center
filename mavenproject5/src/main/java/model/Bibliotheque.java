/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import model.dao.AbstractDaoFactory;
import model.dao.BibliothequeDao;

/**
 *
 * @author Fabien
 */
public class Bibliotheque {

    private int idBibliotheque;
    private String nom;
    private String Adresse;
    private int idManager;
    private List<Bibliotheque> listeDeBiliotheque = new ArrayList<>();

    public Bibliotheque(int idBibliotheque, String nom, String Adresse, int idManager) {
        this.idBibliotheque = idBibliotheque;
        this.nom = nom;
        this.Adresse = Adresse;
        this.idManager = idManager;
    }

    public Bibliotheque(int idBibliotheque, String nom) {
        this.idBibliotheque = idBibliotheque;
        this.nom = nom;
    }

    public Bibliotheque() {
    }

    public Bibliotheque(String nom) {
        this.nom = nom;
    }

    public int getIdBibliotheque() {
        return idBibliotheque;
    }

    public void setIdBibliotheque(int idBibliotheque) {
        this.idBibliotheque = idBibliotheque;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getAdresse() {
        return Adresse;
    }

    public void setAdresse(String Adresse) {
        this.Adresse = Adresse;
    }

    public int getIdManager() {
        return idManager;
    }

    public void setIdManager(int idManager) {
        this.idManager = idManager;
    }

    public List<Bibliotheque> getListeDeBiliotheque() {
        return listeDeBiliotheque;
    }

    public void setListeDeBiliotheque(List<Bibliotheque> listeDeBiliotheque) {
        this.listeDeBiliotheque = listeDeBiliotheque;
    }

    public List<Bibliotheque> getAllBibliotheque() {
        AbstractDaoFactory factory = AbstractDaoFactory.getFactory();
        BibliothequeDao bibliDao = factory.createBibliothequeDao();
        listeDeBiliotheque = bibliDao.getAllBibliotheque();
        return listeDeBiliotheque;
    }

    public Bibliotheque getBibliothequeById(int idBibliotheque) {
        AbstractDaoFactory factory = AbstractDaoFactory.getFactory();
        BibliothequeDao bibliDAO = factory.createBibliothequeDao();
        return bibliDAO.getBibliothèqueById(idBibliotheque);
    }

    public static Bibliotheque verifyMember(int id, Bibliotheque bibliotheque) {
        AbstractDaoFactory factory = AbstractDaoFactory.getFactory();
        BibliothequeDao bibliDAO = factory.createBibliothequeDao();
        return bibliDAO.verifyMember(id, bibliotheque);
    }

    public void location(int idexemplaire, int iduser) {
        AbstractDaoFactory factory = AbstractDaoFactory.getFactory();
        BibliothequeDao bibliDAO = factory.createBibliothequeDao();
        bibliDAO.location(idexemplaire, iduser);
    }

    public List<Exemplaire> exemplaireByType(int idLivre) {
        AbstractDaoFactory factory = AbstractDaoFactory.getFactory();
        BibliothequeDao bibliDAO = factory.createBibliothequeDao();
        return bibliDAO.exemplaireByType(idLivre);
    }

    public void createBibliotheque(Bibliotheque bibliotheque, int idUser) {
        AbstractDaoFactory factory = AbstractDaoFactory.getFactory();
        BibliothequeDao bibliDAO = factory.createBibliothequeDao();
        bibliDAO.createBibliotheque(bibliotheque, idUser);
    }

    public List<Livre> getAllLivreByBibliotheque(Bibliotheque bibliotheque) {
        AbstractDaoFactory factory = AbstractDaoFactory.getFactory();
        BibliothequeDao bibliothequeDao = factory.createBibliothequeDao();
        return bibliothequeDao.getAllLivreByBibliotheque(bibliotheque);
    }

    public List<Exemplaire> getAllExemplaireByTypeByBibliotheque(int idLivre, Bibliotheque bibliotheque) {
        AbstractDaoFactory factory = AbstractDaoFactory.getFactory();
        BibliothequeDao bibliothequeDao = factory.createBibliothequeDao();
        return bibliothequeDao.getAllExemplaireByTypeByBibliotheque(idLivre, bibliotheque);
    }

    public void insertQuestion(String question, User user) {
        AbstractDaoFactory factory = AbstractDaoFactory.getFactory();
        BibliothequeDao bibliothequeDao = factory.createBibliothequeDao();
        bibliothequeDao.insertQuestion(question, user);
    }

    public List<Date> getDateLocation(int idExemplaireSelected) {
        AbstractDaoFactory factory = AbstractDaoFactory.getFactory();
        BibliothequeDao bibliothequeDao = factory.createBibliothequeDao();
        return bibliothequeDao.getDateLocation(idExemplaireSelected);
    }

    public void location(Location loc) {
        AbstractDaoFactory factory = AbstractDaoFactory.getFactory();
        BibliothequeDao bibliothequeDao = factory.createBibliothequeDao();
        bibliothequeDao.location(loc);
    }

    public Boolean verifyDispoLocationInsertEbook(Location location, Bibliotheque bibliotheque) {
        AbstractDaoFactory factory = AbstractDaoFactory.getFactory();
        BibliothequeDao bibliothequeDao = factory.createBibliothequeDao();
        return bibliothequeDao.verifyDispoLocation(location, bibliotheque);
    }

    public Exemplaire getExemplaireById(int idExemplaire) {
        AbstractDaoFactory factory = AbstractDaoFactory.getFactory();
        BibliothequeDao bibliothequeDao = factory.createBibliothequeDao();
        return bibliothequeDao.getExemplaireById(idExemplaire);
    }

    public boolean verifyDispoLocationInsertLivre(Location location, Bibliotheque bibliotheque) {
        AbstractDaoFactory factory = AbstractDaoFactory.getFactory();
        BibliothequeDao bibliothequeDao = factory.createBibliothequeDao();
        return bibliothequeDao.verifyDispoLocationLivre(location, bibliotheque);
    }

    public List<Faq> listeQuestionByUser(User user, Bibliotheque bibliotheque) {
        AbstractDaoFactory factory = AbstractDaoFactory.getFactory();
        BibliothequeDao bibliothequeDao = factory.createBibliothequeDao();
        return bibliothequeDao.listeQuestionByUser(user, bibliotheque);
    }

    public void reponseQuestion(User user, String reponse, int idQuestion) {
        AbstractDaoFactory factory = AbstractDaoFactory.getFactory();
        BibliothequeDao bibliothequeDao = factory.createBibliothequeDao();
        bibliothequeDao.reponseQuestion(user, reponse, idQuestion);
    }

    public List<Faq> listeQuestionByIdBibliotheque(Bibliotheque bibliotheque) {
        AbstractDaoFactory factory = AbstractDaoFactory.getFactory();
        BibliothequeDao bibliothequeDao = factory.createBibliothequeDao();
        return bibliothequeDao.listeQuestionByIdBibliotheque(bibliotheque);
    }

    public List<Location> getAllLocationByIdUserIdBibliotheque(Bibliotheque bibliotheque, User user) {
        AbstractDaoFactory factory = AbstractDaoFactory.getFactory();
        BibliothequeDao bibliothequeDao = factory.createBibliothequeDao();
        return bibliothequeDao.getAllLocationByIdUserIdBibliotheque(bibliotheque,  user);
    }

    public List<Avis> getAllAvisbyIdLivre(int idLivreSelected) {
        AbstractDaoFactory factory = AbstractDaoFactory.getFactory();
        BibliothequeDao bibliothequeDao = factory.createBibliothequeDao();
        return bibliothequeDao.getAllAvisbyIdLivre(idLivreSelected);
    }
    
    public void updateCoti(){
     AbstractDaoFactory factory = AbstractDaoFactory.getFactory();
        BibliothequeDao bibliothequeDao = factory.createBibliothequeDao();
        bibliothequeDao.updateCoti();
    }

    public List<User>  getAvailableManager() {
               AbstractDaoFactory factory = AbstractDaoFactory.getFactory();
        BibliothequeDao bibliothequeDao = factory.createBibliothequeDao();
       return  bibliothequeDao.getAvailableManager();
    }

    public List<Location> getAllLocationByBibliotheque(Bibliotheque bibliotheque) {
          AbstractDaoFactory factory = AbstractDaoFactory.getFactory();
        BibliothequeDao bibliothequeDao = factory.createBibliothequeDao();
        return  bibliothequeDao.getAllLocationByBibliotheque(bibliotheque);
    }

    public void validationRetour(int idExemplaireValider) {
               AbstractDaoFactory factory = AbstractDaoFactory.getFactory();
        BibliothequeDao bibliothequeDao = factory.createBibliothequeDao();
        bibliothequeDao.validationRetour(idExemplaireValider);
    }

    public void supprimerLocation(Location location) {
      AbstractDaoFactory factory = AbstractDaoFactory.getFactory();
        BibliothequeDao bibliothequeDao = factory.createBibliothequeDao();
        bibliothequeDao.supprimerLocation(location);
    }

    public Map<String, Integer> searchBook(String search) {
      AbstractDaoFactory factory = AbstractDaoFactory.getFactory();
        BibliothequeDao bibliothequeDao = factory.createBibliothequeDao();
        return bibliothequeDao.searchBook(search);
    }


}
