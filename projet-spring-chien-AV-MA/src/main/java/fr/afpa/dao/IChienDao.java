package fr.afpa.dao;

import java.util.List;

import fr.afpa.bean.Chien;

public interface IChienDao {
	public List<Chien> getListBdd();

	public void deleteByIdBdd(int id);

	public Chien selectByIdBdd(int id);

	public Chien ajoutChienBdd(Chien c);

	public void updateChienBdd(Chien c);

}
