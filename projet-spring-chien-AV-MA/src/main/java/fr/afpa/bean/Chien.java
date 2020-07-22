package fr.afpa.bean;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Chien {

	private Integer idChien;
	private String nom;
	private String race;
	private String couleur;
	private byte age;

}
