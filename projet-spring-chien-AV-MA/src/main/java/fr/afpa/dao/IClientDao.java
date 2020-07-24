package fr.afpa.dao;

import java.util.List;

import fr.afpa.bean.Client;

public interface IClientDao {
	public List<Client> getListBdd();

	public Client selectByLogin(String pLogin);

	public Client ajoutClientBdd(Client c);

	public void deleteClientBdd(Client clientTestRecup);

}
