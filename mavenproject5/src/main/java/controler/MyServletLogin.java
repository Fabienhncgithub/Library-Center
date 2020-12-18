/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controler;

/**
 *
 * @author Fabien
 */
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Bibliotheque;
import model.Facade;
import model.dao.AbstractDaoFactory;
import model.dao.mysql.MySqlDaoFactory;
import model.User;

public class MyServletLogin extends HttpServlet {

    Facade facade = new Facade();

    @Override
    public void init() throws ServletException {

        if (AbstractDaoFactory.getFactory() == null) {
            AbstractDaoFactory.setFactory(MySqlDaoFactory.getInstance());
            facade.getBiblitoheque().updateCoti();
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Bibliotheque bibliotheque = new Bibliotheque();

        List<Bibliotheque> listeDeBibliotheque = bibliotheque.getAllBibliotheque();
        request.getSession().setAttribute("listeDeBibliotheque", listeDeBibliotheque);
        request.getRequestDispatcher("index.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

          User user = new User();
        user.setEmail(request.getParameter("email"));
        user.setPassword(request.getParameter("password"));
        int idBibliotheque = Integer.parseInt(request.getParameter("bibliotheque"));

      

        Bibliotheque bibliotheque = facade.getBiblitoheque().getBibliothequeById(idBibliotheque);

        /*TODO tester le getbibliothequebyid*/
//          request.getSession().setAttribute("bibliotheque", bibliotheque);
//    request.getRequestDispatcher("test.jsp").forward(request, response);
        user = facade.getUser().authentification(user, bibliotheque);

     //   List<Bibliotheque> listeDeBiblio = facade.getBiblitoheque().getAllBibliotheque();

      
     
        request.getSession().setAttribute("user", user);
        request.getSession().setAttribute("bibliotheque", bibliotheque);

        if (user != null) {
            String role = user.getRole().getNomRole();
            switch (role) {
                case "client":
                    response.sendRedirect(request.getContextPath() + "/MyServletLivre.do");
                    break;
                case "bibliothécaire":
                    request.getRequestDispatcher("menuBibliothécaire.jsp");

                    break;
                case "admin":
                    response.sendRedirect(request.getContextPath() + "/MyServletLivre.do");
                    /*TODO a nettoyer*/
                    break;
            }
        } else {
            String errorMessage = "This user is not register in this biliotheque";
            request.setAttribute("errorMessage", errorMessage);
            this.getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
        }
    }
}
