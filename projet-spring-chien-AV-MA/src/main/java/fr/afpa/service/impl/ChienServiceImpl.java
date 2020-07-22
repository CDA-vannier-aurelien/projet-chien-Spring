package fr.afpa.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import fr.afpa.bean.Chien;
import fr.afpa.dao.IChienDao;
import fr.afpa.service.IChienService;

public class ChienServiceImpl implements IChienService {

	@Autowired
	IChienDao chienDao;

	@Override
	public List<Chien> getList() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteById(int id) {
		// TODO Auto-generated method stub

	}

	@Override
	public Chien selectById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void ajouterChien(Chien c) {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateChien(Chien c) {
		// TODO Auto-generated method stub

	}

}
