package fr.afpa.controleur;

import java.io.IOException;
import java.util.List;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;

import fr.afpa.bean.Chien;
import fr.afpa.bean.Client;
import fr.afpa.controleur.conf.AbstractServletController;
import fr.afpa.service.IChienService;

/**
 * Cette servlet permet la création et l'implémentation d'un nouveau chien.
 * 
 * 
 * @author Aurélien
 * @version 1.0
 *
 */
@WebServlet(urlPatterns = ("/ajoutChien.do"))
public class AjoutChienServlet extends AbstractServletController {
	private static final long serialVersionUID = 1L;

	@Autowired
	IChienService chienService;

	/**
	 * Récupère les informations saisies dans la jsp liste-chien afin de créer un
	 * nouveau chien.
	 * 
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// récupération information du client et des informations du chien.
		HttpSession session = request.getSession();
		Client c = (Client) session.getAttribute("client");
		String login = c.getLogin();

		String vNom = request.getParameter("nom").toLowerCase();
		String vRace = request.getParameter("race").toLowerCase();
		String vCouleur = request.getParameter("couleur").toLowerCase();
		String strAge = request.getParameter("age");
		String strPuce = request.getParameter("puce");

		boolean parsableByte = chienService.parseByteTest(strAge);
		boolean parsableLong = chienService.parseLongTest(strPuce);

		// Vérifie la conformité des informations du chien
		if (!Pattern.matches("[\\D]+", vNom) || !Pattern.matches("[\\D]+", vRace)
				|| !Pattern.matches("[\\D]+", vCouleur) || parsableByte == false || parsableLong == false) {
			request.setAttribute("error", "Erreur de saisie");
			this.getServletContext().getRequestDispatcher("/WEB-INF/jsp/liste-chiens.jsp").forward(request, response);
		} else {
			boolean existe = false;
			byte vAge = Byte.parseByte(strAge);
			long vPuce = Long.parseLong(strPuce);
			Chien chien = new Chien(vNom, vRace, vCouleur, vAge, vPuce);

			List<Chien> listeChien = chienService.getListChienByClient(login);

			// Vérifie l'existence du chien dans la BDD associée à ce client.
			for (Chien vChien : listeChien) {
				if (vChien.getNom().equals(chien.getNom()) && vChien.getRace().equals(chien.getRace())
						&& vChien.getAge() == (chien.getAge()) || vChien.getPuce() == (chien.getPuce())) {
					existe = true;
				}
			}
			if (existe == true) {
				request.setAttribute("existe", "ce chien est déjà enregistré!");
				this.getServletContext().getRequestDispatcher("/WEB-INF/jsp/liste-chiens.jsp").forward(request,
						response);
				// Création et ajout de ce chien dans la BDD
			} else {
				chienService.ajouterChien(chien, login);
				chien = chienService.selectByName(vNom);

				listeChien.add(chien);

			}
			request.setAttribute("listeDeChiens", listeChien);
		}
		this.getServletContext().getRequestDispatcher("/WEB-INF/jsp/liste-chiens.jsp").forward(request, response);

	}

}
