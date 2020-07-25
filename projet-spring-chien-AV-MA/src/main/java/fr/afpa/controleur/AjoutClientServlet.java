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
import util.BCrypt;

/**
 * @author Mathieu
 * @version Servlet pour l'ajout d'un nouveau client.
 */
@WebServlet("/ajoutClient")
public class AjoutClientServlet extends AbstractServletController {
	private static final long serialVersionUID = 1L;

	@Autowired
	IClientService clientService;

	public AjoutClientServlet() {
		super();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Client c;
		String error = "";

		String login = request.getParameter("login");
		String password = request.getParameter("password");
		String prenom = request.getParameter("prenom");
		String nom = request.getParameter("nom");

		// Hashage du password
		String passwordHash = BCrypt.hashpw(password, BCrypt.gensalt());

		// Check si login est déjà pris, error first
		if (clientService.checkSiExisteBDD(login)) {
			error = "Login déjà utilisé !";
			request.setAttribute("error", error);
			this.getServletContext().getRequestDispatcher("/WEB-INF/jsp/error.jsp").forward(request, response);
		} else {
			// Ok pour construction
			c = new Client(login, passwordHash, prenom, nom);

			// On ajoute en bdd et on construit la session
			try {
				clientService.ajouterClient(c);
				HttpSession session = request.getSession();
				session.setAttribute("client", c);
			} catch (Exception e) {
				error = "Erreur lors de l'ajout";
				request.setAttribute("message", error);
				this.getServletContext().getRequestDispatcher("/WEB-INF/jsp/error.jsp").forward(request, response);
			}
		}
		this.getServletContext().getRequestDispatcher("/WEB-INF/jsp/clientAjoute.jsp").forward(request, response);
	}
}
