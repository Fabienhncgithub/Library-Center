package controler;

import com.mysql.cj.jdbc.interceptors.SessionAssociationInterceptor;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Facade;
import model.User;

public class MyServletPayerAmende extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        User user = (User) request.getSession().getAttribute("user");
        user.payerAmende();
        user.setAmende(0);

        String errorMessage = "vous avez payez l'amende";

        request.setAttribute("errorMessage", errorMessage);
       // this.getServletContext().getRequestDispatcher("/menuClient.jsp").forward(request, response);
        response.sendRedirect(request.getContextPath() + "/MyServletLivre.do");

    }

}
