package com.softserve.osbb;

import com.softserve.osbb.repository.ReportRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.softserve.osbb")
@EntityScan
public class PersistenceConfiguration {

	public static void main(String[] args)
	{
		SpringApplication.run(PersistenceConfiguration.class, args);


	}



}
