package com.example.main;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.main.dao.Tondeuse;

@Configuration
public class JobConfig {
	
	@Autowired 
	private JobBuilderFactory jobBuilderFactory;
	
	@Autowired 
	private StepBuilderFactory stepBuilderFactory;
	
	@Autowired 
	private ItemReader<Tondeuse> tondeuseItemReader;
	@Autowired 
	private ItemWriter<Tondeuse> tondeuseItemWriter;
	@Autowired
	private ItemProcessor< Tondeuse, Tondeuse> tondeuseItemProcessor;
	
	@Autowired
	private ExtremiteListener extremiteListener;
	
	@Bean
	public Job tondeuseJob() {
		
		Step step1= stepBuilderFactory.get("step-load-data")
				.<Tondeuse,Tondeuse>chunk(10)
				.reader(tondeuseItemReader)
				.processor(tondeuseItemProcessor)
				.writer(tondeuseItemWriter)
				.listener(extremiteListener) 
				.build();
		return jobBuilderFactory.get("tondeuse-job")
				.start(step1)
				.build();

	}
	

}
