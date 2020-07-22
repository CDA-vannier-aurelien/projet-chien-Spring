package fr.afpa.controleur;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import fr.afpa.service.IClientService;

@WebServlet(urlPatterns = ("/Ajout.do"))
public class AjoutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Autowired
	IClientService clientService;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.getServletContext().getRequestDispatcher("/jsp/ajout.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		WebApplicationContext context = WebApplicationContextUtils
				.getWebApplicationContext(request.getServletContext());
		clientService = context.getBean(IClientService.class);
//		if(request.getParameter("nom").matches("\\[a-zA-Z]+"))

		String nom = request.getParameter("nom").toLowerCase();
		String race = request.getParameter("race").toLowerCase();
		String couleur = request.getParameter("race").toLowerCase();
		String age = request.getParameter("date");
//		Chien p = new Personne(nom, race, couleur, age);			//TODO!!!!!!
//		clientService.ajouterChien(p);

		response.sendRedirect("index.html");
	}
}
