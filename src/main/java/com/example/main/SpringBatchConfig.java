package com.example.main;



import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.transform.BeanWrapperFieldExtractor;
import org.springframework.batch.item.file.transform.DelimitedLineAggregator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;

import com.example.main.dao.Tondeuse;


@Configuration
@EnableBatchProcessing
public class SpringBatchConfig {


	@Bean
	public FlatFileItemWriter<Tondeuse> writer() {

		Resource resource = new FileSystemResource("outputData.txt");
		 FlatFileItemWriter<Tondeuse> writer = new FlatFileItemWriter<>();
		 writer.setResource(resource);
		 writer.setAppendAllowed(true);
		 writer.setLineAggregator(new DelimitedLineAggregator<>() {
			    {
			      setDelimiter(" ");
			      setFieldExtractor(new BeanWrapperFieldExtractor<>() {
			        {
			          setNames(new String[]{"positionX", "positionY","direction"});
			        }
			      });
			    }
			  });
		 return writer;
	}
	

	@Bean
    public ExtremiteListener extremiteListener() {
        return new ExtremiteListener(new  ClassPathResource("inputData.txt"));
    }

    @Bean
    public TondeuseItemReader tondeuseItemReader() throws Exception {
        return new TondeuseItemReader(new ClassPathResource("inputData.txt"));
       // return new TondeuseItemReader(new ClassPathResource("inputData.txt"));
    }

}
