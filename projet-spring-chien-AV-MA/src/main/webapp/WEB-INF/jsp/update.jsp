<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="fr.afpa.bean.Chien"%>
   
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Page 1</title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
	integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm"
	crossorigin="anonymous">
</head>
<body>
<%@ include file="navbar.jsp" %>
<div class="jumbotron">
<div class="container"><div class="container">
	<a href="ListeChien.do">
		<button type="button" class="btn btn-primary btn-sm">Affichage de la liste</button>
	</a>
		<form action="update.html" method="post">
			<div class="row row-cols-3 align-items-center "  style = "background-color: white">
			<div class="col">
					<label for="formGroupExampleInput">id</label> 
					<input type="number" class="form-control" id="formGroupExampleInput1"
						name="idChien" value="${leChien.idChien}" readonly>
				</div>
				<div class="col">
					<label for="formGroupExampleInput">Nom</label> <input
						type="text" class="form-control" id="formGroupExampleInput2"
						name="nom" value="${leChien.nom}" required>
				</div>
				<div class="col">
					<label for="formGroupExampleInput2">Race</label>
					<input type="text" class="form-control" id="formGroupExampleInput3"
						name="race" value="${leChien.race}" required>
				</div>
				<div class="col">
					<label for="formGroupExampleInput2">Couleur</label>
					<input type="text" class="form-control" id="formGroupExampleInput4"
						name="couleur" value="${leChien.couleur}">
				</div>
				<div class="col">
					<label for="formGroupExampleInput2"> Age</label> 
					<input type="number" class="form-control" id="formGroupExampleInput5"
						name="age" value="${leChien.age}" min =1 max =30 required >
				</div>
				<div class="col">
					<label for="formGroupExampleInput2"> N° Puce</label> 
					<input type="number" class="form-control" id="formGroupExampleInput6"
						name="puce" value="${leChien.puce}" min =1  required >
				</div>
				<button type="submit" id ="submit" class="btn btn-outline-secondary">envoyer</button>
			</div>
		</form>
			<h2 style ="color : red" >
	
${error}

</h2>
	</div>

	<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
		integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
		crossorigin="anonymous"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"
		integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1"
		crossorigin="anonymous"></script>
	<script
		src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"
		integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
		crossorigin="anonymous"></script>
</div>
</div>
</body>
</html>