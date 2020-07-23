<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Page 1</title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
	integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm"
	crossorigin="anonymous">
</head>
<body>
<div class="jumbotron">
<div class="container">

<!-- METTRE ça dans une navbar et afficher sur liste-chiens .jsp !! -->
<h1>Bienvenue !</h1>
<h3>Prénom : ${sessionScope.client.prenom}</h3>
<h3>Nom : ${sessionScope.client.nom}</h3>
<a href="ListeChien.do" class="btn btn-primary" role="button">Voir mes chiens</a>
<a href="" class="btn btn-danger" role="button">Log Out</a>
</div>
</div>
</body>
</html>