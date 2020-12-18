package controler;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Avis;
import model.Bibliotheque;
import model.Facade;
import model.User;

public class MyServletConsulterAvis extends HttpServlet {

    Facade facade = new Facade();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Bibliotheque bibliotheque = (Bibliotheque) request.getSession().getAttribute("bibliotheque");
        User user = (User) request.getSession().getAttribute("user");
        int idLivreSelected = Integer.parseInt(request.getParameter("idLivreSelected"));

        List<Avis> listeAvis = facade.getBiblitoheque().getAllAvisbyIdLivre(idLivreSelected);

        request.setAttribute("listeAvis", listeAvis);
        request.getRequestDispatcher("avisLivre.jsp").forward(request, response);

    }

}
