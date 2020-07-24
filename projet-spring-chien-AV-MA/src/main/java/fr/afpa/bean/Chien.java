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
	private long puce;

	public Chien(String pNom, String pRace, String pCouleur, byte pAge, long pPuce) {
		super();
		this.nom = pNom;
		this.race = pRace;
		this.couleur = pCouleur;
		this.age = pAge;
		this.puce = pPuce;
	}

}
