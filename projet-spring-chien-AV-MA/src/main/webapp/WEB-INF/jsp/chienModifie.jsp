<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>informations modifiées</title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
	integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm"
	crossorigin="anonymous">
</head>
<body>
<%@ include file="navbar.jsp" %>
<div class="jumbotron">
<div class="container">

<!-- METTRE �a dans une navbar et afficher sur liste-chiens .jsp !! -->
<h1> Les informations de votre chien ont bien été modifiées!</h1>

<a href="ListeChien.do" class="btn btn-primary" role="button">Voir mes chiens</a>
<a href="deconnexion" class="btn btn-danger" role="button">Log Out</a>
</div>
</div>
</body>
</html>