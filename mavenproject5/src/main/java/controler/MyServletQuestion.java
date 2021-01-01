package controler;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Facade;
import model.User;


public class MyServletQuestion extends HttpServlet {

  Facade facade = new Facade();

   


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
             request.getRequestDispatcher("question.jsp").forward(request, response);
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        User user = (User)request.getSession().getAttribute("user");
        String question = request.getParameter("question");
        
        
        facade.getUser().insertQuestion(question,user);
        
        
        
        
        
           response.sendRedirect(request.getContextPath() + "/MyServletQuestion");
        
    }



}
