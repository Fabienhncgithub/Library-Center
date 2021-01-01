/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controler;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Bibliotheque;
import model.Exemplaire;
import model.Facade;
import model.Livre;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

@MultipartConfig(
        fileSizeThreshold = 1024 * 1024 * 1, // 1 MB
        maxFileSize = 1024 * 1024 * 10, // 10 MB
        maxRequestSize = 1024 * 1024 * 15 // 15 MB
)

public class MyServletAddBook extends HttpServlet {

    Facade facade = new Facade();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.getRequestDispatcher("addBook.jsp").forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Bibliotheque bibliotheque = (Bibliotheque) request.getSession().getAttribute("bibliotheque");

        if (!ServletFileUpload.isMultipartContent(request)) {
            throw new IllegalArgumentException("Request is not multipart, please 'multipart/form-data' enctype for your form.");
        }

        ServletFileUpload uploadHandler = new ServletFileUpload(new DiskFileItemFactory());
        PrintWriter writer = response.getWriter();

        try {
            List<FileItem> items = uploadHandler.parseRequest(request);
            String titre = "";
            String auteur = "";
            String editeur = "";
            String type = "ebook";
            String path = "";
            String prix = "";
            String page = "";
            for (FileItem item : items) {
                if (!item.isFormField()) {
                    String pathPdf = "C:\\Users\\Fabien\\Documents\\NetBeansProjects\\Office365-Java-Connect2\\mavenproject5\\src\\main\\webapp\\picture";
                    File file = new File(pathPdf, item.getName());
                    item.write(file);
                    path = file.getAbsolutePath();
                } else {
                    switch (item.getFieldName()) {
                        case "titre":
                            titre = item.getString();
                            break;
                        case "auteur":
                            auteur = item.getString();
                            break;
                        case "editeur":
                            editeur = item.getString();
                            break;
                        case "type":
                            type = item.getString();
                            break;
                        case "prix":
                            prix = item.getString();
                            break;
                        case "page":
                            page = item.getString();
                            break;
                    }
                }
            }

           // Exemplaire exemplaireLivre = new Exemplaire();
            Exemplaire exemplaireEbook = new Exemplaire();
            Livre livre = new Livre();

            livre.setTitre(titre);
            livre.setAuteur(auteur);
            livre.setEditeur(editeur);
            livre.setPage(Integer.parseInt(page));
            livre.setPrixAchat(Integer.parseInt(prix));

//            exemplaireLivre.setLivre(livre);
//            exemplaireLivre.setType(type);
//            exemplaireLivre.setPath("");

            exemplaireEbook.setLivre(livre);
            exemplaireEbook.setType(type);
            exemplaireEbook.setPath(path);

            //facade.getUser().addBook(exemplaireEbook, bibliotheque);
//            if (type.equals("livre")) {
//                facade.getUser().addBook(exemplaireLivre, bibliotheque);
//                System.out.println(exemplaireLivre);
//            } else {
                facade.getBiblitoheque().addBook(exemplaireEbook, bibliotheque);
                System.out.println(exemplaireEbook);
            

        } catch (FileUploadException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {

            writer.close();
            response.sendRedirect(request.getContextPath() + "/MyServletAddbook.do");
        }
    }
}
