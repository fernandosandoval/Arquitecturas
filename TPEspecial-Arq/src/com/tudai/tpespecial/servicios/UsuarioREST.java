package com.tudai.tpespecial.servicios;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.fasterxml.jackson.databind.node.ObjectNode;

import com.tudai.tpespecial.entidades.Usuario;

public class UsuarioREST {

	public UsuarioREST() {
		// TODO Auto-generated constructor stub
	}
	
	
	@DELETE
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response deleteUser(@PathParam("id") Integer id) {
		boolean result= UsuarioDAO.getInstance().delete(id);
		if(result) {
			return Response.status(201).build();
		}

		return Response.notModified().build();
	}
	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public static Usuario getUsuarioPorId(@PathParam("id") Integer id) {
		//int id = Integer.valueOf(msg);
		Usuario u = UsuarioDAO.getInstance().findById(id);
		if( u != null )
			return u;
		else
			return null;
	}

}