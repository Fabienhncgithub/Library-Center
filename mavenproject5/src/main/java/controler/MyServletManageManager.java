package controler;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Bibliotheque;
import model.Facade;
import model.Role;
import model.User;
import org.mindrot.jbcrypt.BCrypt;

public class MyServletManageManager extends HttpServlet {

    Facade facade = new Facade();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        List<Role> listRole = facade.getCentre().getAllRole();
        request.setAttribute("listRole", listRole);
        request.getRequestDispatcher("createManager.jsp").forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

            Bibliotheque bibliotheque = (Bibliotheque) request.getSession().getAttribute("bibliotheque");
        
        User user = new User();
        user.setNom(request.getParameter("nom"));
        user.setPrenom(request.getParameter("prenom"));
        user.setEmail(request.getParameter("email"));
        String haspassword = BCrypt.hashpw(request.getParameter("password"),BCrypt.gensalt());
        user.setPassword(haspassword);
        user.setAdresse(request.getParameter("adresse"));
        int idRole = Integer.parseInt(request.getParameter("role"));
        user.setRole(facade.getCentre().getRoleById(idRole));
       boolean result;
        if(idRole == 3 || idRole ==4){
        result = facade.getUser().addManager(user);
        }else{
         result = facade.getUser().addProfil(user,bibliotheque);
        }
        String errorMessage;
        if (result) {
            errorMessage = "Utilisateur enregistrée";
            request.setAttribute("errorMessage", errorMessage);
            this.getServletContext().getRequestDispatcher("/createManager.jsp").forward(request, response);
        } else {
            errorMessage = "Cet utilisateur existe déjà";
            request.setAttribute("errorMessage", errorMessage);
            this.getServletContext().getRequestDispatcher("/createManager.jsp").forward(request, response);

        }

    }

}
