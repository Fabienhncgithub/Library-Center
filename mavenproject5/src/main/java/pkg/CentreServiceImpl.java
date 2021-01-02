/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg;

import javax.jws.WebService;
import model.Centre;


@WebService(endpointInterface = "pkg.CentreServiceInterface")
public class CentreServiceImpl implements CentreServiceInterface{

    


    @Override
    public String searchBookTitle(String titre) {
              Centre centre = new Centre();      
        return centre.searchBook(titre).toString();
    }
}
