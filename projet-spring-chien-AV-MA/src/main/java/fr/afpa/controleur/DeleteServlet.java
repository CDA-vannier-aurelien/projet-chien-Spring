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
 * Permet de supprimer un chien d'un compte et de la BDD.
 * 
 * @author Aurélien
 * @version
 * 
 *          Servlet implementation class DeleteServlet
 */
@WebServlet("/delete.html")
public class DeleteServlet extends AbstractServletController {
	private static final long serialVersionUID = 1L;

	@Autowired
	private IChienService chienService;

	/**
	 * Récupère l'information de la modale présente dans la jsp liste-chien.
	 * Supprimera le chien du compte lié ainsi que dans la BDD
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// Récupération des informations du client
		HttpSession session = request.getSession();
		Client c = ((Client) session.getAttribute("client"));

		String strId = request.getParameter("idChien");

		int vId = Integer.parseInt(strId);

		// Récupération du chien à supprimer puis suppression
		Chien chienASupprimer = chienService.selectById(vId);

		List<Chien> listeDeChiens = chienService.getListChienByClient(c.getLogin());
		chienService.deleteById(vId);
		listeDeChiens.remove(chienASupprimer);
		request.setAttribute("listeDeChiens", listeDeChiens);
		this.getServletContext().getRequestDispatcher("/WEB-INF/jsp/liste-chiens.jsp").forward(request, response);
	}

}
