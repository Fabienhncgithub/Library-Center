/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;
import java.util.List;
import javax.jws.WebService;
import model.dao.AbstractDaoFactory;
import model.dao.BibliothequeDao;

@WebService(name = "CentreWS")
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

    public List<Livre> searchBook(String searchLivre) {
        AbstractDaoFactory factory = AbstractDaoFactory.getFactory();
        BibliothequeDao bibliDao= factory.createBibliothequeDao();
        return bibliDao.searchBook(searchLivre);
    }
}


