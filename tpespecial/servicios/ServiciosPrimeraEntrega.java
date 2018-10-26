package com.tudai.tpespecial.servicios;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.persistence.EntityManager;

import com.tudai.tpespecial.entidades.Paper;
import com.tudai.tpespecial.entidades.Revision;
import com.tudai.tpespecial.entidades.Tema;
import com.tudai.tpespecial.entidades.Usuario;
import com.tudai.tpespecial.servicios.OtrosServicios;


public class ServiciosPrimeraEntrega {

	  public static Usuario altaUsuario(String nombre, String lugarTrabajo, String titulo, int anoEgreso, Boolean esEvaluador, Boolean esAutor, Boolean esExperto, EntityManager emanager) {
	      emanager.getTransaction().begin();	
	      Usuario usuario = new Usuario();
	      usuario.setNombre(nombre);
	      usuario.setLugarTrabajo(lugarTrabajo);
	      usuario.setTitulo(titulo);
	      usuario.setAnoEgreso(anoEgreso);
	      usuario.setEsAutor(esAutor);
	      usuario.setEsEvaluador(esEvaluador);
	      usuario.setEsExperto(esExperto);
	      emanager.persist(usuario);
	      emanager.getTransaction().commit();
	      return usuario;
	  }
	  
	  public static void asignarConocimiento(Tema t, int idUsuario, EntityManager emanager) {
		  Usuario usuario = emanager.find(Usuario.class, idUsuario);
		  if (usuario!= null) {
			  usuario.addTemaConocido(t);
		  }
		  
	  }
	  
	  public static void bajaUsuario (int idUsuario, EntityManager emanager) {
		  Usuario usuario = emanager.find(Usuario.class, idUsuario);
	      if (usuario!= null) {
	    	  emanager.getTransaction().begin();
	    	  emanager.remove(usuario);
	    	  emanager.getTransaction().commit();
	      }
	  }
	  
	  public static Usuario modificacionUsuario (int idUsuario, String nombre, String lugarTrabajo, String titulo, int anoEgreso, Boolean esEvaluador, Boolean esAutor, Boolean esExperto, EntityManager emanager) {  
	      Usuario usuario = emanager.find(Usuario.class, idUsuario);
	      if (usuario!= null) {
	    	  emanager.getTransaction().begin();
	    	  usuario.setNombre(nombre);
		      usuario.setLugarTrabajo(lugarTrabajo);
		      usuario.setTitulo(titulo);
		      usuario.setAnoEgreso(anoEgreso);
		      usuario.setEsAutor(esAutor);
		      usuario.setEsEvaluador(esEvaluador);
		      usuario.setEsExperto(esExperto);
		      emanager.flush();
	    	  emanager.getTransaction().commit();
	      }
	      return usuario;
	  }
	  
	  public static void obtenerUsuarioPorNombre(String nombre) {
		  
	  }
	             
      public static Paper altaPaper(String categoria, EntityManager emanager) {  
	  
	      emanager.getTransaction().begin();	
	      Paper paper = new Paper(); 
	      paper.setCategoría(categoria);
	      emanager.persist(paper);
	      emanager.getTransaction().commit();
	      return paper;
	  }
      
      public static void bajaPaper (int idPaper, EntityManager emanager) {
		  Paper paper = emanager.find(Paper.class, idPaper);
	      if (paper!= null) {
	    	  emanager.getTransaction().begin();
	    	  emanager.remove(paper);
	    	  emanager.getTransaction().commit();
	      }
	  }
	  
	  
	  private static Revision altaRevision(String texto, Calendar fecha, Paper paper, EntityManager emanager) {
	    //  emanager.getTransaction().begin();	
	      Revision revision = new Revision(texto, fecha); 
	      paper.getRevisiones().add(revision);
	      emanager.persist(revision);
	    //  emanager.getTransaction().commit();
	      return revision;
	  }
	  
