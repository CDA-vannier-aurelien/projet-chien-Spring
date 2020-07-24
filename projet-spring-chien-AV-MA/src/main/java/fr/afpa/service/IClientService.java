package fr.afpa.service;

import java.util.List;

import fr.afpa.bean.Client;


public interface IClientService {

	public List<Client> getList();
	
	public Client selectByLogin(String pLogin);

	public void ajouterClient(Client c);

	public boolean checkSiExisteBDD(String pLogin);

	public void deleteClient(Client clientTest);

}
