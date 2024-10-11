package com.example.demo;

import static org.junit.jupiter.api.Assertions.*;



import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import com.example.main.dao.Tondeuse;
import com.example.main.service.TondeuseService;
import com.example.main.service.TondeuseServiceImpl;



class TondeuseTest {
	
	
	private TondeuseService tondeuseService;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		
		
	}

	
	
	@Test
	public void deplacerTest() {
		 Tondeuse ton = new Tondeuse();  //1 2 N GAGAGAGAA  -> 1 3 N 
		 tondeuseService = new TondeuseServiceImpl() ;
		 ton.setPositionX(1);
		 ton.setPositionY(2);
		 ton.setDirection('N');
		 ton.setPositionXMax(5);
		 ton.setPositionYMax(5);
		 ton.setChemin("GAGAGAGAA");
		 
		 System.out.println(ton.getPositionX());
		 tondeuseService.deplacerTondeuse(ton);
		 
		 assertEquals(1, ton.getPositionX());
		 assertEquals(3, ton.getPositionY());
		 assertEquals('N', ton.getDirection());
		 
	}
	
	@Test
	public void deplacerTest2() {
		 Tondeuse ton = new Tondeuse();  // 3 3 E AADAADADDA  -> 5 1 E
		 tondeuseService = new TondeuseServiceImpl() ;
		 ton.setPositionX(3);
		 ton.setPositionY(3);
		 ton.setDirection('E');
		 ton.setPositionXMax(5);
		 ton.setPositionYMax(5);
		 ton.setChemin("AADAADADDA");
		 
		 System.out.println(ton.getPositionX());
		 tondeuseService.deplacerTondeuse(ton);
		 
		 assertEquals(5, ton.getPositionX());
		 assertEquals(1, ton.getPositionY());
		 assertEquals('E', ton.getDirection());
		 
	}

}
