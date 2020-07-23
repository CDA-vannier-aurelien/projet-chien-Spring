package fr.afpa.service;

import java.util.List;

import fr.afpa.bean.Chien;
import fr.afpa.bean.Client;

public interface IChienService {

	public List<Chien> getList();

	public void deleteById(int id);

	public Chien selectById(int id);

	public void ajouterChien(Chien c, String pLogin);

	public void updateChien(Chien c);

	public List<Chien> getListChienByClient(String login);

}
