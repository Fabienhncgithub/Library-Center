/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import model.dao.AbstractDaoFactory;
import model.dao.BibliothequeDao;
import model.dao.UserDao;

public class Centre {

    private List<Bibliotheque> listeDeBiliotheque = new ArrayList<>();

    public Centre() {
    }

    public List<Bibliotheque> getListeDeBiliotheque() {
        return listeDeBiliotheque;
    }

    public void setListeDeBiliotheque(List<Bibliotheque> listeDeBiliotheque) {
        this.listeDeBiliotheque = listeDeBiliotheque;
    }

    public List<Role> getAllRole() {
        AbstractDaoFactory factory = AbstractDaoFactory.getFactory();
        UserDao userDao = factory.createUserDao();
        return userDao.getAllRole();
    }

    public List<User> getAllManager() {
        AbstractDaoFactory factory = AbstractDaoFactory.getFactory();
        BibliothequeDao bibliDao = factory.createBibliothequeDao();
        return bibliDao.getAllManager();
    }

    public boolean createBibliotheque(Bibliotheque bibliotheque, int idUser) {
        AbstractDaoFactory factory = AbstractDaoFactory.getFactory();
        BibliothequeDao bibliDAO = factory.createBibliothequeDao();
        return bibliDAO.createBibliotheque(bibliotheque, idUser);
    }

    public Role getRoleById(int idRole) {
        AbstractDaoFactory factory = AbstractDaoFactory.getFactory();
        UserDao userDao = factory.createUserDao();
        return userDao.getRoleByid(idRole);
    }

    public Exemplaire getExemplaireById(int idExemplaire) {
        AbstractDaoFactory factory = AbstractDaoFactory.getFactory();
        BibliothequeDao bibliothequeDao = factory.createBibliothequeDao();
        return bibliothequeDao.getExemplaireById(idExemplaire);
    }

    public Boolean verifyDispoLocationInsertEbook(Location location, Bibliotheque bibliotheque) {
        AbstractDaoFactory factory = AbstractDaoFactory.getFactory();
        BibliothequeDao bibliothequeDao = factory.createBibliothequeDao();
        return bibliothequeDao.verifyDispoLocation(location, bibliotheque);
    }

    public boolean verifyDispoLocationInsertLivre(Location location, Bibliotheque bibliotheque) {
        AbstractDaoFactory factory = AbstractDaoFactory.getFactory();
        BibliothequeDao bibliothequeDao = factory.createBibliothequeDao();
        return bibliothequeDao.verifyDispoLocationLivre(location, bibliotheque);
    }

    public List<Bibliotheque> getAllBibliotheque() {
        AbstractDaoFactory factory = AbstractDaoFactory.getFactory();
        BibliothequeDao bibliDao = factory.createBibliothequeDao();
        listeDeBiliotheque = bibliDao.getAllBibliotheque();
        return listeDeBiliotheque;
    }

    public Map<String, Integer> searchBook(String search) {
        AbstractDaoFactory factory = AbstractDaoFactory.getFactory();
        BibliothequeDao bibliothequeDao = factory.createBibliothequeDao();
        return bibliothequeDao.searchBook(search);
    }

    public Map<String, String> convertionString(String res) {
        res = res.substring(1, res.length() - 1);
        String[] keyValuePairs = res.split(",");
        Map<String, String> mapTitreLivre = new HashMap<>();

        for (String p : keyValuePairs) {
            String[] entry = p.split("=");
            mapTitreLivre.put(entry[0].trim(), entry[1].trim());
        }
        return mapTitreLivre;
    }
}