	  public static void bajaRevision (int idRevision, EntityManager emanager) {
		  Revision revision = emanager.find(Revision.class, idRevision);
	      if (revision!= null) {
	    	  emanager.getTransaction().begin();
	    	  emanager.remove(revision);
	    	  emanager.getTransaction().commit();
	      }
	  }
	  
	  public static void asignarUsuarioAPaper(int idPaper, int idUsuario, EntityManager emanager) {
		  emanager.getTransaction().begin();
		  Usuario usuario = emanager.find(Usuario.class, idUsuario);
		  Paper paper = emanager.find(Paper.class, idPaper);
		  if ((usuario!= null)&&(paper!=null)) {
			  usuario.addPaper(paper);
			  paper.addUsuario(usuario);
			  emanager.flush();
		  }	  
    	  emanager.getTransaction().commit();
	      
	  }
	  
	  public static boolean asignarPaperARevisor(int idPaper, int idUsuario, EntityManager emanager) {
		  	emanager.getTransaction().begin();
		  	Usuario usuario = emanager.find(Usuario.class, idUsuario);
			Paper paper = emanager.find(Paper.class, idPaper);
			  if(usuario.getPapers().contains(paper)) {
				    System.out.println("El usuario "+idUsuario+" es autor del paper "+idPaper+" y no puede ser revisor del mismo");
                    return false;			  
			  }
			  else 
				  if(usuario.getPapers().size() == 3) {
					    System.out.println("El usuario "+idUsuario+" ya posee tres papers de su autoría");
	                    return false;			  
				  }
				  else			  
			      {
						  if ((usuario!= null)&&(paper!=null)) {
							  altaRevision("Está bastante bien", Calendar.getInstance(), paper, emanager);
							  System.out.println("El usuario "+idUsuario+" ha sido asignado como revisor del paper "+idPaper);
							  System.out.println("El paper "+idPaper+ " tiene "+paper.getRevisiones().size()+" revisiones");
							  emanager.flush();
						  }
			      }	  
	    	  emanager.getTransaction().commit();
	    	  return true;
	  }
	
		  
	  public static void getUsuariosPorPaper(int idPaper, EntityManager emanager) {
		  List<Usuario> res = new ArrayList<>();
		  res = OtrosServicios.buscarTodosLosUsuariosPorPaper(idPaper, emanager);
	      System.out.println("Los autores del paper "+idPaper+" son:");
	      for (Usuario u: res) {
	    	  System.out.println(u.getId());
	      }
	  }
	  
	  public static void getDatosUsuario(int idUsuario, EntityManager emanager) {
		  Usuario res = new Usuario();
		  res = OtrosServicios.getUsuarioPorId(idUsuario, emanager);
		  System.out.println("Los datos del usuario con id "+idUsuario+" son:");
		  System.out.println("Nombre: "+res.getNombre());
		  System.out.println("Trabaja en: "+res.getLugarTrabajo());
		  System.out.println("Egresó el año: "+res.getAnoEgreso());
		  if(res.isEsAutor()) {
			  System.out.println("El usuario es autor");
		  }
		  if(res.isEsEvaluador()) {
			  System.out.println("El usuario es evaluador");
		  }
		  if(res.isEsExperto()) {
			  System.out.println("El usuario es experto");
		  }
	  }
	  
	  public static void obtenerRevisionesPorRevisor(int idUsuario, EntityManager emanager) {
		  List<Revision> res = new ArrayList<>();
		  res = OtrosServicios.getRevisionesPorRevisor(idUsuario, emanager);
	      System.out.println("Las revisiones del revisor "+idUsuario+" son:");
	      for (Revision r: res) {
	    	  System.out.println(r.getId());
	      }
	  }
	  
	  public static void obtenerTrabajosAsignadosPorRevisor(int idUsuario, EntityManager emanager) {
		  List<Revision> res = new ArrayList<>();
		  res = OtrosServicios.getTrabajosAsignadosPorRevisor(idUsuario, emanager);
	      System.out.println("Los trabajos asignados al revisor "+idUsuario+" son:");
	      for (Revision r: res) {
	    	  System.out.println(r.getId());
	      }
	  }
	  
	  
}	  
	  