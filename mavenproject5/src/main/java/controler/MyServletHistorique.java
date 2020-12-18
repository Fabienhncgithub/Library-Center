package controler;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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

        request.setAttribute("listeLocation", listeLocation);
        request.setAttribute("user", user);
        request.getRequestDispatcher("historique.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String titreLivreAvis = request.getParameter("titreLivreAvis");
        request.getSession().setAttribute("titreLivreAvis", titreLivreAvis);
        response.sendRedirect(request.getContextPath() + "/MyServletAvisNote.do");

    }

}
