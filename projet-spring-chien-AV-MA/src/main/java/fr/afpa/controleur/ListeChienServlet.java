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

import fr.afpa.bean.Chien;
import fr.afpa.service.IChienService;

/**
 * Servlet implementation class ListeChienServlet
 */
@WebServlet(urlPatterns = ("/ListeChien.do"))
public class ListeChienServlet extends HttpServlet {
	@Autowired
	private IChienService chienService;

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		List<Chien> personnes = chienService.getList();

		request.setAttribute("personnes", personnes);
		this.getServletContext().getRequestDispatcher("/jsp/affichageListeChien.jsp").forward(request, response);
		// Cette Jsp est à modifier en fonction du nom donné.
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String vNom = request.getParameter("nom").toUpperCase().trim();
		String vRace = request.getParameter("race");
		String vCouleur = request.getParameter("couleur");
		String strAge = request.getParameter("age");
		boolean parsable = parsableTest(strAge);

		if (!Pattern.matches("[\\D]+", vNom) || !Pattern.matches("[\\D]+", vRace)
				|| !Pattern.matches("[\\D]+", vCouleur) || parsable == false) {

			request.setAttribute("error", "Erreur de saisie");
			this.getServletContext().getRequestDispatcher("/WEB-INF/saisie.jsp").forward(request, response);
		} else {

			boolean existe = false;
			Byte vAge = Byte.parseByte(strAge);
			Chien c = new Chien(vNom, vRace, vCouleur, vAge);
			List<Chien> listeChien = chienService.getList();
			for (Chien vChien : listeChien) {
				if (vChien.getNom().equals(c.getNom()) && vChien.getRace().equals(c.getRace())
						&& vChien.getAge() == (c.getAge())) {
					existe = true;
				}
			}
			if (existe == true) {
				request.setAttribute("existe", "ce chien est déjà enregistrée!");
				this.getServletContext().getRequestDispatcher("/WEB-INF/ajoutChien.jsp").forward(request, response);
			} else {
				chienService.ajouterChien(c);
				;
				listeChien = chienService.getList();
			}
			request.setAttribute("perso", listeChien);
		}
		this.getServletContext().getRequestDispatcher("/WEB-INF/affichageListeChien.jsp").forward(request, response);
	}

	private boolean parsableTest(String strAge) {
		boolean bool = false;
		try {
			byte res = Byte.parseByte(strAge);
			bool = true;
		} catch (Exception e) {
			bool = false;
		}
		return bool;

	}
}