package fr.afpa.controleur;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;

import fr.afpa.bean.Client;
import fr.afpa.controleur.conf.AbstractServletController;
import fr.afpa.service.IClientService;

/**
 * Servlet implementation class AjoutClient
 */
@WebServlet("/ajoutClient")
public class AjoutClientServlet extends AbstractServletController {
	private static final long serialVersionUID = 1L;

	@Autowired
	IClientService clientService;

	public AjoutClientServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	
		String message = "";

		String login = request.getParameter("login");
		String password = request.getParameter("password");
		String prenom = request.getParameter("nom").toLowerCase();
		String nom = request.getParameter("prenom").toLowerCase();
		Client c = new Client(login, password, prenom, nom);

		try {
			clientService.ajouterClient(c);
			System.out.println("client ajouté en bdd");
			HttpSession session = request.getSession();
			session.setAttribute("client", c);
			
		} catch (Exception e) {
			message = "Erreur lors de l'ajout";
			request.setAttribute("message", message);
			this.getServletContext().getRequestDispatcher("/WEB-INF/jsp/accueil.jsp").forward(request, response);
		}

		this.getServletContext().getRequestDispatcher("/WEB-INF/jsp/clientAjoute.jsp").forward(request, response);

	}

}
