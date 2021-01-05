/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controler;

import java.io.IOException;
import java.util.Random;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Exemplaire;
import model.Facade;
import model.User;

/**
 *
 * @author Fabien
 */
public class MyServletenregistrerPage extends HttpServlet {

    Facade facade = new Facade();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int  idLocation =  Integer.parseInt(request.getParameter("pageSelect"));
        int nbPage = Integer.parseInt(request.getParameter("pageTotal"));
        
        
        
        
        Random random = new Random();
        int pageSelect = random.nextInt(nbPage - 1) + 1;
        

        
        
        facade.getLivre().registerPage(pageSelect, idLocation);
        
        response.sendRedirect(request.getContextPath() + "/MyServletHistorique.do");
        
        
        
        

    }

}
