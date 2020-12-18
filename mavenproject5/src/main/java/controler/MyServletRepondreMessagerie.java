package controler;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Facade;
import model.User;

public class MyServletRepondreMessagerie extends HttpServlet {

    Facade facade = new Facade();



    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        User user = (User) request.getSession().getAttribute("user");
        String reponse = request.getParameter("reponse");
        int idQuestion = Integer.parseInt(request.getParameter("idQuestion"));

        request.setAttribute("user", user);
        request.setAttribute("reponse", reponse);

        facade.getBiblitoheque().reponseQuestion(user, reponse, idQuestion);

        //   facade.getRequestDispatcher("test.jsp").forward(request, response);
    }

}
