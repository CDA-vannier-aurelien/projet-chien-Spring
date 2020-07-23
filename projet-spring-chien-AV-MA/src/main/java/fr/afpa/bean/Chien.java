package fr.afpa.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Chien {

	private Integer idChien;
	private String nom;
	private String race;
	private String couleur;
	private byte age;

	public Chien(String pNom, String pRace, String pCouleur, byte pAge) {
		super();
		this.nom = pNom;
		this.race = pRace;
		this.couleur = pCouleur;
		this.age = pAge;

	}

}
