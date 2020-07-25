package fr.afpa.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.afpa.bean.Chien;
import fr.afpa.dao.IChienDao;
import fr.afpa.service.IChienService;

/**
 * Implémente les méthodes de service en lien avec les chiens en appelant les
 * méthodes Dao nécessaires
 * 
 * @author Aurélien
 * @version 1.0
 *
 */
@Service
public class ChienServiceImpl implements IChienService {

	@Autowired
	IChienDao chienDao;

	/**
	 * Récupère la liste de tous les chiens
	 * 
	 * @return Retourne la liste de tous les chiens.
	 */
	@Override
	public List<Chien> getList() {
		return chienDao.getListBdd();
	}

	/**
	 * Appelera la méthode de suppression du chien en dao.
	 * 
	 * @param id identifiant du chien à supprimer
	 */
	@Override
	public void deleteById(int id) {
		chienDao.deleteByIdBdd(id);

	}

	/**
	 * Sélectionne un chien par son id.
	 * 
	 * @param id identifiant du chien à sélectionner
	 * @return retourne le chien que nous souhaitons manipuler
	 */
	@Override
	public Chien selectById(int id) {
		return chienDao.selectByIdBdd(id);
	}

	/**
	 * Appelera l'ajout d'un chien par le Dao.
	 * 
	 * @param c      Chien que l'on souhaite ajouter.
	 * @param pLogin Login du client propriétaire du nouveau chien
	 */
	@Override
	public void ajouterChien(Chien c, String pLogin) {
		chienDao.ajoutChienBdd(c, pLogin);

	}

	/**
	 * Mise à jour des informations du chien sélectionné.
	 * 
	 * @param c chien que l'on souhaite modifier
	 */
	@Override
	public void updateChien(Chien c) {
		chienDao.updateChienBdd(c);

	}

	/**
	 * Récupération de la liste de tous les chiens du client en cours de session
	 * 
	 * @param pLogin login du client connecté
	 * @return retourne la liste de tous les chiens du client connecté
	 */
	@Override
	public List<Chien> getListChienByClient(String pLogin) {
		return chienDao.getListChienByClient(pLogin);

	}

	/**
	 * Sélection d'un chien par son nom.
	 * 
	 * @param nom nom du chien à sélectionner
	 * @return retourne le chien que l'on veut manipuler.
	 */
	@Override
	public Chien selectByName(String nom) {
		return chienDao.selectByNameBdd(nom);

	}

	/**
	 * s'assure que l'age saisit soit bien un Byte
	 * 
	 * @param strAge age du chien entré par le client
	 * @return retourne un boolean true si le string de retour peut devenir un Byte
	 *         compris entre 1 et 30 sinon retourne false.
	 */
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

	/**
	 * s'assure que l'age saisit soit bien un Long
	 * 
	 * @param str puce du chien entrée par le client
	 * @return retourne un boolean true si le string de retour peut devenir un long
	 *         sinon retourne false.
	 */
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
