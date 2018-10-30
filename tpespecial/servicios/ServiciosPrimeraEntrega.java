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
	  
	  public static void asignarConocimiento(int idTema, int idUsuario, EntityManager emanager) {
		  emanager.getTransaction().begin();
		  Usuario usuario = emanager.find(Usuario.class, idUsuario);
	      Tema tema = emanager.find(Tema.class, idTema);
		  if ((usuario!= null)&&(tema!= null)) {
			  if(usuario.addTemaConocido(tema)) {
			      System.out.println("Este revisor ("+usuario.getNombre()+") ahora tiene conocimiento sobre el tema "+tema.getTexto());
			      tema.addRevisor(usuario);
			  }
			  else {
				  System.out.println("Este revisor ya tenía conocimiento sobre el tema "+tema.getTexto());
			  }
			  emanager.flush();
		  }
		  emanager.getTransaction().commit();
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
      
      public static void asignarTemaAPaper(int idTema, int idPaper, EntityManager emanager) {
		  emanager.getTransaction().begin();
		  Paper paper = emanager.find(Paper.class, idPaper);
	      Tema tema = emanager.find(Tema.class, idTema);
		  if ((paper!= null)&&(tema!= null)) {
			      System.out.println("El paper "+paper.getId()+" trata sobre el tema "+tema.getTexto());
			      paper.addTemaTratado(tema);
			  }
			  else {
				  System.out.println("Este paper ya trata sobre el tema "+tema.getTexto());
			  }
		  emanager.flush();
		  emanager.getTransaction().commit();
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
	  
	  public static Tema altaTema (String texto, EntityManager emanager) {   
	      emanager.getTransaction().begin();	
	      Tema tema = new Tema(); 
	      tema.setTexto(texto);
	      emanager.persist(tema);
	      emanager.getTransaction().commit();
	      return tema;
	  }
      
	  
//	  public static void asignarUsuarioAPaper(int idPaper, int idUsuario, EntityManager emanager) {
//		  emanager.getTransaction().begin();
//		  Usuario usuario = emanager.find(Usuario.class, idUsuario);
//		  Paper paper = emanager.find(Paper.class, idPaper);
//		  if ((usuario!= null)&&(paper!=null)) {
//			  usuario.addPaper(paper);
//			  paper.addUsuario(usuario);
//			  emanager.flush();
//		  }	  
//    	  emanager.getTransaction().commit();
//	      
//	  }
	  
	  public static boolean asignarPaperARevisor(int idPaper, int idUsuario, EntityManager emanager) {
		  	
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
			    	  System.out.println("Buscando el tema "+tema.getTexto());
			  	       if (!conoce.contains(tema)) {
			  	    	   encontrado = false;
			  	    	 System.out.println("Se ha encontrado el tema "+tema.getTexto()+" que es no conocido por el evaluador. Como el paper es "+paper.getCategoría()+", el conocimiento minimo no cumple con los requisitos solicitados");
				       } 			  
	              }
			      if(!encontrado)
				      return encontrado;    
			  }
			  //si se llega hasta aqui es que cumple con las condiciones previas, por lo tanto agregamos el revisor a la lista de papers y viceversa
			  usuario.addPaper(paper);
			  paper.addUsuario(usuario);
			  System.out.println("El revisor "+usuario.getNombre()+" cumple con los requisitos y se le ha asignado el paper "+paper.getId());
			  emanager.flush();			  
	    	  emanager.getTransaction().commit();
	    	  return true;
	  }


//    if ((usuario!= null)&&(paper!=null)) {
//	  altaRevision(texto, Calendar.getInstance(), paper, emanager);
//	  System.out.println("El usuario "+usuario.getNombre()+" ha sido asignado como revisor del paper "+idPaper);
//	  System.out.println("El paper "+idPaper+ " tiene "+paper.getRevisiones().size()+" revisiones");
//				  emanager.flush();

	  
//	  public static boolean asignarPaperARevision(int idPaper, int idRevision, String texto, EntityManager emanager) {
//			//  	emanager.getTransaction().begin();
//			  	Revision Revision = emanager.find(Revision.class, idRevision);
//				Paper paper = emanager.find(Paper.class, idPaper);
//				  if(usuario.getPapers().contains(paper)) {
//					    System.out.println("El usuario "+usuario.getNombre()+" es autor del paper "+idPaper+" y no puede ser revisor del mismo");
//	                    return false;			  
//				  }
//				  else 
//					  if(paper.getRevisiones().size() == 3) {
//						    System.out.println("El paper "+idPaper+" ya posee tres papers de su autoría");
//		                    return false;			  
//					  }
//					  else			  
//				      {
//							  if ((usuario!= null)&&(paper!=null)) {
//								  altaRevision(texto, Calendar.getInstance(), paper, emanager);
//								  System.out.println("El usuario "+usuario.getNombre()+" ha sido asignado como revisor del paper "+idPaper);
//								  System.out.println("El paper "+idPaper+ " tiene "+paper.getRevisiones().size()+" revisiones");
//				//				  emanager.flush();
//							  }
//				      }	
//			//	  emanager.persist(idUsuario);
//			//	  emanager.persist(idPaper);
//		    //	  emanager.getTransaction().commit();
//		    	  return true;
//		  }
	
		  
	  public static void getUsuariosPorPaper(int idPaper, EntityManager emanager) {
		  List<Usuario> res = new ArrayList<>();
		  res = OtrosServicios.buscarTodosLosUsuariosPorPaper(idPaper, emanager);
		  if (res.size() == 0) {
			  System.out.println("El paper "+idPaper+" no tiene autor asignado");
		  }
		  else {
	      System.out.println("Los autores del paper "+idPaper+" son:");
	      for (Usuario u: res) {
	    	  System.out.println(u.getId());
	          }
		  }
	  }
	  
//	  public static List<Usuario> evaluadoresPorTrabajo (int idPaper, EntityManager emanager){
//		  List<Usuario> res = new ArrayList<Usuario>();
//		  List<Tema> temas = new ArrayList<Tema>();
//		  temas = OtrosServicios.getTemasPorPaper(idPaper, emanager);
//		  
//		  return res;
//	  }
//	  
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
	  