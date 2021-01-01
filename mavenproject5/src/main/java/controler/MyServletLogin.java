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

        
        List<Bibliotheque> listeDeBibliotheque = facade.getCentre().getAllBibliotheque();
        
        request.getSession().setAttribute("listeDeBibliotheque", listeDeBibliotheque);
        request.getRequestDispatcher("index.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String email = request.getParameter("email").trim();
        String password = request.getParameter("password").trim();
        String errorMessage;
        if (email.isEmpty() && password.isEmpty()) {
            errorMessage = "Please enter username and password";
            request.setAttribute("errorMessage", errorMessage);
            this.getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
        } else if (email.isEmpty()) {
            errorMessage = "Please enter valid email";
            request.setAttribute("errorMessage", errorMessage);
            this.getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
        } else if (password.isEmpty()) {
            errorMessage = "Please enter valid password";
            request.setAttribute("errorMessage", errorMessage);
            this.getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
        } else {
            User user = new User();
            user.setEmail(email);
            user.setPassword(password);
            int idBibliotheque = Integer.parseInt(request.getParameter("bibliotheque"));
            Bibliotheque bibliotheque = facade.getBiblitoheque().getBibliothequeById(idBibliotheque);
            user = facade.getUser().authentification(user, bibliotheque);

            if (user != null) {
                request.getSession().setAttribute("user", user);
                request.getSession().setAttribute("bibliotheque", bibliotheque);
         
                response.sendRedirect(request.getContextPath() + "/MyServletLivre.do");
            } else {
                errorMessage = "This user is not register in this biliotheque";
                request.setAttribute("errorMessage", errorMessage);
                this.getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
            }
        }
    }
}
