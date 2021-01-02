
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
import org.mindrot.jbcrypt.BCrypt;

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
        int idBiblio = Integer.parseInt(request.getParameter("bibliotheque"));
        Bibliotheque biblio = facade.getBiblitoheque().getBibliothequeById(idBiblio);
        String password = request.getParameter("password");
        String hasedPassword = BCrypt.hashpw(password, BCrypt.gensalt());

        User user = new User();
        user.setNom(request.getParameter("nom"));
        user.setPrenom(request.getParameter("prenom"));
        user.setEmail(request.getParameter("email"));
        user.setPassword(hasedPassword);
        user.setAdresse(request.getParameter("adresse"));

        boolean result = user.signIn(biblio);

        String errorMessage;
        if (result) {
            errorMessage = "enregistrée";
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
