package fr.afpa.dao;

import java.util.List;

import fr.afpa.bean.Client;
import fr.afpa.service.impl.ClientServiceImpl;

/**
 * Interface permettant l'implémentation des méthodes de communicaton avec la
 * liste client dans la BDD.
 * 
 * @author Mathieu
 * @version 1.0
 *
 */
public interface IClientDao {

	/**
	 * Récupère l'intégralité des clients dans la BDD.
	 * 
	 * @return la liste de tous les clients
	 * @see ClientServiceImpl#getList()
	 */
	public List<Client> getListBdd();

	/**
	 * Selection d'un client par son login
	 * 
	 * @param pLogin login du client à sélectionner.
	 * @return retourne le client cherché par son login.
	 * @see ClientServiceImpl
	 */
	public Client selectByLogin(String pLogin);

	/**
	 * 
	 * Permet l'enregistrement du client dans la BDD.
	 * 
	 * @param c le nouveau client à enregistrer
	 * @return Client client ajouté en base de donnée
	 * @see ClientServiceImpl#ajouterClient(Client)
	 */
	public Client ajoutClientBdd(Client c);

	/**
	 * Permet la suppression du client de la BDD
	 * 
	 * @param clientTestRecup Client à supprimer de la BDD
	 */
	public void deleteClientBdd(Client clientTestRecup);

}
