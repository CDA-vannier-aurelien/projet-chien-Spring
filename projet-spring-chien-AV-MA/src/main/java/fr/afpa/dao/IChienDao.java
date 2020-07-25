package fr.afpa.dao;

import java.util.List;

import fr.afpa.bean.Chien;
import fr.afpa.service.impl.ChienServiceImpl;

/**
 * Interface permettant l'implémentation des méthodes de communicaton avec la
 * liste chien dans la BDD.
 * 
 * @author Mathieu
 * @version 1.0
 *
 */
public interface IChienDao {

	/**
	 * Récupère l'intégralité des chiens dans la BDD.
	 * 
	 * @return la liste de tous les chiens
	 * @see ChienServiceImpl#getList()
	 */
	public List<Chien> getListBdd();

	/**
	 * récupère la liste des chiens du client dont la session est ouverte.
	 * 
	 * @param pLogin Login du client en cours.
	 * @return la liste des chiens du client
	 * @see ChienServiceImpl#getListChienByClient(String)
	 */
	public List<Chien> getListChienByClient(String pLogin);

	/**
	 * Supprimer le chien par l'id-chien
	 * 
	 * @param id du chien sélectionné
	 * @see ChienServiceImpl#deleteById(int)
	 */

	public void deleteByIdBdd(int id);

	/**
	 * Sélectionne un chien par son ID.
	 * 
	 * @param id du chien à récupérer
	 * @return le chien à récupérer.
	 * @see ChienServiceImpl#selectById(int)
	 */
	public Chien selectByIdBdd(int id);

	/**
	 * 
	 * Permet l'enregistrement du chien dans la BDD.
	 * 
	 * @param c      le nouveau chien à enregistrer
	 * @param pLogin Id du client propriétaire du nouveau chien et qui sert de
	 *               Foreign key.
	 * @see ChienServiceImpl#ajouterChien(Chien, String)
	 */

	public void ajoutChienBdd(Chien c, String pLogin);

	/**
	 * Mise à jour en BDD du chien sélectionné.
	 * 
	 * @param c Le chien sélectionné dont les informations seront modifiées.
	 * @see ChienServiceImpl#updateChien(Chien)
	 * 
	 */

	public void updateChienBdd(Chien c);

	/**
	 * Selection d'un chien par son nom
	 * 
	 * @param nom du chien à sélectionner.
	 * @return retourne le chien cherché par son nom.
	 * @see ChienServiceImpl#selectByName(String)
	 */

	public Chien selectByNameBdd(String nom);

}
