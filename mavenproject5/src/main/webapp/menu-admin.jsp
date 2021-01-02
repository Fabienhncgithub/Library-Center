<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>


<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="bootstrap/js/jquery.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
<script src="bootstrap/js/bootstrap.js"></script>   
<link href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css" rel="stylesheet">

<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-giJF6kkoqNQ00vy+HMDP7azOuL0xtbfIcaT9wjKHr8RbDVddVHyTfAAsrekwKmP1" crossorigin="anonymous">

<nav id="menu">
    <div class="left">
        <a href="MyServletLivre.do"> <img src="picture/book1.png" alt="logo"/> Louer</a>
        <a href="MyServletHistorique.do"><img src="picture/calendar.png" alt="logo"/>Historique</a>
        <a href='MyServletCotisation.do'><img src="picture/stock-exchange-app.png" alt="logo"/>Cotisation</a>
        <a href='question.jsp'><img src="picture/mail-inbox-app.png" alt="logo"/>Contact</a>
        <a href='MyServletMessagerie.do'><img src="picture/speech-bubble.png" alt="logo"/>Messagerie</a>
        <a href='MyServletCentreService.do'><img src="picture/speech-bubble.png" alt="logo"/>Moyave sur le vieux port</a>


        <li class="nav-item dropdown">
            <a class=" dropdown-toggle"  id="navbardrop" data-toggle="dropdown">
                Add book
            </a>
            <div class="dropdown-menu">
                <a class="dropdown-item"  href='MyServletAddLivre.do'>ajouter un livre</a>
                <a class="dropdown-item"  href='MyServletAddBook.do'>ajouter un ebook</a>

            </div>
        </li>
        <li class="nav-item dropdown">
            <a class="dropdown-toggle"  id="navbardrop" data-toggle="dropdown">
                Menu Admin
            </a>
            <div class="dropdown-menu">
                <a class="dropdown-item"  href='MyServletGestionLocation.do'>Gestion location</a>
                <a class="dropdown-item" href='MyServletManageManager.do'>Add profil</a>
                <a class="dropdown-item" href='MyServletManageBibliotheque.do'>Add bibliothèque</a>
            </div>
        </li>
        <div class="right">
            <a href='MyServletModifyProfil.do'><img src="picture/user.png" alt="logo"/>Profil de ${user.prenom}</a>
            <a href='MyServletDisconect.do' id="signout"><img src="picture/exit.png"/>Se déconnecter <!-- de la bibliothèque de ${bibliotheque.nom} --></a>
        </div>
</nav>


