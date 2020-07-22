package fr.afpa.controleur;

import java.io.IOException;
import java.util.List;

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

		List<Chien> listeDeChiens = chienService.getList();

		request.setAttribute("listeDeChiens", listeDeChiens);
		this.getServletContext().getRequestDispatcher("/jsp/liste-chiens.jsp").forward(request, response);
		// Cette Jsp est à modifier en fonction du nom donné.
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}
}