package com.example.main;

import java.util.HashMap;
import java.util.Map;

import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameter;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class JobRestController {
	
	@Autowired
	private JobLauncher jobLauncher;
	
	@Autowired
	private Job job;
	
	@GetMapping("/startJob")
	public BatchStatus load() throws Exception {
		
		Map<String, JobParameter> params = new HashMap<>();
		params.put("time",new JobParameter(System.currentTimeMillis()));
		JobParameters jobParameters = new JobParameters(params);
		JobExecution jobExecution = jobLauncher.run(job, jobParameters);
		while (jobExecution.isRunning()) {
			System.out.println(".......");
		}
		
		return jobExecution.getStatus();
	}

}
