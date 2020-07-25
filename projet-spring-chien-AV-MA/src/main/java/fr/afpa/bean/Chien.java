package fr.afpa.bean;

import fr.afpa.dao.impl.ChienDaoImpl;
import fr.afpa.service.impl.ChienServiceImpl;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * La classe Chien reprend toutes les informations nécessaires concernant le
 * chien pour notre projet. Un chien est rattaché à son propriétaire par le
 * login. Son Id est unique ainsi que son numéro de puce.
 * 
 * @see ChienServiceImpl#ajouterChien(Chien, String)
 * @see ChienDaoImpl#ajoutChienBdd(Chien, String)
 * @see ChienServiceImpl#updateChien(Chien)
 * @see ChienDaoImpl#updateChienBdd(Chien)
 * 
 * @author Aurelien
 * @version 1.0
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Chien {

	/**
	 * L'id chien est non modifiable, auto-implémentée, auto-incrémentée et unique.
	 * 
	 * @see Chien#Chien(int, String, String, String, byte, long)
	 * @see Chien#getIdChien()
	 * @see ChienServiceImpl#deleteById(int)
	 * @see ChienDaoImpl#deleteByIdBdd(int)
	 * @see ChienServiceImpl#selectById(int)
	 * @see ChienDaoImpl#selectByIdBdd(int)
	 * 
	 */
	private Integer idChien;

	/**
	 * Le nom du chien sert à appeler son chien. Est modifiable, requise et non
	 * unique.
	 * 
	 * @see Chien#getNom()
	 * @see Chien#setNom(String)
	 * @see Chien#Chien(String, String, String, byte, long)
	 * @see ChienServiceImpl#selectByName(String)
	 * @see ChienDaoImpl#selectByNameBdd(String)
	 */

	private String nom;

	/**
	 * La race du chien sert à connaître son type. Est modifiable, requise et non
	 * unique.
	 * 
	 * @see Chien#getRace()
	 * @see Chien#setRace(String)
	 * @see Chien#Chien(String, String, String, byte, long)
	 */

	private String race;

	/**
	 * La couleur du chien permet de renseigner l'apparence du chien. Est
	 * modifiable, non unique et requise.
	 * 
	 * @see Chien#Chien(Integer, String, String, String, byte, long)
	 * @see Chien#setCouleur(String)
	 * @see Chien#getCouleur()
	 */
	private String couleur;

	/**
	 * Renseinge l'age du chien. Est requis, non unique et modifiable.
	 * 
	 * @see Chien#getAge()
	 * @see Chien#setAge(byte)
	 * @see Chien#Chien(Integer, String, String, String, byte, long)
	 */
	private byte age;

	/**
	 * Donne le numéro de la puce électronique propre au chien. Est unique, non
	 * modifiable et requise.
	 * 
	 * @see Chien#Chien(Integer, String, String, String, byte, long)
	 * @see Chien#getPuce()
	 */
	private long puce;

	/**
	 * 
	 * Constructeur permettant de créer le chien avant son implémentation en BDD
	 * donc sans l'id qui est incrémentée.
	 * 
	 * @see ChienServiceImpl#ajouterChien(Chien, String)
	 * @param pNom     nom du chien
	 * @param pRace    race du chien
	 * @param pCouleur couleur du chien
	 * @param pAge     age du chien
	 * @param pPuce    numéro de puce du chien
	 */
	public Chien(String pNom, String pRace, String pCouleur, byte pAge, long pPuce) {
		super();
		this.nom = pNom;
		this.race = pRace;
		this.couleur = pCouleur;
		this.age = pAge;
		this.puce = pPuce;
	}

}
