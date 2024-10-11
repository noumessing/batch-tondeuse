package com.example.main.service;

import com.example.main.dao.Tondeuse;

public interface TondeuseService {
	
	void deplacerTondeuse(Tondeuse tondeuse );
	
	void orienterTondeuse( Tondeuse tondeuse,char direction);
	
	void avancerTondeuse(Tondeuse tondeuse) ;

}
