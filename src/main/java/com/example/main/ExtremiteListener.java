package com.example.main;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.listener.StepExecutionListenerSupport;
import org.springframework.core.io.Resource;

import com.example.main.dao.Extremite;

public class ExtremiteListener extends StepExecutionListenerSupport {
	
	private  Resource resource;

	public ExtremiteListener(Resource resource) {
		this.resource = resource;
	}
	
	@Override
    public void beforeStep(StepExecution stepExecution) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(resource.getInputStream()))) {
            // Lire la première ligne
            String extremiteLine = reader.readLine();
            if (extremiteLine != null) {
                String[] coordonnees = extremiteLine.split(" ");
                int x = Integer.parseInt(coordonnees[0]);
                int y = Integer.parseInt(coordonnees[1]);
                
                // Stocker dans le contexte d'exécution du Step
                stepExecution.getExecutionContext().put("extremite", new Extremite(x, y));
            }
        } catch (Exception e) {
            throw new IllegalStateException("Erreur lors de la lecture de la première ligne du fichier.", e);
        }
    }
	

}
