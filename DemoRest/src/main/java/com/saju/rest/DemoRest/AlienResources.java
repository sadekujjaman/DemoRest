package com.saju.rest.DemoRest;

import java.util.Arrays;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("aliens")
public class AlienResources {

	AlienRepository alienRepo = new AlienRepository();
	
	@GET
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public List<Alien> getAliens(){
		return alienRepo.getAliens();
	}
	
	@GET
	@Path("alien/{id}")
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public Alien getAlien(@PathParam("id")int id){
		return alienRepo.getAlien(id);
	}
	
	@POST
	@Path("alien")
	@Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public Alien createAlien(Alien alien){
		return alienRepo.create(alien);
	}
	
	@PUT
	@Path("alien")
	@Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public Alien updateAlien(Alien alien){
		if(alienRepo.getAlien(alien.getId()) == null){
			return alienRepo.create(alien);
		}
		return alienRepo.update(alien);
	}
	
	@DELETE
	@Path("alien/{id}")
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public Alien deleteAlien(@PathParam("id")int id){
		Alien alien = alienRepo.getAlien(id);
		if(alien != null){
			alienRepo.delete(id);
		}
		return alien;
	}
}
