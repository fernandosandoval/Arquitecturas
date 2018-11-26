package com.tudai.tpespecial.servicios;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.POST;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.WebApplicationException;

import com.tudai.tpespecial.entidades.Paper;
import com.tudai.tpespecial.entidades.Usuario;

public class PaperREST {

	public PaperREST() {
		// TODO Auto-generated constructor stub
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response createPaper(Paper p) {
		Paper pr = PaperDAO.getInstance().persist(p);
		if(pr != null) {
			return Response.status(201).entity(p).build();
		}else {
			return Response.noContent().build();
		}
	}
	
	
	@DELETE
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response deletePaper(@PathParam("id") Integer id) {
		boolean result= PaperDAO.getInstance().delete(id);
		if(result) {
			return Response.status(201).build();
		}

		return Response.notModified().build();
	}
	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public static Paper getPaperPorId(@PathParam("id") Integer id) {
		//int id = Integer.valueOf(msg);
		Paper p = PaperDAO.getInstance().findById(id);
		if( p != null )
			return p;
		else
			return null;
	}
}
