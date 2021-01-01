/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controler;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.TimeZone;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Bibliotheque;
import model.Exemplaire;
import model.Facade;
import model.Location;
import model.User;

/**
 *
 * @author Fabien
 */
public class MyServletExemplaire extends HttpServlet {

    Facade facade = new Facade();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        User user = (User) request.getSession().getAttribute("user");
        List<Exemplaire> listeExemplaire = (ArrayList<Exemplaire>) request.getServletContext().getAttribute("listeExemplaire");

        request.setAttribute("listeExemplaire", listeExemplaire);

        request.getRequestDispatcher("exemplaire.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Bibliotheque bibliotheque = (Bibliotheque) request.getSession().getAttribute("bibliotheque");
        Location location = new Location();
        String datepicker = request.getParameter("datepicker");
        java.text.DateFormat format = new java.text.SimpleDateFormat("MM/dd/yyyy");
        format.setTimeZone(TimeZone.getTimeZone("Europe/Paris"));
        String errorMessage;
        try {
            java.util.Date da = format.parse(datepicker);
            User user = (User) request.getSession().getAttribute("user");
            location.setIdUser(user.getIdUser());
            int idExemplaire = Integer.parseInt(request.getParameter("idExemplaireSelected"));

            location.setExemplaire(facade.getCentre().getExemplaireById(idExemplaire));
            location.setDateLocation(da);

            if (location.getExemplaire().getType().equals("ebook")) {

                if (facade.getCentre().verifyDispoLocationInsertEbook(location, bibliotheque)) {
                    errorMessage = "Vous avez loué le ebook";
                    request.setAttribute("errorMessage", errorMessage);
                    this.getServletContext().getRequestDispatcher("/exemplaire.jsp").forward(request, response);
                } else {
                    errorMessage = "ebook non disponible";
                    request.setAttribute("errorMessage", errorMessage);
                    this.getServletContext().getRequestDispatcher("/exemplaire.jsp").forward(request, response);
                }
            } else if (location.getExemplaire().getType().equals("livre")) {

                if (facade.getCentre().verifyDispoLocationInsertLivre(location, bibliotheque)) {
                    errorMessage = "Vous avez loué le livre";
                    request.setAttribute("errorMessage", errorMessage);
                    this.getServletContext().getRequestDispatcher("/exemplaire.jsp").forward(request, response);
                } else {
                    errorMessage = "Livre non disponible";
                    request.setAttribute("errorMessage", errorMessage);
                    this.getServletContext().getRequestDispatcher("/exemplaire.jsp").forward(request, response);
                }
            }
        } catch (ParseException ex) {
            Logger.getLogger(MyServletExemplaire.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
