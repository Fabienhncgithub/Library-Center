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
import model.Exemplaire;
import model.Facade;

import model.Livre;
import model.User;

/**
 *
 * @author Fabien
 */
public class MyServletLivre extends HttpServlet {

    Facade facade = new Facade();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Bibliotheque bibliotheque = (Bibliotheque) request.getSession().getAttribute("bibliotheque");
        User user = (User) request.getSession().getAttribute("user");

        Boolean paiementCoti = facade.getUser().getStatutCotisation(user, bibliotheque);
        List<Livre> listeDeLivre = facade.getBiblitoheque().getAllLivreByBibliotheque(bibliotheque);
     
       
        request.setAttribute("paiementCoti", paiementCoti);
        request.setAttribute("listeDeLivre", listeDeLivre);
        request.getRequestDispatcher("menuClient.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Bibliotheque bibliotheque = (Bibliotheque) request.getSession().getAttribute("bibliotheque");

        int idLivreSelected = Integer.parseInt(request.getParameter("idLivreSelected"));
        request.getSession().setAttribute("idLivreSelected", idLivreSelected);

        /* TODO corriger l'exemplaire dans toute les bibliotheques? */
        List<Exemplaire> listeExemplaire = facade.getBiblitoheque().getAllExemplaireByTypeByBibliotheque(idLivreSelected, bibliotheque);

        request.getSession().setAttribute("listeExemplaire", listeExemplaire);

        response.sendRedirect(request.getContextPath() + "/MyServletExemplaire.do");

    }

}
