package com.eclipse.credit.suisse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import com.eclipse.credit.suisse.service.LogReaderService;

@SpringBootApplication
public class Application {
	
	static Logger logger = LoggerFactory.getLogger(Application.class);

	public static void main(String[] args) {
		
		logger.debug("*****Start*****");
		
		ApplicationContext context = SpringApplication.run(Application.class, args);
		LogReaderService logFileReader = context.getBean(LogReaderService.class);

		String fileName = "logfile.txt";
		
		logger.info("File Location: "+ fileName);
		
		logFileReader.readFile(fileName);
		
	}

}
