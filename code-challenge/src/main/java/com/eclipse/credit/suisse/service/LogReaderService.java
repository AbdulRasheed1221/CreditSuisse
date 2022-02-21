package com.eclipse.credit.suisse.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.eclipse.credit.suisse.entity.Event;
import com.eclipse.credit.suisse.entity.EventsDomain;
import com.eclipse.credit.suisse.repository.EventsRepository;
import com.google.gson.Gson;

@Service
@Transactional
public class LogReaderService {
	
	@Autowired
	private EventsRepository impl;
	
	Logger logger = LoggerFactory.getLogger(LogReaderService.class);
	
	 public void readFile(String fileName){
		
		logger.info("Entry in Service");
		
		logger.debug("File Location: ",fileName);
		
		HashMap<String,Event> hMap = new HashMap<>();
		
		try (Stream<String> stream = Files.lines(Paths.get(fileName))) {
			
			logger.debug("Reading File");
			
            stream.forEach(a -> {
            				Event targetObject = new Gson().fromJson(a, Event.class);
            				if(hMap.get(targetObject.id) == null) {
            					logger.debug("Found new ID "+targetObject.id);
            					hMap.put(targetObject.id, targetObject);
            				} else {
            					logger.debug("Found old ID "+targetObject.id);
            					long diff = Math.abs((hMap.get(targetObject.id).timestamp - targetObject.timestamp));
            					
            					EventsDomain domainObj = new EventsDomain();
            					domainObj.setDuration(diff);
            					domainObj.setEventID(targetObject.id);
            					domainObj.setHost(targetObject.host);
            					domainObj.setType(targetObject.type);
            					
            					logger.debug("Difference in timestamp is "+diff + " for id = "+domainObj.getEventID());
            					if(diff > 4){
            						logger.debug("Setting alert to true");
            						domainObj.setAlert(true);
            					}
            					logger.debug("Saving object in DB: "+domainObj);
            					impl.save(domainObj);
            					logger.info("Save successful for " +domainObj.getEventID());
            					
            				}
            			});
            
            logger.info("Exit from Service");
            

        } catch (IOException e) {
            e.printStackTrace();
        }
	}
}
