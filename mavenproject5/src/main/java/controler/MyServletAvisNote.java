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
import model.Avis;
import model.Facade;
import model.User;

/**
 *
 * @author Fabien
 */
public class MyServletAvisNote extends HttpServlet {

    Facade facade = new Facade();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String titreLivreAvis = (String) request.getSession().getAttribute("titreLivreAvis");

        request.setAttribute("titreLivreAvis", titreLivreAvis);
        request.getRequestDispatcher("avisNote.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        User user = (User) request.getSession().getAttribute("user");
        String titreLivreAvis = (String) request.getSession().getAttribute("titreLivreAvis");

        Avis avis = new Avis();
        avis.setUser(user);
        avis.setIdLivre(facade.getLivre().getLivreByNom(titreLivreAvis).getIdLivre());
        avis.setCommentaire(request.getParameter("avis"));
        avis.setNote(Integer.parseInt(request.getParameter("note")));

  
        facade.getLivre().insertAvis(avis);
        response.sendRedirect(request.getContextPath() + "/MyServletAvisNote.do");
        
        
    }

}
