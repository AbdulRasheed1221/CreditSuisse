package com.eclipse.credit.suisse.repository;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.eclipse.credit.suisse.entity.EventsDomain;

@Repository
public interface EventsRepository extends CrudRepository<EventsDomain, String>{
	
	
	
}
