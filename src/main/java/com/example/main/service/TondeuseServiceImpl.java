package com.example.main.service;

import org.springframework.stereotype.Service;

import com.example.main.dao.Tondeuse;
@Service
public class TondeuseServiceImpl implements TondeuseService {

	@Override
	public void deplacerTondeuse(Tondeuse tondeuse) {
		String mouvement = tondeuse.getChemin();
		for (int i = 0; i < mouvement.length() ; i++) {
			
			if ((mouvement.charAt(i)=='D')||(mouvement.charAt(i)=='G'))
					orienterTondeuse(tondeuse, mouvement.charAt(i));
			else 
				avancerTondeuse(tondeuse);
			
			
		}
		
	}

	@Override
	public void orienterTondeuse(Tondeuse tondeuse, char direction) {
		if (direction=='G') {
			 switch (tondeuse.getDirection()) {
			case 'N' -> tondeuse.setDirection('W');
			case 'W' -> tondeuse.setDirection('S');
			case 'S' -> tondeuse.setDirection('E');
			case 'E' -> tondeuse.setDirection('N');
			default -> throw new IllegalArgumentException("Invalid position" + tondeuse.getDirection());
			}
		}
		
		if (direction=='D') {
			 switch (tondeuse.getDirection()) {
			case 'N' -> tondeuse.setDirection('E');
			case 'E' -> tondeuse.setDirection('S');
			case 'S' -> tondeuse.setDirection('W');
			case 'W' -> tondeuse.setDirection('N');
			default -> throw new IllegalArgumentException("Invalid position" + tondeuse.getDirection());
			}
		}
		
	}

	@Override
	public void avancerTondeuse(Tondeuse tondeuse) {
		int x = tondeuse.getPositionX();
		
		int y = tondeuse.getPositionY();
		
		char direction = tondeuse.getDirection();
		
		if (direction=='N') {
			if (y < tondeuse.getPositionYMax())
				tondeuse.setPositionY(tondeuse.getPositionY()+1);
			//else throw new Exception(" Déplacement impossible");
		}
		
		if (direction=='S') {
			if (y >0 )
				tondeuse.setPositionY(tondeuse.getPositionY()-1);

		}
		
		if (direction=='E') {
			if (x < tondeuse.getPositionXMax() )
				tondeuse.setPositionX(tondeuse.getPositionX()+1);

		}
		
		if (direction=='W') {
			if (x >0 )
				tondeuse.setPositionX(tondeuse.getPositionX()-1);

		}
		
	}

}
