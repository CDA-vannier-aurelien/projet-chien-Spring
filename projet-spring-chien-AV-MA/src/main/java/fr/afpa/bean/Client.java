package fr.afpa.bean;

import fr.afpa.dao.impl.ChienDaoImpl;
import fr.afpa.dao.impl.ClientDaoImpl;
import fr.afpa.service.impl.ChienServiceImpl;
import fr.afpa.service.impl.ClientServiceImpl;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * La classe Client reprend toutes les informations nécessaires conernant le
 * client pour notre projet. Un client est propriétaire de zéro chien lors de
 * son inscription. Son login correspond à son Id et donc à sa clé primaire en
 * BDD et deviendra la foreign key des chiens dont il sera le propriétaire.
 * 
 * @see ClientServiceImpl#ajouterClient(Client)
 * @see ClientServiceImpl#deleteClient(Client)
 * @see ClientDaoImpl#ajoutClientBdd(Client)
 * @see ClientDaoImpl#deleteClientBdd(Client)
 * 
 * @author Mathieu
 * @version 1.0
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Client {

	/**
	 * Le login est le surnom que le client choisit lors de l'inscription est
	 * correspondra à son ID. Est unique, non modifiable et requis.
	 * 
	 * @see Client#getLogin()
	 * @see ClientServiceImpl#checkSiExisteBDD(String)
	 * @see ClientServiceImpl#selectByLogin(String)
	 * @see ClientDaoImpl#selectByLogin(String)
	 * @see ChienServiceImpl#getListChienByClient(String)
	 * @see ChienDaoImpl#getListChienByClient(String)
	 * 
	 */

	private String login;

	/**
	 * Le password est le mot de passe obligatoire du client pour se connecter à son
	 * compte. Le compte est hashé afin de le sécuriser notamment en BDD. Non
	 * modifiable, non unique et requis.
	 * 
	 * @see Client#getPassword()
	 * 
	 */
	private String password;

	/**
	 * Le prénom est un élément d'identification du client. Modifiable, requis et
	 * non unique.
	 * 
	 * @see Client#getPrenom()
	 * @see Client#setPrenom(String)
	 */
	private String prenom;

	/**
	 * Le nom est un élément d'identification du client. Modifiable, requis et non
	 * unique.
	 * 
	 * @see Client#getNom()
	 * @see Client#setNom(String)
	 * @see Client
	 */
	private String nom;

}
