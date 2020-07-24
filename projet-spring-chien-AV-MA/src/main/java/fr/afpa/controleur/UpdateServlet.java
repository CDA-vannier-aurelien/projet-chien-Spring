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

@WebServlet("/update.html")
public class UpdateServlet extends AbstractServletController {
	@Autowired
	IChienService chienService;
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String strId = request.getParameter("idChien");
		int vId = Integer.parseInt(strId);
		Chien c = chienService.selectById(vId);
		request.setAttribute("leChien", c);

		this.getServletContext().getRequestDispatcher("/WEB-INF/jsp/update.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		Client c = (Client) session.getAttribute("client");
		String login = c.getLogin();

		String strIdChien = request.getParameter("idChien").toLowerCase();
		int vIdChien = Integer.parseInt(strIdChien);
		String vNom = request.getParameter("nom").toLowerCase();
		String vRace = request.getParameter("race").toLowerCase();
		String vCouleur = request.getParameter("couleur").toLowerCase();
		String strAge = request.getParameter("age");
		String strPuce = request.getParameter("puce");

		boolean parsableByte = chienService.parseByteTest(strAge);
		boolean parsableLong = chienService.parseLongTest(strPuce);
		if (!Pattern.matches("[\\D]+", vNom) || !Pattern.matches("[\\D]+", vRace)
				|| !Pattern.matches("[\\D]+", vCouleur) || parsableByte == false || parsableLong == false) {
			request.setAttribute("error", "Erreur de saisie");
			this.getServletContext().getRequestDispatcher("/WEB-INF/jsp/liste-chiens.jsp").forward(request, response);
		} else {

			byte vAge = Byte.parseByte(strAge);
			long vPuce = Long.parseLong(strPuce);
			Chien chien = new Chien(vIdChien, vNom, vRace, vCouleur, vAge, vPuce);

			chienService.updateChien(chien);

			List<Chien> listeChien = chienService.getListChienByClient(login);

			request.setAttribute("listeDeChiens", listeChien);
		}
		this.getServletContext().getRequestDispatcher("/WEB-INF/jsp/chienModifie.jsp").forward(request, response);

	}

}
