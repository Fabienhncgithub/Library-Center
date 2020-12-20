/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controler;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Bibliotheque;
import model.Exemplaire;
import model.Facade;
import model.Livre;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

/**
 *
 * @author Fabien
 */
public class MyServletAddBook extends HttpServlet {

    Facade facade = new Facade();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        /* TODO Supprimer le type? rajouter un exemplaier si il existe pas sinon livre*/
        request.getRequestDispatcher("addBook.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Bibliotheque bibliotheque = (Bibliotheque) request.getSession().getAttribute("bibliotheque");

        Exemplaire exemplaire = new Exemplaire();
        Livre livre = new Livre();

        livre.setTitre(request.getParameter("titre"));
        livre.setAuteur(request.getParameter("auteur"));
        livre.setEditeur(request.getParameter("editeur"));
        livre.setPage(Integer.parseInt(request.getParameter("page")));
        livre.setPrixAchat(Integer.parseInt(request.getParameter("prix")));
        if (facade.getLivre().getLivreByNom("titre") != null) {
            livre.setIdLivre(facade.getLivre().getLivreByNom("titre").getIdLivre());
        }

        exemplaire.setLivre(livre);
        exemplaire.setType(request.getParameter("type"));

        facade.getUser().addBook(exemplaire, bibliotheque);

        if (!ServletFileUpload.isMultipartContent(request)) {
            throw new IllegalArgumentException("Request is not multipart, please 'multipart/form-data' enctype for your form.");
        }
    }
}

//String message;
//
//  
//     ServletFileUpload uploadHandler = new ServletFileUpload(new DiskFileItemFactory());
//     PrintWriter writer = response.getWriter();
//   
//  //  System.out.println(new File(request.getServletContext().getRealPath("/")+"images/"));
//     try {
//         List<FileItem> items = uploadHandler.parseRequest(request);
//         
//      
//         
//         for (FileItem item : items) {
//             if (!item.isFormField()) {
//                     File file = new File(request.getServletContext().getRealPath("/")+"images/", item.getName());
//                     item.write(file);
//                     
//                     System.out.println("uploaded");
//             }
//         }
//     } catch (FileUploadException e) {
//             throw new RuntimeException(e);
//     } catch (Exception e) {
//             throw new RuntimeException(e);
//     } finally {
//        
//         writer.close();
//     }
//         
//    }

