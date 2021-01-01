<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-giJF6kkoqNQ00vy+HMDP7azOuL0xtbfIcaT9wjKHr8RbDVddVHyTfAAsrekwKmP1" crossorigin="anonymous">

<nav id="menu">
    
    <div class="left">
   
        <a href="MyServletLivre.do"> <img src="picture/book1.png" alt="logo"/> Louer</a>
        <a href="MyServletHistorique.do"><img src="picture/calendar.png" alt="logo"/>Mes réservations</a>
        <a href='MyServletCotisation.do'><img src="picture/stock-exchange-app.png" alt="logo"/>Payer cotisation</a>
        <a href='question.jsp'><img src="picture/mail-inbox-app.png" alt="logo"/>Contact</a>
        <a href='MyServletMessagerie.do'><img src="picture/speech-bubble.png" alt="logo"/>Messagerie</a>
    </div>
            
    <div class="right">
        <a href='MyServletModifyProfil.do'><img src="picture/user.png" alt="logo"/>Profil de ${user.prenom}</a>
        <a href='MyServletDisconect.do' id="signout"><span class="glyphicon glyphicon-log-out"></span><img src="picture/exit.png" alt="logo"/>Se déconnecter <!-- de la bibliothèque de ${bibliotheque.nom} --></a>
    </div>
    
</nav>