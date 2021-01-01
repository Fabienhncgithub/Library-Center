/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controler;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Bibliotheque;
import model.Facade;
import model.Livre;
import model.User;
import model.dao.AbstractDaoFactory;
import model.dao.mysql.MySqlDaoFactory;

/**
 *
 * @author Fabien
 */
public class MyServletLocation extends HttpServlet {
    
    
     Facade facade = new Facade();



    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
   
        Livre livre = new Livre();

        List<Livre> listeDeLivre = livre.getAllLivre();
        request.setAttribute("listeDeLivre", listeDeLivre);
        request.getRequestDispatcher("menuClient.jsp").forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

                  User user = (User) request.getSession().getAttribute("user");
        int idUser = user.getIdUser();

        String idL = request.getParameter("louer");
        int idLivre = Integer.parseInt(idL);

        facade.getBiblitoheque().location(idLivre, idUser);
    }

}
