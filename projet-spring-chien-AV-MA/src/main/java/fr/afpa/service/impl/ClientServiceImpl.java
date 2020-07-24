package fr.afpa.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.afpa.bean.Client;
import fr.afpa.dao.IClientDao;
import fr.afpa.service.IClientService;

@Service
public class ClientServiceImpl implements IClientService {

	@Autowired
	IClientDao clientDao;

	@Override
	public List<Client> getList() {
		return clientDao.getListBdd();
	}

	@Override
	public void ajouterClient(Client c) {
		clientDao.ajoutClientBdd(c);

	}

	@Override
	public Client selectByLogin(String pLogin) {
		return clientDao.selectByLogin(pLogin);
	}

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

	@Override
	public void deleteClient(Client pClient) {
		clientDao.deleteClientBdd(pClient);
	}

}
