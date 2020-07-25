package fr.afpa.service;

import java.util.List;

import fr.afpa.bean.Chien;

/**
 * Interface permettant l'implémentation des méthodes de service en lien avec
 * les chiens
 * 
 * @author Aurélien
 * @version 1.0
 *
 */
public interface IChienService {

	/**
	 * Récupère la liste de tous les chiens
	 * 
	 * @return retourne la liset de tous les chiens
	 */
	public List<Chien> getList();

	/**
	 * Appelera la méthode de suppression du chien en dao.
	 * 
	 * @param id identifiant du chien à supprimer
	 */
	public void deleteById(int id);

	/**
	 * Sélectionne un chien par son id.
	 * 
	 * @param id identifiant du chien à sélectionner
	 * @return retourne le chien que nous souhaitons manipuler
	 */
	public Chien selectById(int id);

	/**
	 * Sélection d'un chien par son nom.
	 * 
	 * @param nom nom du chien à sélectionner
	 * @return retourne le chien que l'on veut manipuler.
	 */
	public Chien selectByName(String nom);

	/**
	 * Appelera l'ajout d'un chien par le Dao.
	 * 
	 * @param c      Chien que l'on souhaite ajouter.
	 * @param pLogin Login du client propriétaire du nouveau chien
	 */
	public void ajouterChien(Chien c, String pLogin);

	/**
	 * Mise à jour des informations du chien sélectionné.
	 * 
	 * @param c chien que l'on souhaite modifier
	 */
	public void updateChien(Chien c);

	/**
	 * Récupération de la liste de tous les chiens du client en cours de session
	 * 
	 * @param login login du client connecté
	 * @return retourne la liste de tous les chiens du client connecté
	 */
	public List<Chien> getListChienByClient(String login);

	/**
	 * s'assure que l'age saisit soit bien un Byte
	 * 
	 * @param strAge age du chien entré par le client
	 * @return retourne un boolean true si le string de retour peut devenir un Byte
	 *         compris entre 1 et 30 sinon retourne false.
	 */
	public boolean parseByteTest(String strAge);

	/**
	 * s'assure que l'age saisit soit bien un Long
	 * 
	 * @param str puce du chien entrée par le client
	 * @return retourne un boolean true si le string de retour peut devenir un long
	 *         sinon retourne false.
	 */
	public boolean parseLongTest(String str);

}
