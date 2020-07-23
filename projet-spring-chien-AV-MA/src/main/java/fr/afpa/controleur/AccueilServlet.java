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
		// Récup infos + création session + association
		Client c = null;
		String error = "";

		String login = request.getParameter("login");
		String password = request.getParameter("password");

		// Check si Login est présent en bdd ,error first 
		if (!clientService.checkSiExisteBDD(login)) {
			error = "Login inconnu";
			request.setAttribute("error", error);
			this.getServletContext().getRequestDispatcher("/WEB-INF/jsp/error.jsp").forward(request, response);
		} else {
			c = clientService.selectByLogin(login);
		}

		// Login ok, Check si le password correspond au login ,error first
		if (!password.equals(c.getPassword())) {
			error = "Login/password invalide";
			request.setAttribute("error", error);
			this.getServletContext().getRequestDispatcher("/WEB-INF/jsp/error.jsp").forward(request, response);
		} else {
			HttpSession session = request.getSession();
			session.setAttribute("client", c);
			this.getServletContext().getRequestDispatcher("/WEB-INF/jsp/clientAjoute.jsp").forward(request, response);
		}
	}
}
