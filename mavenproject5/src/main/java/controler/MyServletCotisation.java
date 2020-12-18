/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controler;

import java.io.IOException;
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
public class MyServletCotisation extends HttpServlet {

    Facade facade = new Facade();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        User user = (User)request.getSession().getAttribute("user");
        Bibliotheque bibliotheque = (Bibliotheque)request.getSession().getAttribute("bibliotheque");
        String statutCotisation;

        Boolean paiementCoti = facade.getUser().getStatutCotisation(user, bibliotheque);
        if (!paiementCoti) {
            statutCotisation = "Vous ête en ordre";
        } else {
            statutCotisation = "Vous devez payer votre cotisation";
            String paiementCotisation = "Veuilliez choisir votre méthode de paiement";
            request.setAttribute("paiementCotisation", paiementCotisation);
        }
        request.setAttribute("statutCotisation", statutCotisation);
        request.getRequestDispatcher("cotisation.jsp").forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        User user = (User) request.getSession().getAttribute("user");
        Bibliotheque bibliotheque = (Bibliotheque) request.getSession().getAttribute("bibliotheque");
        facade.getUser().validationCotisation(user, bibliotheque);
        response.sendRedirect(request.getContextPath() + "/MyServletCotisation.do");

    }

}
