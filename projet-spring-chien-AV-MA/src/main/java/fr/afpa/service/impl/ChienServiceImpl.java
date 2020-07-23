package fr.afpa.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.afpa.bean.Chien;
import fr.afpa.dao.IChienDao;
import fr.afpa.service.IChienService;

@Service
public class ChienServiceImpl implements IChienService {

	@Autowired
	IChienDao chienDao;

	@Override
	public List<Chien> getList() {
		return chienDao.getListBdd();
	}

	@Override
	public void deleteById(int id) {
		chienDao.deleteByIdBdd(id);

	}

	@Override
	public Chien selectById(int id) {
		return chienDao.selectByIdBdd(id);
	}

	@Override
	public void ajouterChien(Chien c) {
		chienDao.ajoutChienBdd(c);

	}

	@Override
	public void updateChien(Chien c) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Chien> getListChienByClient(String pLogin) {
		chienDao.getListChienByClient(pLogin);
		return null;
	}

}
