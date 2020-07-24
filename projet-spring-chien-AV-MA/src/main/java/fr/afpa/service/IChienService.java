package fr.afpa.service;

import java.util.List;

import fr.afpa.bean.Chien;

public interface IChienService {

	public List<Chien> getList();

	/**
	 * descriptif
	 * 
	 * @param id identifiant du chien à supprimer
	 */
	public void deleteById(int id);

	/**
	 * 
	 * @param id
	 * @return retourne le chien que nous souhaiton manipuler
	 */
	public Chien selectById(int id);

	public Chien selectByName(String nom);

	public void ajouterChien(Chien c, String pLogin);

	public void updateChien(Chien c);

	public List<Chien> getListChienByClient(String login);

	public boolean parseByteTest(String strAge);

	public boolean parseLongTest(String str);

}
