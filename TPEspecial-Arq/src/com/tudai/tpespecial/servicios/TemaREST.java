package com.tudai.tpespecial.servicios;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.tudai.tpespecial.entidades.Paper;
import com.tudai.tpespecial.entidades.Tema;
import com.tudai.tpespecial.entidades.Usuario;


public class TemaREST {

	public TemaREST() {
		// TODO Auto-generated constructor stub
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response createTema(Tema t) {
		Tema tr = TemaDAO.getInstance().persist(t);
		if(tr != null) {
			return Response.status(201).entity(t).build();
		}else {
			return Response.noContent().build();
		}
	}
	
	@DELETE
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response deleteTema(@PathParam("id") Integer id) {
		boolean result= TemaDAO.getInstance().delete(id);
		if(result) {
			return Response.status(201).build();
		}

		return Response.notModified().build();
	}
	
	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public static Tema getTemaPorId(@PathParam("id") Integer id) {
		//int id = Integer.valueOf(msg);
		Tema t = TemaDAO.getInstance().findById(id);
		if( t != null )
			return t;
		else
			return null;
	}
    
}
