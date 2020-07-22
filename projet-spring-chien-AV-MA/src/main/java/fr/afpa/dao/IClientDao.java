package fr.afpa.dao;

import java.util.List;

import fr.afpa.bean.Client;

public interface IClientDao {
	public List<Client> getListBdd();

	public void deleteByIdBdd(int id);

	public Client selectByIdBdd(int id);

	public Client ajoutClientBdd(Client c);

	public Client updateClientBdd(Client c);
}
