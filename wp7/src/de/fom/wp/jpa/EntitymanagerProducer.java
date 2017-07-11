package de.fom.wp.jpa;

import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class EntitymanagerProducer {
	
	@Produces
	@PersistenceContext(unitName="wpdatasource")
	public EntityManager manager;
	
	

}
