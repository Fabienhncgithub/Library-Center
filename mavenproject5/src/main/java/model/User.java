/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.List;
import model.dao.AbstractDaoFactory;
import model.dao.BibliothequeDao;
import model.dao.UserDao;

/**
 *
 * @author Fabien
 */
public class User {

    private int idUser;
    private String nom;
    private String prenom;
    private String email;
    private String password;
    private Role role;
    private String adresse;
    private float amende;


    public User(int idUser, String nom, String prenom, String email, String password, Role role, String adresse, float amende) {
        this.idUser = idUser;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.password = password;
        this.role = role;
        this.adresse = adresse;
        this.amende = amende;
    }

    public User(int idUser, String nom, String prenom, String email, String password, Role role, String adresse) {
        this.idUser = idUser;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.password = password;
        this.role = role;
        this.adresse = adresse;
    }

    public User() {
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public String getLogin() {
        return email;
    }

    public void setLogin(String login) {
        this.email = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public float getAmende() {
        return amende;
    }

    public void setAmende(float amende) {
        this.amende = amende;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }


    

    public User authentification(User user, Bibliotheque bibliotheque) {
        AbstractDaoFactory factory = AbstractDaoFactory.getFactory();
        UserDao userDao = factory.createUserDao();
        return userDao.authentification(user, bibliotheque);
    }

    public boolean signIn(Bibliotheque bibliotheque) {
        AbstractDaoFactory factory = AbstractDaoFactory.getFactory();
        UserDao userDao = factory.createUserDao();
        return userDao.signIn(this, bibliotheque);
    }

    public boolean getStatutCotisation(User user, Bibliotheque bibliotheque) {
        AbstractDaoFactory factory = AbstractDaoFactory.getFactory();
        UserDao userDao = factory.createUserDao();
        return userDao.getStatutCotisation(user, bibliotheque);

    }




 

    public boolean addManager(User user) {
        AbstractDaoFactory factory = AbstractDaoFactory.getFactory();
        UserDao userDao = factory.createUserDao();
        return userDao.addManager(user);
    }

    public void insertAvis(Livre livre, User user, String avis, double note) {
        AbstractDaoFactory factory = AbstractDaoFactory.getFactory();
        UserDao userDao = factory.createUserDao();
        userDao.insertAvis(livre, user, avis, note);
    }

    public List<Role> getAllRole() {
        AbstractDaoFactory factory = AbstractDaoFactory.getFactory();
        UserDao userDao = factory.createUserDao();
        return userDao.getAllRole();
    }


    public Boolean updateProfil(User user) {
        AbstractDaoFactory factory = AbstractDaoFactory.getFactory();
        UserDao userDao = factory.createUserDao();
        return userDao.updateProfil(user);
    }

    public void payerAmende() {
        AbstractDaoFactory factory = AbstractDaoFactory.getFactory();
        UserDao userDao = factory.createUserDao();
        userDao.payerAmende(this);
    }
    
    
        public void insertQuestion(String question, User user) {
        AbstractDaoFactory factory = AbstractDaoFactory.getFactory();
            UserDao userDao = factory.createUserDao();
            userDao.insertQuestion(question, user);
    }

    public boolean addProfil(User user, Bibliotheque bibliotheque) {
         AbstractDaoFactory factory = AbstractDaoFactory.getFactory();
        UserDao userDao = factory.createUserDao();
          return userDao.addProfil(user,bibliotheque);
    }

}
