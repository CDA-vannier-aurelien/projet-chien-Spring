package fr.afpa.service;

import java.util.List;

import fr.afpa.bean.Client;


public interface IClientService {

	public List<Client> getList();

	public void deleteById(int id);

	public Client selectById(int id);
	
	public Client selectByLogin(String pLogin);

	public void ajouterClient(Client c);

	public void updateClient(Client c);

}
