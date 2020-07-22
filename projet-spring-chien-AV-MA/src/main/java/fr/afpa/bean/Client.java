package fr.afpa.bean;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Client {

	private Integer idClient;
	private String login;
	private String password;
	private String prenom;
	private String nom;
	private List<Chien> listeChiens;
	
	public Client(String login, String password, String prenom, String nom, List<Chien> listeChiens) {
		super();
		this.login = login;
		this.password = password;
		this.prenom = prenom;
		this.nom = nom;
		this.listeChiens = listeChiens;
	}

	public Client(String login, String password, String prenom, String nom) {
		super();
		this.login = login;
		this.password = password;
		this.prenom = prenom;
		this.nom = nom;
	}

	
	
	
}
