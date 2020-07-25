package fr.afpa.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.afpa.bean.Client;
import fr.afpa.dao.IClientDao;
import fr.afpa.service.IClientService;

/**
 * implemente l'interface ClientService afin de réalser des méthodes de service
 * en vue d'appeler les péthodes DAo notamment.
 * 
 * 
 * @author Mathieu
 * @version 1.0
 *
 */
@Service
public class ClientServiceImpl implements IClientService {

	@Autowired
	IClientDao clientDao;

	/**
	 * Sélectionne la liste de tous les clients.
	 * 
	 * @return retourne la liste de tous les clients.
	 */
	@Override
	public List<Client> getList() {
		return clientDao.getListBdd();
	}

	/**
	 * Sélectionne un client par son login
	 * 
	 * @param pLogin Login du client à sélectionner
	 * @return retourne le client désiré par son login
	 */
	@Override
	public void ajouterClient(Client c) {
		clientDao.ajoutClientBdd(c);

	}

	/**
	 * Ajoute un client
	 * 
	 * @param c Client crée en Servlet qui devra être ajouter au Dao puis en BDD
	 */
	@Override
	public Client selectByLogin(String pLogin) {
		return clientDao.selectByLogin(pLogin);
	}

	/**
	 * Vérife l'existence d'un client dans la BDD
	 * 
	 * @param pLogin Login (id) du client vérifié
	 * @return retourne un boolean true si le client existe sinon retourne false.
	 */
	@Override
	public boolean checkSiExisteBDD(String pLogin) {
		boolean existe = false;
		// On récupère la liste des clients
		List<Client> listeLoginClients = clientDao.getListBdd();
		// On teste si le login est présent en bdd
		for (Client client : listeLoginClients) {
			if (pLogin.equals(client.getLogin())) {
				existe = true;
			}
		}
		return existe ? true : false;
	}

	/**
	 * Permet de supprimer un client.
	 * 
	 * @param clientTest Client que l'on souhaite supprimer.
	 */
	@Override
	public void deleteClient(Client pClient) {
		clientDao.deleteClientBdd(pClient);
	}

}
