package com.example.main.dao;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @ToString
public class Tondeuse {

	
	private int positionX;  //Position x
	
	private int positionY;   //position y
	
	private char direction;  // indication de l'orientation (N,S,E,W)
	
	public  int positionXMax ;  //coordonnée x du coin supérieur droit
	
	public  int positionYMax ;  //coordonnée y du coin supérieur droit
	
	private String chemin;  //serie d'instructions pour la tondeuse

}
