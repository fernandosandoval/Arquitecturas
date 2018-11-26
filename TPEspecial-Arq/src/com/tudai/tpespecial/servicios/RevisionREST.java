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
import com.tudai.tpespecial.entidades.Revision;
import com.tudai.tpespecial.entidades.Usuario;

public class RevisionREST {

	public RevisionREST() {
		// TODO Auto-generated constructor stub
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response createRevision(Revision r) {
		Revision rr = RevisionDAO.getInstance().persist(r);
		if(rr != null) {
			return Response.status(201).entity(r).build();
		}else {
			return Response.noContent().build();
		}
	}
	
	@DELETE
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response deleteRevision(@PathParam("id") Integer id) {
		boolean result= RevisionDAO.getInstance().delete(id);
		if(result) {
			return Response.status(201).build();
		}

		return Response.notModified().build();
	}
	
	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public static Revision getRevisionPorId(@PathParam("id") Integer id) {
		//int id = Integer.valueOf(msg);
		Revision r = RevisionDAO.getInstance().findById(id);
		if( r != null )
			return r;
		else
			return null;
	}
	
	@PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public static Response updateRevision(@PathParam("id") Integer id, Revision revision) {
    	Revision rr = RevisionDAO.getInstance().update(id, revision);
	    if (rr == null) {
	    	return Response.notModified().build();	
	    }
	    else {
	    	return Response.status(201).build();
	    }
    }
	
}
