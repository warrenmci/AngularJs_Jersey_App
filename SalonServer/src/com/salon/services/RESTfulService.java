package com.salon.services;

import java.sql.SQLException;
import java.util.List;

import javax.ws.rs.*;
import javax.ws.rs.core.*;

import com.salon.dao.PersonDao;
import com.salon.entities.Person;

@Path("/salon")
public class RESTfulService {
	
	PersonDao personDao = new PersonDao();
	
	@GET
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	@Path("/find")
	public Person find() {
		return personDao.find();
	}
	
	@GET
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	@Path("/findall")
	public List<Person> findAll() {
		return personDao.findAll();
	}
	
	@GET
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	@Path("/{id}")
	public Person findById(@PathParam("id") Integer id) {
		return personDao.findById(id);
	}
	
	@GET
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	@Path("/firstname/{st}")
	public List<Person> findByFirstName(@PathParam("st") String st) {
		return personDao.findByFirstName(st);
	}
	
	@GET
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	@Path("/lastname/{st}")
	public List<Person> findByLastName(@PathParam("st") String st) {
		return personDao.findByLastName(st);
	}
	
	@GET
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	@Path("/position/{st}")
	public List<Person> findByPosition(@PathParam("st") String st) {
		return personDao.findByPosition(st);
	}
	
	@POST
	@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	@Path("/add")
	public Person addJSONStaff(Person person) {
		return personDao.addStaff(person);
	}
	
	@PUT
    @Path("/update")
	@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Person updateStaff(Person person) {
		if(person.getId() <= 0) {
			return null;
		} else {
			return personDao.updateStaff(person);
		}
    }
	
	@DELETE
    @Path("/remove/{id}")
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public void removeStaff(@PathParam("id") int id) {
		personDao.removeStaff(id);
    }
	
	
}
