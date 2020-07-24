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

@WebServlet(urlPatterns = ("/ajoutChien.do"))
public class AjoutChienServlet extends AbstractServletController {
	private static final long serialVersionUID = 1L;

	@Autowired
	IChienService chienService;

//	@Override
//	protected void doGet(HttpServletRequest request, HttpServletResponse response)
//			throws ServletException, IOException {
//		this.getServletContext().getRequestDispatcher("/WEB-INF/jsp/ajoutChien.jsp").forward(request, response);
//	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();
		Client c = (Client) session.getAttribute("client");
		System.out.println(c + "dans le doPost");
		String login = c.getLogin();

//		if(request.getParameter("nom").matches("\\[a-zA-Z]+"))

		// ajout session client

		String vNom = request.getParameter("nom").toLowerCase();
		String vRace = request.getParameter("race").toLowerCase();
		String vCouleur = request.getParameter("couleur").toLowerCase();
		String strAge = request.getParameter("age");
		System.out.println(vNom);
		boolean parsable = parseTest(strAge);
		if (!Pattern.matches("[\\D]+", vNom) || !Pattern.matches("[\\D]+", vRace)
				|| !Pattern.matches("[\\D]+", vCouleur) || parsable == false) {
			request.setAttribute("error", "Erreur de saisie");
			this.getServletContext().getRequestDispatcher("/WEB-INF/jsp/liste-chiens.jsp").forward(request, response);
		} else {
			boolean existe = false;
			byte vAge = Byte.parseByte(strAge);
			Chien chien = new Chien(vNom, vRace, vCouleur, vAge);
			System.out.println(chien);
			List<Chien> listeChien = chienService.getListChienByClient(login);

			for (Chien vChien : listeChien) {
				if (vChien.getNom().equals(chien.getNom()) && vChien.getRace().equals(chien.getRace())
						&& vChien.getAge() == (chien.getAge())) {
					existe = true;
				}
			}
			if (existe == true) {
				request.setAttribute("existe", "ce chien est déjà enregistré!");
				this.getServletContext().getRequestDispatcher("/WEB-INF/jsp/liste-chiens.jsp").forward(request,
						response);
			} else {
				chienService.ajouterChien(chien, login);
				chien = chienService.selectByName(vNom);
				System.out.println(chien);
				listeChien.add(chien);
				for (Chien chien2 : listeChien) {
					System.out.println(chien2);
				}
			}
			request.setAttribute("listeDeChiens", listeChien);
		}
		this.getServletContext().getRequestDispatcher("/WEB-INF/jsp/liste-chiens.jsp").forward(request, response);

	}

	private boolean parseTest(String strAge) {
		boolean res = false;
		try {
			byte age = Byte.parseByte(strAge);
			if (age > 0 && age < 30) {
				res = true;
			}else {
			res = false;
			}
		} catch (Exception e) {
			res = false;
		}
		return res;
	}
}
