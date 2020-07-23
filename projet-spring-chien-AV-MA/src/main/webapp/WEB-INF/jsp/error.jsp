<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Whoops !</title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
	integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm"
	crossorigin="anonymous">
</head>
<body>
	<div class="jumbotron">
		<div class="container">
			<h1>Hmm... c'est embarrasant !</h1>
			<h2 class="text-warning">${error}</h2>
			<br>
			<a href="accueil" class="btn btn-primary" role="button">Retour accueil</a>
		</div>
	</div>
</body>
</html>