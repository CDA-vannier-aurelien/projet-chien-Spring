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
	public void ajouterChien(Chien c, String pLogin) {
		chienDao.ajoutChienBdd(c, pLogin);

	}

	@Override
	public void updateChien(Chien c) {
		chienDao.updateChienBdd(c);

	}

	@Override
	public List<Chien> getListChienByClient(String pLogin) {
		return chienDao.getListChienByClient(pLogin);

	}

	@Override
	public Chien selectByName(String nom) {
		return chienDao.selectByNameBdd(nom);

	}

	@Override
	public boolean parseByteTest(String strAge) {
		boolean res = false;
		try {
			byte age = Byte.parseByte(strAge);
			if (age > 0 && age < 30) {
				res = true;
			} else {
				res = false;
			}
		} catch (Exception e) {
			res = false;
		}
		return res;
	}

	@Override
	public boolean parseLongTest(String str) {
		boolean res = false;
		try {
			long num = Long.parseLong(str);
			if (num > 0) {
				res = true;
			} else {
				res = false;
			}
		} catch (Exception e) {
			res = false;
		}
		return res;
	}

}
