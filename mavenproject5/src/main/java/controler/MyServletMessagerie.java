/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Bibliotheque;
import model.Facade;
import model.Faq;
import model.User;

/**
 *
 * @author Fabien
 */
public class MyServletMessagerie extends HttpServlet {

    Facade facade = new Facade();


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        User user = (User) request.getSession().getAttribute("user");
        Bibliotheque bibliotheque = (Bibliotheque) request.getSession().getAttribute("bibliotheque");

        List<Faq> listeQuestionByUser = new ArrayList<>();
        if (user.getRole().getNomRole().equals("admin")) {
            listeQuestionByUser = facade.getBiblitoheque().listeQuestionByIdBibliotheque(bibliotheque);
        } else {
            listeQuestionByUser = facade.getBiblitoheque().listeQuestionByUser(user, bibliotheque);
        }

        request.setAttribute("listeQuestionByUser", listeQuestionByUser);
        request.getRequestDispatcher("messagerie.jsp").forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int idQuestionSelected = Integer.parseInt(request.getParameter("idQuestionSelected"));
        String QuestionSelected = request.getParameter("QuestionSelected");

        request.setAttribute("idQuestionSelected", idQuestionSelected);
        request.setAttribute("QuestionSelected", QuestionSelected);

        request.getRequestDispatcher("repondreMessagerie.jsp").forward(request, response);

    }
}
