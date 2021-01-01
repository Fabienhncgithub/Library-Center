/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package WebService;

import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;

/**
 *
 * @author Fabien
 */
@WebService(serviceName = "WebService")
public class CentreService {

    /**
    http://localhost:8080/mavenproject5/WebService?wsdl
     */
    @WebMethod(operationName = "hellotest")
    public String hello(@WebParam(name = "name") String txt) {
        return "Hello " + txt + " !";
    }
}
