<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ page import="fr.afpa.bean.Chien"%>
	<%@ page import="java.util.List"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Vos chiens</title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
	integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm"
	crossorigin="anonymous">
</head>
<body>
<%-- 		<%@ include file="clientAjoute.jsp" %> --%>
	<div class="jumbotron">
		<div class="container">
		
		
			<h1>Liste de tous vos chiens</h1>
			<h3 class="text-danger">${error}</h3>
			<table class="table">
				<thead>
					<tr>
						<th>#</th>
						<th>Nom</th>
						<th>Race</th>
						<th>Couleur</th>
						<th>Age</th>
					</tr>
				</thead>
				<tbody>
					<% 
						List<Chien> listeChiens =(List<Chien>) request.getAttribute("listechiens");
					if(listeChiens != null){
					int cpt = 1;
					for (Chien c : listeChiens) {
					%>

					<tr
						class=<%=cpt % 2 == 0 ? "bg-light text-white" : "bg-info text-dark"%>>
						<td><%=cpt%></td>
						<td><%=c.getNom()%></td>
						<td><%=c.getRace()%></td>
						<td><%=c.getCouleur()%></td>
						<td><%=c.getAge()%></td>
						<td><a href="update?id=<%=c.getIdChien()%>" class="btn btn-warning" role="button">Update</a></td>
						<td><a href="deleteServlet?id=<%=c.getIdChien()%>" class="btn btn-danger" role="button">Delete</a></td>
					</tr>
					<%
						cpt++;
					}}
					%>
				</tbody>
			</table>

			<!-- Button trigger modal -->
			<button type="button" class="btn btn-primary" data-toggle="modal"
				data-target="#exampleModal">Ajouter un chien</button>

			<!-- Modal -->
			<div class="modal fade" id="exampleModal" tabindex="-1" role="dialog"
				aria-labelledby="exampleModalLabel" aria-hidden="true">
				<div class="modal-dialog" role="document">
					<div class="modal-content">
						<div class="modal-header">
							<h5 class="modal-title" id="exampleModalLabel">Ajout chien</h5>
							<button type="button" class="close" data-dismiss="modal"
								aria-label="Close">
								<span aria-hidden="true">&times;</span>
							</button>
						</div>
						<form id="modalForm" action="ajoutChien.do" method="post">
							<div class="modal-body">

								<div class="form-group">
									<input id="nom" type="text" name="nom" class="form-control"
										placeholder="Nom du chien">
								</div>
								<div class="form-group">
									<input id="race" type="text" name="race" class="form-control"
										placeholder="Race">
								</div>
								<div class="form-group">
									<input id="couleur" type="text" name="couleur"
										class="form-control" placeholder="Couleur du pelage">
								</div>

								<div class="form-group">
									<input id="age" type="number" name="age" class="form-control"
										placeholder="Age">
								</div>

								<!-- 								<div class="form-group"> -->
								<!-- 									<input id="date_naissance" type="date" name="date_naissance" -->
								<!-- 										class="form-control" placeholder="Date de naissance"> -->
								<!-- 								</div> -->

							</div>
							<div class="modal-footer">
								<button type="button" class="btn btn-secondary"
									data-dismiss="modal">Fermer</button>
								<button type="submit" class="btn btn-primary">Enregistrer</button>
							</div>
						</form>
					</div>
				</div>
			</div>

		</div>
	</div>


	<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"
		integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj"
		crossorigin="anonymous"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"
		integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo"
		crossorigin="anonymous"></script>
	<script
		src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"
		integrity="sha384-OgVRvuATP1z7JjHLkuOU7Xw704+h835Lr+6QL9UvYjZE3Ipu6Tp75j7Bh/kR0JKI"
		crossorigin="anonymous"></script>

</body>
</html>