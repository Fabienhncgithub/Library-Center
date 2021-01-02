
package controler;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import model.Facade;
import pkg.CentreServiceInterface;

/**
 *
 * @author Fabien
 */
public class MyServletCentreService extends HttpServlet {

Facade facade = new Facade();
       

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        URL wsdURL = new URL("http://localhost:8080/mavenproject5/CentreServiceImplService?wsdl");
        QName qn = new QName("http://pkg/","CentreServiceImplService");
        Service service = Service.create(wsdURL, qn);
        CentreServiceInterface csi = service.getPort(CentreServiceInterface.class);
        String titre = "harry";
        String res = csi.searchBookTitle(titre);
        Map<String, String> listLivre = facade.getCentre().convertionString(res);
        request.setAttribute("listLivre", listLivre);
        System.out.println("MEC C ICI !!!!!!!!!!!!!!!!!!!!!!!!!!");
        System.out.println(listLivre);
        request.getServletContext().getRequestDispatcher("/test.jsp").forward(request, response);
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }


}
