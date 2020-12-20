/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao;

import java.sql.Date;
import java.util.List;
import java.util.Map;
import model.Avis;
import model.Bibliotheque;
import model.Exemplaire;
import model.Faq;
import model.Livre;
import model.Location;
import model.User;

/**
 *
 * @author Fabien
 */
public interface BibliothequeDao {

    public Bibliotheque getBibliotheque();

    public List<Bibliotheque> getAllBibliotheque();

    public Bibliotheque getBibliothèqueById(int id);

    public Bibliotheque verifyMember(int id, Bibliotheque bibliotheque);

    public void location(int idexemplaire, int iduser);

    public List<Exemplaire> exemplaireByType(int idLivre);

    public void createBibliotheque(Bibliotheque bibliotheque, int idUser);

    public List<Livre> getAllLivreByBibliotheque(Bibliotheque bibliotheque);

    public List<Exemplaire> getAllExemplaireByTypeByBibliotheque(int idLivre,Bibliotheque bibliotheque);

    public void insertQuestion(String question,User user);

    public List<Date> getDateLocation(int idExemplaireSelected);

    public void location(Location location);


    public Exemplaire getExemplaireById(int idExemplaire);

    public boolean verifyDispoLocation(Location location, Bibliotheque bibliotheque);

    public boolean verifyDispoLocationLivre(Location location, Bibliotheque bibliotheque);

    public List<Faq> listeQuestionByUser(User user, Bibliotheque bibliotheque);

    public void reponseQuestion(User user, String reponse, int idQuestion);

    public List<Faq> listeQuestionByIdBibliotheque(Bibliotheque bibliotheque);

    public Map<String, Integer> searchBook(String searchLivre);

    public List<Location> getAllLocationByIdUserIdBibliotheque(Bibliotheque bibliotheque, User user);

    public List<Avis> getAllAvisbyIdLivre(int idLivreSelected);

    public void updateCoti();

    public List<User> getAvailableManager();

    public List<Location> getAllLocationByBibliotheque(Bibliotheque bibliotheque);

    public void validationRetour(int idExemplaireValider);

    public void supprimerLocation(Location location);







}
