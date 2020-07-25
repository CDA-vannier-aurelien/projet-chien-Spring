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
<%@ include file="navbar.jsp" %>
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
						<th>N° Puce</th>
						<th>IDChien</th>
					</tr>
				</thead>
				<tbody>
					<% 
						List<Chien> listeChiens =(List<Chien>) request.getAttribute("listeDeChiens");
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
						<td><%=c.getPuce()%></td>
						<td><%=c.getIdChien() %></td>
						</td>
				<td class="celluleBouton">
				<form action="update.html" method="get">
				 <input type="hidden" name="idChien" value="<%=c.getIdChien() %>"></input>
				<button class="btn btn-warning">Update</button>	
				</form>
				</td>
						<td class="celluleBouton">
				 <form action="delete.html" method="post">
				 <input type="hidden" name="idChien" value="<%=c.getIdChien()%>"></input>
				 <button type="button" class="btn btn-danger" data-toggle="modal" data-target="#deleteModal<%=c.getIdChien()  %>">
				 
  Supprimer
</button>
<!-- 				<button class="btn btn-outline-danger">Supprimer</button>	 -->
				</form>
				<!-- Modal -->
<div class="modal fade" id="deleteModal<%=c.getIdChien() %>" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel<%=c.getIdChien() %>" aria-hidden="true">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="exampleModalLabel<%=c.getIdChien()  %>">Confirmation de suppression</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body">
       Souhaitez vous vraiment supprimer ce chien?
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-dismiss="modal">Annuler</button>
        <form action="delete.html"  method="post">
        <input type="hidden" name="idChien" value="<%=c.getIdChien() %>"></input>
        <button type="submit" class="btn btn-primary">Continuer</button>
        </form>
      </div>
    </div>
  </div>
</div>
				</td>
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
										placeholder="Nom du chien" required>
								</div>
								<div class="form-group">
									<input id="race" type="text" name="race" class="form-control"
										placeholder="Race" required>
								</div>
								<div class="form-group">
									<input id="couleur" type="text" name="couleur"
										class="form-control" placeholder="Couleur du pelage" required>
								</div>

								<div class="form-group">
									<input id="age" type="number" name="age" class="form-control"
										placeholder="Age" min="1" max="30" required>
								</div>
								<div class="form-group">
									<input id="puce" type="number" name="puce" class="form-control"
										placeholder="N° Puce" min="1"  required>
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