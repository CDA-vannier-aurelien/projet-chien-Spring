<nav class="navbar navbar-expand-sm bg-dark navbar-dark">
  <ul class="navbar-nav">
    <li class="nav-item">
      <p class="text-white">Bienvenue ! / </p>
    </li>
    <li class="nav-item">
     <p class="text-white"> Pr�nom : ${sessionScope.client.prenom} / </p>
    </li>
    <li class="nav-item">
     <p class="text-white"> Nom : ${sessionScope.client.nom} / </p>
    </li>
     <li class="nav-item">
    <a href="deconnexion" class="btn btn-danger" role="button">Log Out</a>
    </li>
  </ul>
</nav>


<!-- METTRE �a dans une navbar et afficher sur liste-chiens .jsp !! -->
<!-- <h1>Bienvenue !</h1> -->
<%-- <h3>Pr�nom : ${sessionScope.client.prenom}</h3> --%>
<%-- <h3>Nom : ${sessionScope.client.nom}</h3> --%>
<!-- <a href="ListeChien.do" class="btn btn-primary" role="button">Voir mes chiens</a> -->
<!-- <a href="" class="btn btn-danger" role="button">Log Out</a> -->
