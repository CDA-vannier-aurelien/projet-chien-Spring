package fr.afpa.service;

import java.util.List;

import fr.afpa.bean.Chien;

public interface IChienService {

	public List<Chien> getList();

	public void deleteById(int id);

	public Chien selectById(int id);

	public void ajouterChien(Chien c);

	public void updateChien(Chien c);

}
