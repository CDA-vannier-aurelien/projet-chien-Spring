package fr.afpa.service;

import java.util.List;

import fr.afpa.bean.Client;

/**
 * Inteferace qui permettra d'appeler les méthodes de service et d'appel des
 * Dao.
 * 
 * @author Mathieu
 * @version 1.O
 *
 */
public interface IClientService {

	/**
	 * Sélectionne la liste de tous les clients.
	 * 
	 * @return retourne la liste de tous les clients.
	 */
	public List<Client> getList();

	/**
	 * Sélectionne un client par son login
	 * 
	 * @param pLogin Login du client à sélectionner
	 * @return retourne le client désiré par son login
	 */
	public Client selectByLogin(String pLogin);

	/**
	 * Ajoute un client
	 * 
	 * @param c Client crée en Servlet qui devra être ajouter au Dao puis en BDD
	 */
	public void ajouterClient(Client c);

	/**
	 * cérife l'existence d'un client dans la BDD
	 * 
	 * @param pLogin Login (id) du client vérifié
	 * @return retourne un boolean true si le client existe sinon retourne false.
	 */
	public boolean checkSiExisteBDD(String pLogin);

	/**
	 * Permet de supprimer un client.
	 * 
	 * @param clientTest Client que l'on souhaite supprimer.
	 */
	public void deleteClient(Client clientTest);

}
