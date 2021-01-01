package controler;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Facade;
import model.Location;
import model.User;

public class MyServletRendreLocation extends HttpServlet {

    Facade facade = new Facade();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        User user = (User) request.getSession().getAttribute("user");

        int idLocationRendu = Integer.parseInt(request.getParameter("idLocationRendu"));
        Location location = facade.getLivre().getLocationById(idLocationRendu);

        
        facade.getBiblitoheque().rendreLocation(location, user);
        response.sendRedirect(request.getContextPath() + "/MyServletHistorique.do");

        //  request.setAttribute("location", location);
        //   response.sendRedirect(request.getContextPath() + "/MyServletRendreLocation.do");
    }

}
