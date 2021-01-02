package controler;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Facade;
import model.User;

public class MyServletSearch extends HttpServlet {
    Facade facade = new Facade();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        User user = (User) request.getSession().getAttribute("user");
        String search = request.getParameter("search");
       Map<String, Integer>searchResult = facade.getCentre().searchBook(search);
        request.setAttribute("searchResult", searchResult);
        request.getRequestDispatcher("resultatRecherche.jsp").forward(request, response);
    }

}
