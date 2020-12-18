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
import model.Exemplaire;
import model.Facade;
import model.Livre;

/**
 *
 * @author Fabien
 */
public class MyServletAddBook extends HttpServlet {

    Facade facade = new Facade();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        /* TODO Supprimer le type? rajouter un exemplaier si il existe pas sinon livre*/
        request.getRequestDispatcher("addBook.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Bibliotheque bibliotheque = (Bibliotheque) request.getSession().getAttribute("bibliotheque");

        Exemplaire exemplaire = new Exemplaire();
        Livre livre = new Livre();

        livre.setTitre(request.getParameter("titre"));
        livre.setAuteur(request.getParameter("auteur"));
        livre.setEditeur(request.getParameter("editeur"));
        livre.setPage(Integer.parseInt(request.getParameter("page")));
        livre.setPrixAchat(Integer.parseInt(request.getParameter("prix")));
        if(facade.getLivre().getLivreByNom("titre")!=null){
            livre.setIdLivre(facade.getLivre().getLivreByNom("titre").getIdLivre());
        }
        
        
        exemplaire.setLivre(livre);
        exemplaire.setType(request.getParameter("type"));

         facade.getUser().addBook(exemplaire, bibliotheque);
    }

}