package fr.afpa.dao;

import java.util.List;

import fr.afpa.bean.Client;

public interface IClientDao {
	public List<Client> getListBdd();

	public void deleteByIdBdd(int id);

	public Client selectByIdBdd(int id);

	public void ajoutClientBdd(Client c);

	public void updateClientBdd(Client c);
}
