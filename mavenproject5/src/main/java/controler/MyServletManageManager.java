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

public class MyServletManageManager extends HttpServlet {

    Facade facade = new Facade();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        List<Role> listRole = facade.getUser().getAllRole();
        request.setAttribute("listRole", listRole);
        request.getRequestDispatcher("createManager.jsp").forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        User user = new User();
        user.setNom(request.getParameter("nom"));
        user.setPrenom(request.getParameter("prenom"));
        user.setEmail(request.getParameter("email"));
        user.setPassword(request.getParameter("password"));
        user.setAdresse(request.getParameter("adresse"));
        int idRole = Integer.parseInt(request.getParameter("role"));
        user.setRole(facade.getUser().getRoleById(idRole));
    

        facade.getUser().addManager(user);
    }

}
