package com.tudai.tpespecial.servicios;

import java.sql.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.tudai.tpespecial.entidades.Paper;
import com.tudai.tpespecial.entidades.Tema;
import com.tudai.tpespecial.entidades.Usuario;
import com.tudai.tpespecial.entidades.Revision;

public class UsuarioDAO implements DAO<Usuario, Integer>{
	private static UsuarioDAO usuario;
	public UsuarioDAO() {
		
	}
	
	public static UsuarioDAO getInstance() {
			if(usuario == null) {
				usuario = new UsuarioDAO();
			}
			return usuario;
	}
	@Override
	public Usuario persist(Usuario usuario) {
		EntityManager emanager = EMF.createEntityManager();
		emanager.getTransaction().begin();
		emanager.persist(usuario);
	    emanager.getTransaction().commit();
	    emanager.close();
		return usuario;
	}

	@Override
	public Usuario update(Integer id, Usuario usuario) {
		EntityManager emanager = EMF.createEntityManager();
		if(emanager.find(Usuario.class, id) != null) {
			emanager.getTransaction().begin();
			emanager.merge(usuario);
		    emanager.getTransaction().commit();
		    emanager.close();
			return usuario;
		}
		
		return null;
	}

	@Override
	public Usuario findById(Integer id) {
		EntityManager emanager = EMF.createEntityManager();
		return emanager.find(Usuario.class, id);
	}

	@Override
	public List<Usuario> findAll() {
		EntityManager emanager = EMF.createEntityManager();
		Query query = emanager.createNamedQuery("SELECT u FROM Usuario u");
		emanager.close();
		return query.getResultList();
	}

	@Override
	public boolean delete(Integer id) {
		EntityManager emanager = EMF.createEntityManager();
		if(emanager.find(Usuario.class, id) != null) {
			emanager.getTransaction().begin();
			emanager.remove(usuario);
		    emanager.getTransaction().commit();
		    emanager.close();
		}
		if(emanager.find(Usuario.class, id) != null) { //comprobamos que no esté más
			return false;
		}
		return true;
	}
	public List<Paper> findAllPapers(Integer idUsuario) {
		EntityManager emanager = EMF.createEntityManager();
		Query query = emanager.createNamedQuery("SELECT t.idPaper FROM tabla_papers_tabla_usuarios t WHERE idUsuario = ?");
		emanager.close();
		return query.getResultList();
	}
	
	public List<Revision> findRevisionesPorFechas(Integer idUsuario, Date fechaInicio, Date fechaFin) {
		EntityManager emanager = EMF.createEntityManager();
		Query query = emanager.createNamedQuery("select r from Revisiones r where r.fecha >= \" +fechaInicio+\" and r.fecha <=\"+fechaFin+\" and r.ID_US = \"+idUsuario+\"");
		emanager.close();
		return query.getResultList();
	}
	
	public boolean assignUserToPaper(Integer idUsuario, Integer idPaper) {
		EntityManager emanager = EMF.createEntityManager();
		emanager.getTransaction().begin();
		boolean encontrado = false;
		Usuario usuario = emanager.find(Usuario.class, idUsuario);
	    Paper paper = emanager.find(Paper.class, idPaper);
	    List<Tema> conoce = usuario.getTemasConocidos();
		List<Tema> requiere = paper.getTemasTratados();
		System.out.println("Intentando asignar paper de categoria "+paper.getCategoría());
		for (Tema tema: requiere) {
		System.out.println("Este paper requiere conocimiento en "+tema.getTexto());
		}
			//verifico si el usuario es autor del paper
		if(usuario.getPapers().contains(paper)) {
			    System.out.println("El usuario "+usuario.getNombre()+" es autor del paper "+idPaper+" y no puede ser revisor del mismo");
		           return encontrado;			  
			  }
			   
			  //verifico si el evaluador posee el conocimiento para poder evaluar el paper
			  //si el paper es poster, con uno solo alcanza
			  if(paper.getCategoría()=="Poster") {
			      for (Tema tema: requiere) {
			  	       if (conoce.contains(tema)) {
					      encontrado = true;
					      System.out.println("Se ha encontrado el tema "+tema.getTexto()+" que es conocido por el evaluador. Como el paper es un poster, el conocimiento es suficiente");
				       } 			  
		          }
			    if(!encontrado)
			      return encontrado;	    
			  }	  
			  //si el paper no es poster, tiene que cumplir con cada uno de los temas que el evaluador conoce 
			  encontrado = true;
			  if(paper.getCategoría()!="Poster") {
			      for (Tema tema: requiere) {
			    	  System.out.println("Buscando en la lista de conocimientos del revisor "+usuario.getNombre()+ " el tema "+tema.getTexto());
			  	       if (!conoce.contains(tema)) {
			  	    	   encontrado = false;
			  	    	 System.out.println("Se ha encontrado el tema "+tema.getTexto()+" que es no conocido por el evaluador. Como el paper es "+paper.getCategoría()+", el conocimiento minimo no cumple con los requisitos solicitados");
				       } 			  
		          }
			      if(!encontrado)
				      return encontrado;    
			  }
			  //si se llega hasta aqui es que cumple con las condiciones previas, por lo tanto agregamos el revisor a la lista de papers y viceversa
			 // usuario.addTrabajoAsignado(paper);
		  paper.addRevisor(usuario);
		  System.out.println("El revisor "+usuario.getNombre()+" cumple con los requisitos y se le ha asignado el paper "+paper.getId());
		  //emanager.flush();			  
		  emanager.getTransaction().commit();
		  return true;
	}
}
