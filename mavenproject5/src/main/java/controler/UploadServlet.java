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
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

public class UploadServlet extends HttpServlet {

//    /*
//     * Chemin dans lequel les images seront sauvegardées.
//     */
//    public static final String IMAGES_FOLDER = "/Images";
//
//    public String uploadPath;
//
//    /*
//     * Si le dossier de sauvegarde de l'image n'existe pas, on demande sa création.
//     */
//    @Override
//    public void init() throws ServletException {
//        uploadPath = getServletContext().getRealPath(IMAGES_FOLDER);
//        File uploadDir = new File(uploadPath);
//        if (!uploadDir.exists()) {
//            uploadDir.mkdir();
//        }
//    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        if (!ServletFileUpload.isMultipartContent(request)) {
            throw new IllegalArgumentException("Request is not multipart, please 'multipart/form-data' enctype for your form.");
        }

        ServletFileUpload uploadHandler = new ServletFileUpload(new DiskFileItemFactory());
        PrintWriter writer = response.getWriter();


        try {
            List<FileItem> items = uploadHandler.parseRequest(request);

            for (FileItem item : items) {
                if (!item.isFormField()) {
                    File file = new File(request.getServletContext().getRealPath("/"), item.getName());
                    item.write(file);
                }
            }
        } catch (FileUploadException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            writer.close();
        }
    }
}
