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
 * Servlet permettant de récupérer les informations du chien à modifier et le
 * traitement de ces modifications.
 * 
 * @author Aurélien
 * @version 1.0
 *
 */
@WebServlet("/update.html")
public class UpdateServlet extends AbstractServletController {

	@Autowired
	IChienService chienService;
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// Récupération de l'IdChien provenant de la jsp liste-chien permettant de
		// récupérer le chien en question.
		String strId = request.getParameter("idChien");
		int vId = Integer.parseInt(strId);
		Chien c = chienService.selectById(vId);
		request.setAttribute("leChien", c);

		this.getServletContext().getRequestDispatcher("/WEB-INF/jsp/update.jsp").forward(request, response);
	}

	// Modifications des informations du chien sélectionné.
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// Récupération du client et de sa session.
		HttpSession session = request.getSession();
		Client c = (Client) session.getAttribute("client");
		String login = c.getLogin();

		// Récupération des nouvelles informations du chien via la jsp update.
		String strIdChien = request.getParameter("idChien").toLowerCase();
		int vIdChien = Integer.parseInt(strIdChien);
		String vNom = request.getParameter("nom").toLowerCase();
		String vRace = request.getParameter("race").toLowerCase();
		String vCouleur = request.getParameter("couleur").toLowerCase();
		String strAge = request.getParameter("age");
		String strPuce = request.getParameter("puce");

		// Vérification des nombres et de leur format.
		boolean parsableByte = chienService.parseByteTest(strAge);
		boolean parsableLong = chienService.parseLongTest(strPuce);

		// Vérification de la bonne saisie des données entrées par le client.
		if (!Pattern.matches("[\\D]+", vNom) || !Pattern.matches("[\\D]+", vRace)
				|| !Pattern.matches("[\\D]+", vCouleur) || parsableByte == false || parsableLong == false) {
			request.setAttribute("error", "Erreur de saisie");
			this.getServletContext().getRequestDispatcher("/WEB-INF/jsp/liste-chiens.jsp").forward(request, response);
		} else {

			byte vAge = Byte.parseByte(strAge);
			long vPuce = Long.parseLong(strPuce);
			Chien chien = new Chien(vIdChien, vNom, vRace, vCouleur, vAge, vPuce);

			// Mise à jour des informations du chien en BDD puis retour sur liste-chien.jsp

			chienService.updateChien(chien);

			List<Chien> listeChien = chienService.getListChienByClient(login);

			request.setAttribute("listeDeChiens", listeChien);
		}
		this.getServletContext().getRequestDispatcher("/WEB-INF/jsp/chienModifie.jsp").forward(request, response);

	}

}
