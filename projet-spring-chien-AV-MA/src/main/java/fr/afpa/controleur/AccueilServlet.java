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
 * Servlet implementation class TestServlet
 */
@WebServlet(urlPatterns = { "/accueil" })
public class AccueilServlet extends AbstractServletController {
	private static final long serialVersionUID = 1L;
	@Autowired
	IClientService clientService;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.getServletContext().getRequestDispatcher("/WEB-INF/jsp/accueil.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Récup infos + création session + assosciation
		String message = "";

		String login = request.getParameter("login");
		Client c = null;
		String password = request.getParameter("password");

		// Test si présent en bdd
		try {
			// On récupère la personne avec login
			c = clientService.selectByLogin(login);
		} catch (Exception e) {
			message = "Login et/ou password non valide";
			request.setAttribute("message", message);
			this.getServletContext().getRequestDispatcher("/WEB-INF/jsp/accueil.jsp").forward(request, response);
		}

		if (c.getPassword().equals(password)) {
			HttpSession session = request.getSession();
			session.setAttribute("client", c);
			message = "Connexion ok";
			request.setAttribute("message", message);
			this.getServletContext().getRequestDispatcher("/WEB-INF/jsp/liste-chiens.jsp").forward(request, response);
		} else {
			message = "Login et/ou password non valide (wouaf)";
			request.setAttribute("message", message);
			this.getServletContext().getRequestDispatcher("/WEB-INF/jsp/accueil.jsp").forward(request, response);

		}
		// Récup du client avec id

		// Création session

		// Si login et password ok Création session --> renvoie sur liste chiens

		// Sinon --> renvoi vers AjouterClientServlet

	}

}
