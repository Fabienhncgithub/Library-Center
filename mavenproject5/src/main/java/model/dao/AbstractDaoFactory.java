/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao;

import model.Livre;

/**
 *
 * @author Fabien
 */
public abstract class AbstractDaoFactory {

    private static AbstractDaoFactory factory;

    public static AbstractDaoFactory getFactory() {
        return factory;
    }

    public static void setFactory(AbstractDaoFactory factory) {
        AbstractDaoFactory.factory = factory;
    }

    public abstract UserDao createUserDao();

    public abstract LivreDao createLivreDao();

    public abstract BibliothequeDao createBibliothequeDao();
}
