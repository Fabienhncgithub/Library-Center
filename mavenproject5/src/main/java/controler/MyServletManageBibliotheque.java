/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controler;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Bibliotheque;
import model.Facade;
import model.User;

/**
 *
 * @author Fabien
 */
public class MyServletManageBibliotheque extends HttpServlet {

    Facade facade = new Facade();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        User user = new User();

        List<User> listeDeManager = facade.getBiblitoheque().getAvailableManager();
        request.getSession().setAttribute("listeDeManager", listeDeManager);
        request.getRequestDispatcher("createBibliotheque.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int idUser = Integer.parseInt(request.getParameter("manager"));
        Bibliotheque bibliotheque = new Bibliotheque();
        bibliotheque.setNom(request.getParameter("nom"));
        bibliotheque.setAdresse(request.getParameter("adresse"));

        boolean result = facade.getCentre().createBibliotheque(bibliotheque, idUser);

        String errorMessage;
        if (result) {
            errorMessage = "bibliothèque enregistrée";
            request.setAttribute("errorMessage", errorMessage);
            this.getServletContext().getRequestDispatcher("/createBibliotheque.jsp").forward(request, response);
        } else {
            errorMessage = "Cette bibliothèque existe déjà";
            request.setAttribute("errorMessage", errorMessage);
            this.getServletContext().getRequestDispatcher("/createBibliotheque.jsp").forward(request, response);

        }

    }
}
