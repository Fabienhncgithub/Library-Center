package controler;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Avis;
import model.Bibliotheque;
import model.Facade;
import model.Location;
import model.User;

public class MyServletHistorique extends HttpServlet {

    Facade facade = new Facade();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        User user = (User) request.getSession().getAttribute("user");
        Bibliotheque bibliotheque = (Bibliotheque) request.getSession().getAttribute("bibliotheque");
        List<Location> listeLocation = facade.getBiblitoheque().getAllLocationByIdUserIdBibliotheque(bibliotheque, user);
        request.getServletContext().setAttribute("listeLocation", listeLocation);
        Date date = new Date();
        request.setAttribute("date", date);
        request.setAttribute("user", user);
        request.getRequestDispatcher("historique.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        User user = (User) request.getSession().getAttribute("user");
        Bibliotheque bibliotheque = (Bibliotheque) request.getSession().getAttribute("bibliotheque");
        String titreLivreAvis = request.getParameter("titreLivreAvis");
        List<Location> listeLocation = facade.getBiblitoheque().getAllLocationByIdUserIdBibliotheque(bibliotheque, user);
        String errorMessage;
        
        
        if (facade.getLivre().getAvisByIdUSerIdLivreSelected(user, facade.getLivre().getLivreByNom(titreLivreAvis))) {
            errorMessage = "Vous avez déjà commenté ce livre";
            request.setAttribute("errorMessage", errorMessage);
            request.getServletContext().setAttribute("listeLocation", listeLocation);
            this.getServletContext().getRequestDispatcher("/historique.jsp").forward(request, response);
        } else {
            request.getSession().setAttribute("titreLivreAvis", titreLivreAvis);
            response.sendRedirect(request.getContextPath() + "/MyServletAvisNote.do");
        }
    }

}
