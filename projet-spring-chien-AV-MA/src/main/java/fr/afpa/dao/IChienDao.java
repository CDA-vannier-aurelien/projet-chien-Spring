package fr.afpa.dao;

import java.util.List;

import fr.afpa.bean.Chien;

public interface IChienDao {
	public List<Chien> getListBdd();

	public List<Chien> getListChienByClient(String pLogin);

	public void deleteByIdBdd(int id);

	public Chien selectByIdBdd(int id);

	public void ajoutChienBdd(Chien c, String pLogin);

	public void updateChienBdd(Chien c);

	public Chien selectByNameBdd(String nom);

}
