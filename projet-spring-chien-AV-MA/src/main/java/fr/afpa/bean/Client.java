package fr.afpa.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Client {

	private String login;
	private String password;
	private String prenom;
	private String nom;

}
