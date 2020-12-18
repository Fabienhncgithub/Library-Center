/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao;

import java.util.List;
import model.Bibliotheque;
import model.Exemplaire;
import model.Livre;
import model.Role;
import model.User;

/**
 *
 * @author Fabien
 */
public interface UserDao {

    public User authentification(User user, Bibliotheque bibliotheque);

    public List<User> getAllUser();

    public boolean signIn(User user, Bibliotheque bibliotheque);

    public boolean getStatutCotisation(User user, Bibliotheque bibliotheque);

    public void validationCotisation(User user, Bibliotheque bibliotheque);

    public List<User> getAllManager();

    public void addBook(Exemplaire exemplaire, Bibliotheque bibliotheque);

    public void addManager(User user);

    public void insertAvis(Livre livre, User user, String avis, double note);

    public List<Role> getAllRole();

    public Role getRoleByid(int idRole);

    public boolean updateProfil(User user);

      public void payerAmende(User user);

}
