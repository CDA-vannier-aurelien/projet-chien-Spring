package fr.afpa.controleur;

import java.io.IOException;
import java.util.List;

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
 * Permet d'afficher les chiens en rapport avec le client dont la session est
 * ouverte. Permet également de faire appel à l'ajout d'un nouveau chien, le
 * supprimer ou modifier ses informations via la jsp liste-chien.
 * 
 * @author Aurélien
 * @version 1.0
 * 
 *          Servlet implementation class ListeChienServlet
 */
@WebServlet(urlPatterns = ("/ListeChien.do"))
public class ListeChienServlet extends AbstractServletController {
	@Autowired
	private IChienService chienService;

	private static final long serialVersionUID = 1L;

	/**
	 * 
	 * Affichage de la liste des chiens du client.
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// Récupération de la session du client.
		HttpSession session = request.getSession();

		// test si la session a expirée --> renvoie à la page d'acceuil pour log
		if (session.getAttribute("client") == null) {
			request.setAttribute("error", "session expirée veuillez vous reconnecter");
			this.getServletContext().getRequestDispatcher("/WEB-INF/jsp/accueil.jsp").forward(request, response);
		} else {

			Client c = ((Client) session.getAttribute("client"));

			// Récupération de la liste de chien par le biais du login du client.
			List<Chien> listeDeChiens = chienService.getListChienByClient(c.getLogin());
			request.setAttribute("listeDeChiens", listeDeChiens);
			this.getServletContext().getRequestDispatcher("/WEB-INF/jsp/liste-chiens.jsp").forward(request, response);
			// Cette Jsp est à modifier en fonction du nom donné.
		}
	}

}