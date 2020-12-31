<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-giJF6kkoqNQ00vy+HMDP7azOuL0xtbfIcaT9wjKHr8RbDVddVHyTfAAsrekwKmP1" crossorigin="anonymous">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link href="css/style.css" rel="stylesheet" type="text/css">
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <jsp:include page="menu-admin.jsp"/>
        <script src="https://rawgit.com/enyo/dropzone/master/dist/dropzone.js"></script>
        <script src="https://rawgit.com/enyo/dropzone/master/dist/dropzone.css"></script>

        <script>
            Dropzone.options.mydropzone = {
                // url does not has to be written if we have wrote action in the form tag but i have mentioned here just for convenience sake 
                addRemoveLinks: true,
                autoProcessQueue: false, // this is important as you dont want form to be submitted unless you have clicked the submit button
                autoDiscover: false,
                paramName: 'file', // this is similar to giving name to the input type file like <input type="file" name="pic" />
                previewsContainer: '#dropzonePreview', // we specify on which div id we must show the files
                clickable: false, // this tells that the dropzone will not be clickable . we have to do it because v dont want the whole form to be clickable 
                accept: function (file, done) {
                    console.log("uploaded");
                    done();
                },
                error: function (file, msg) {
                    alert(msg);
                },
                init: function () {

                    var myDropzone = this;
                    //now we will submit the form when the button is clicked
                    document.getElementById("subtt").onclick = function (e) {
                        e.preventDefault(); //this will prevent the default behaviour of submit button because we want dropzone to submit the form
                        myDropzone.processQueue(); // this will submit your form to the specified action which directs to your jsp upload code
                        // after this, your whole form will get submitted with all the inputs + your files and the jsp code will remain as usual 
                        //REMEMBER you DON'T have to call ajax or anything by yourself to submit the inputs, dropzone will take care of that
                    };

                } // init end

            };
        </script>


        <link rel="stylesheet" href="css/dropzone.css">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Rajouter un Ebook </h1>
        <form id="mydropzone"  action="MyServletAddBook.do" class="dropzone" enctype="multipart/form-data" method="POST" >

            <label for="titre">  Titre: </label><input type="text" name="titre" required/><br/>
            <label for="auteur"> Auteur:</label><input type="text" name="auteur" required/><br/>
            <label for="editeur">  Editeur: </label><input type="text" name="editeur" required/><br/>
            <label for="page"> Page Total:</label><input type="number" name="page" required/><br/>
            <label for="prix">Prix d'achat: </label><input type="text" name="prix" required/><br/>
            <div>
             
            </div>
            <div id = "dropzonePreview">
                <input type="file" value="file"/>
            </div>

            <input type="submit"  id="subtt" value=" Ajouter "/>
        </form>
    </body> 

</html>