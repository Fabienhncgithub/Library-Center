<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-giJF6kkoqNQ00vy+HMDP7azOuL0xtbfIcaT9wjKHr8RbDVddVHyTfAAsrekwKmP1" crossorigin="anonymous">

<nav id="menu">        
    <div class="element_menu">
        <ul>
            <li><a href="MyServletLivre.do">Louer</a></li>
            <li><a href="MyServletHistorique.do">Mes réservations</a></li>
            <li><a href='MyServletCotisation.do'>Payer cotisation</a></li>
            <li><a href='question.jsp'>Contact</a></li>
            <li><a href='MyServletMessagerie.do'>Messagerie</a></li>
            <li><a href='MyServletModifyProfil.do'>Profil de ${user.prenom}</a></li>
            <li><a href='MyServletDisconect.do'><span class="glyphicon glyphicon-log-out"></span>Se déconnecter de la bibliothèque de ${bibliotheque.nom}</a></li>
            
        </ul>
    </div>
</nav>