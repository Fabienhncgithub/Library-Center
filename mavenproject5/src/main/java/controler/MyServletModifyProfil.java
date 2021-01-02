package controler;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Facade;
import model.Role;
import model.User;
import org.mindrot.jbcrypt.BCrypt;

public class MyServletModifyProfil extends HttpServlet {

    Facade facade = new Facade();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        User user = (User) request.getSession().getAttribute("user");

        List<Role> listRole = facade.getUser().getAllRole();
        request.setAttribute("listRole", listRole);
        request.getRequestDispatcher("editProfil.jsp").forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        


        User usr = (User) request.getSession().getAttribute("user");
        User user = new User();
        user.setIdUser(usr.getIdUser());
        user.setNom(request.getParameter("nom"));
        user.setPrenom(request.getParameter("prenom"));
        user.setEmail(request.getParameter("email"));
        String pass = request.getParameter("password");
        String hashpass = BCrypt.hashpw(pass, BCrypt.gensalt());
        user.setPassword(hashpass);
        user.setAdresse(request.getParameter("adresse"));
        boolean result = facade.getUser().updateProfil(user);

        String errorMessage;
        if (result) {
            errorMessage = "Modification(s) enregistrée(s)";
            request.setAttribute("errorMessage", errorMessage);
            this.getServletContext().getRequestDispatcher("/editProfil.jsp").forward(request, response);
        } else {
            errorMessage = "L'email existe déjà";
            request.setAttribute("errorMessage", errorMessage);
            this.getServletContext().getRequestDispatcher("/editProfil.jsp").forward(request, response);
        }

    }

}
