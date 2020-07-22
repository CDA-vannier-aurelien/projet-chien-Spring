package fr.afpa.bean;

import lombok.Data;

@Data
public class Client {

	private Integer idClient;
	private String login;
	private String password;
	private String prenom;
	private String nom;
	
}
