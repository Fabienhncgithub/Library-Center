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
public class MyServletSignIn extends HttpServlet {

    Facade facade = new Facade();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Bibliotheque> listeDeBibliotheque = (List<Bibliotheque>) request.getServletContext().getAttribute("listeDeBibliotheque");
        request.setAttribute("listeDeBibliotheque", listeDeBibliotheque);
        request.getRequestDispatcher("SignIn.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Bibliotheque bibliotheque = (Bibliotheque) request.getSession().getAttribute("bibliotheque");

        User user = new User();
        user.setNom(request.getParameter("nom"));
        user.setPrenom(request.getParameter("prenom"));
        user.setEmail(request.getParameter("email"));
        user.setPassword(request.getParameter("password"));
        user.setAdresse(request.getParameter("adresse"));

        boolean result = facade.getUser().signIn(user, bibliotheque);

        String errorMessage;
        if (result) {
            errorMessage = " enregistrée";
            request.setAttribute("errorMessage", errorMessage);
            this.getServletContext().getRequestDispatcher("/SignIn.jsp").forward(request, response);
        } else {
            errorMessage = "L'email existe déjà";
            request.setAttribute("errorMessage", errorMessage);
            this.getServletContext().getRequestDispatcher("/SignIn.jsp").forward(request, response);
        }

   //     response.sendRedirect(request.getContextPath() + "/MyServletLogin");
    }

}
