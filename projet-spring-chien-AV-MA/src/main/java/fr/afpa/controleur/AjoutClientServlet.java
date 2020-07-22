package fr.afpa.controleur;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import fr.afpa.bean.Chien;
import fr.afpa.bean.Client;
import fr.afpa.controleur.conf.AbstractServletController;
import fr.afpa.service.IClientService;

/**
 * Servlet implementation class AjoutClient
 */
@WebServlet("/AjoutClient")
public class AjoutClientServlet extends AbstractServletController {
	private static final long serialVersionUID = 1L;
      
	@Autowired
	IClientService clientService;
    public AjoutClientServlet() {
        super();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.getServletContext().getRequestDispatcher("/jsp/ajout-client.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		WebApplicationContext context = WebApplicationContextUtils
				.getWebApplicationContext(request.getServletContext());
		clientService = context.getBean(IClientService.class);
		
		String login = request.getParameter("login");
		String password = request.getParameter("password");
		String prenom = request.getParameter("nom").toLowerCase();
		String nom = request.getParameter("prenom").toLowerCase();
		Client c = new Client(login, password, prenom , nom , new ArrayList<Chien>());
		clientService.ajouterClient(c);

		response.sendRedirect("index.html");
		
	}

}
