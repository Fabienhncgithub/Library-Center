package controler;

import com.sun.xml.rpc.processor.modeler.j2ee.xml.paramValueType;
import java.io.IOException;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Bibliotheque;
import model.Facade;
import model.Location;
import model.User;

public class MyServletGestionLocation extends HttpServlet {

    Facade facade = new Facade();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        User user = (User) request.getSession().getAttribute("user");
        Bibliotheque bibliotheque = (Bibliotheque) request.getSession().getAttribute("bibliotheque");
        List<Location> listAllLocationBibliotheque = new ArrayList();
        listAllLocationBibliotheque = facade.getBiblitoheque().getAllLocationByBibliotheque(bibliotheque);

        Date currentDate = new Date();

        request.setAttribute("user", user);
        request.setAttribute("listAllLocationBibliotheque", listAllLocationBibliotheque);
        request.setAttribute("currentDate", currentDate);

        request.getRequestDispatcher("gestionLocation.jsp").forward(request, response);
        //response.sendRedirect(request.getContextPath() + "/MyServletGestionLocation.do");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int idExemplaireValider = Integer.parseInt(request.getParameter("idExemplaireValider"));
        facade.getBiblitoheque().validationRetour(idExemplaireValider);

        response.sendRedirect("MyServletGestionLocation.do");
    }

}
