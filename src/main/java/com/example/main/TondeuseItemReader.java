package com.example.main;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.annotation.BeforeStep;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.core.io.Resource;

import com.example.main.dao.Extremite;
import com.example.main.dao.Tondeuse;

public class TondeuseItemReader implements ItemReader<Tondeuse> {
	
	 private BufferedReader reader;
	 private Extremite extremite;

	 
	 @BeforeStep
	 public void beforeStep(StepExecution stepExecution) {
	        // Récupérer les informations de la premiere 
		 this.extremite = (Extremite) stepExecution.getExecutionContext().get("extremite");
		 
		 if (this.extremite == null) {
	            throw new IllegalStateException("Extremite est null, assurez-vous qu'elle est initialisée dans le GrilleListener.");
	        }
	       
	    }

	 public TondeuseItemReader(Resource resource) throws Exception {
	        this.reader = new BufferedReader(new InputStreamReader(resource.getInputStream()));

	        // Sauter la première ligne 
	        reader.readLine();  // Ignorer la ligne d l'extreme droite
	    }
	 
	@Override
	public Tondeuse read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
		 // Lire la première ligne (position et direction)
        String line1 = reader.readLine();
        if (line1 == null) {
            return null;  // Fin du fichier
        }

        // Lire la deuxième ligne (instructions)
        String chemin = reader.readLine();
        if (chemin == null) {
            throw new IllegalStateException("Fichier mal formé, il manque une ligne d'instructions.");
        }

        // Parse la première ligne : ex :"1 2 N"
        String[] positionDirection = line1.split(" ");
        int positionInitialeX = Integer.parseInt(positionDirection[0]);
        int positionInitialeY = Integer.parseInt(positionDirection[1]);
        char direction = positionDirection[2].charAt(0);


        // Créer et retourner un objet Tondeuse
        return new Tondeuse(positionInitialeX, positionInitialeY, direction, extremite.getX(),extremite.getY(),chemin);
	}

}
