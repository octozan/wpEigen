package de.fom.wp.controller.ws.rest;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import de.fom.wp.controller.DaoException;
import de.fom.wp.dao.PersonDao;
import de.fom.wp.persistence.Company;
import de.fom.wp.persistence.Person;

public class Restservice {

	@Inject
	private PersonDao dao;
	
	@Inject
	private EntityManager manager;
	
	@GET
	@Path("/personsearch")
	//@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	@Produces({MediaType.APPLICATION_JSON})
	public List<Person> personSearch() throws DaoException{
		return dao.list();
	}
	
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Transactional
	public Response savePerson(Company p) throws DaoException{
		//dao.save(p);
		manager.persist(p);
		return Response.ok(p).build();
	}
	
}
