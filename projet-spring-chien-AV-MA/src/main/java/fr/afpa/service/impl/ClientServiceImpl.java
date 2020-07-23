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
	public void deleteById(int id) {
		clientDao.deleteByIdBdd(id);
	}

	@Override
	public Client selectById(int id) {
		return clientDao.selectByIdBdd(id);
	}

	@Override
	public void ajouterClient(Client c) {
		clientDao.ajoutClientBdd(c);

	}

	@Override
	public void updateClient(Client c) {
		// TODO Auto-generated method stub
	}

	public void authentifier() {

	}

	public void modifierInformations() {

	}

	public void supprimerCompte() {

	}

	public void inscrire() {

	}

	@Override
	public Client selectByLogin(String pLogin) {
		return clientDao.selectByLogin(pLogin);
	}

}
