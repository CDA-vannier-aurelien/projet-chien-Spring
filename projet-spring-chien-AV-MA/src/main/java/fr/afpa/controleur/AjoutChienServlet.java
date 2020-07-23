package fr.afpa.controleur;

import java.io.IOException;
import java.util.List;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import fr.afpa.bean.Chien;
import fr.afpa.service.IChienService;

@WebServlet(urlPatterns = ("/AjoutChien.do"))
public class AjoutChienServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Autowired
	IChienService chienService;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.getServletContext().getRequestDispatcher("/WEB-INF/jsp/ajoutChien.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		WebApplicationContext context = WebApplicationContextUtils
				.getWebApplicationContext(request.getServletContext());
		chienService = context.getBean(IChienService.class);
//		if(request.getParameter("nom").matches("\\[a-zA-Z]+"))

		// ajout session client

		String vNom = request.getParameter("nom").toLowerCase();
		String vRace = request.getParameter("race").toLowerCase();
		String vCouleur = request.getParameter("race").toLowerCase();
		String strAge = request.getParameter("date");
		boolean parsable = parseTest(strAge);
		if (!Pattern.matches("[\\D]+", vNom) || !Pattern.matches("[\\D]+", vRace)
				|| !Pattern.matches("[\\D]+", vCouleur) || parsable == false) {
			request.setAttribute("error", "Erreur de saisie");
			this.getServletContext().getRequestDispatcher("/WEB-INF/jsp/ajoutChien.jsp").forward(request, response);
		} else {
			boolean existe = false;
			byte vAge = Byte.parseByte(strAge);
			Chien chien = new Chien(vNom, vRace, vCouleur, vAge);
			List<Chien> listeChien = chienService.getList();
			for (Chien vChien : listeChien) {
				if (vChien.getNom().equals(chien.getNom()) && vChien.getRace().equals(chien.getRace())
						&& vChien.getAge() == (chien.getAge())) {
					existe = true;
				}
			}
			if (existe == true) {
				request.setAttribute("existe", "ce chien est déjà enregistré!");
				this.getServletContext().getRequestDispatcher("/WEB-INF/jsp/ajoutChien.jsp").forward(request, response);
			} else {
				chienService.ajouterChien(chien);

			}
			request.setAttribute("perso", listeChien);
		}
		this.getServletContext().getRequestDispatcher("/WEB-INF/jsp/affichageListeChien.jsp").forward(request, response);

	}

	private boolean parseTest(String strAge) {
		boolean res = false;
		try {
			byte age = Byte.parseByte(strAge);
			res = true;
		} catch (Exception e) {
			res = false;
		}
		return res;
	}
}
