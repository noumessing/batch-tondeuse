package com.example.main;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.main.dao.Tondeuse;
import com.example.main.service.TondeuseService;

@Component
public class TondeuseItemProcessor implements ItemProcessor<Tondeuse, Tondeuse> {
	
	@Autowired
	private TondeuseService tondeuseService;

	@Override
	public Tondeuse process(Tondeuse item) throws Exception {
		tondeuseService.deplacerTondeuse(item);
		return item;
	}

}
