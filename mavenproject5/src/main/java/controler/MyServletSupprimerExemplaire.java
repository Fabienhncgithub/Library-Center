package controler;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Facade;
import model.Location;

public class MyServletSupprimerExemplaire extends HttpServlet {

    Facade facade = new Facade();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int idLocationSupprimer = Integer.parseInt(request.getParameter("idExemplaireSupprimer"));
        Location location  = facade.getLivre().getLocationById(idLocationSupprimer);
        
        facade.getBiblitoheque().supprimerLocation(location);
        response.sendRedirect(request.getContextPath() + "/MyServletGestionLocation.do");
    }

}
