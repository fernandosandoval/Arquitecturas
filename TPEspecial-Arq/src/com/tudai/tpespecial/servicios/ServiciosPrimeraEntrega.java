package com.tudai.tpespecial.servicios;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.persistence.EntityManager;

import com.tudai.tpespecial.entidades.Paper;
import com.tudai.tpespecial.entidades.Revision;
import com.tudai.tpespecial.entidades.Tema;
import com.tudai.tpespecial.entidades.Usuario;
import com.tudai.tpespecial.servicios.*;


public class ServiciosPrimeraEntrega {

	  public static Usuario altaUsuario(String nombre, String lugarTrabajo, String titulo, int anoEgreso, Boolean esEvaluador, Boolean esAutor, Boolean esExperto) {
	      UsuarioDAO u = new UsuarioDAO();	
	      Usuario usuario = new Usuario(nombre, lugarTrabajo, titulo, anoEgreso, esEvaluador, esAutor, esExperto);
	      u.persist(usuario);
	      return usuario;
	  }
	  
	  public static void asignarConocimiento(int idTema, int idUsuario) {
		  EntityManager emanager = EMF.createEntityManager();
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
	  
	  public static void bajaUsuario (int idUsuario) {
		  UsuarioDAO usuario = new UsuarioDAO();
		  usuario.delete(idUsuario);
	  }
	  
	  public static Usuario modificacionUsuario (int idUsuario, String nombre, String lugarTrabajo, String titulo, int anoEgreso, Boolean esEvaluador, Boolean esAutor, Boolean esExperto, EntityManager emanager) {  
	      Usuario usuario = emanager.find(Usuario.class, idUsuario);
	      if (usuario!= null) {
	    	  usuario.setNombre(nombre);
		      usuario.setLugarTrabajo(lugarTrabajo);
		      usuario.setTitulo(titulo);
		      usuario.setAnoEgreso(anoEgreso);
		      usuario.setEsAutor(esAutor);
		      usuario.setEsEvaluador(esEvaluador);
		      usuario.setEsExperto(esExperto);
		     UsuarioDAO u = new UsuarioDAO();
		     u.update(idUsuario, usuario);
	      }
	      return usuario;
	  }
	  
	  public static void obtenerUsuarioPorNombre(String nombre) {
		  
	  }
	             
      public static Paper altaPaper(String categoria) {  
    	  Paper paper = new Paper(); 
	      paper.setCategoría(categoria);
	      PaperDAO paperDAO = new PaperDAO();
	      paperDAO.persist(paper);
	      return paper;
	  }
      
      public static void bajaPaper (int idPaper) {
		  PaperDAO pd = new PaperDAO();
		  pd.delete(idPaper);
	      
	  }
      
      public static void asignarTemaAPaper(int idTema, int idPaper) {
    	  EntityManager emanager = EMF.createEntityManager();
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
	  
	  
	  private static Revision altaRevision(String texto, Calendar fecha, Paper paper) {
		  RevisionDAO rd = new RevisionDAO();
	      Revision revision = new Revision(texto, fecha); 
	      paper.getRevisiones().add(revision);
	      rd.persist(revision);
	   
	      return revision;
	  }
	  
	  public static void bajaRevision (int idRevision) {
		 RevisionDAO rd = new RevisionDAO();
		 rd.delete(idRevision);
	  }
	  
	  public static Tema altaTema (String texto) {  
	      Tema tema = new Tema(); 
	      tema.setTexto(texto);
	      TemaDAO td = new TemaDAO();
	      td.persist(tema);
	      return tema;
	  }
	  
      
	  
	  public static void asignarAutorAPaper(int idPaper, int idUsuario) {
		  EntityManager emanager = EMF.createEntityManager();
		  emanager.getTransaction().begin();
		  Usuario usuario = emanager.find(Usuario.class, idUsuario);
		  Paper paper = emanager.find(Paper.class, idPaper);
		  if ((usuario!= null)&&(paper!=null)) {
			//  usuario.addPaper(paper);
			//  paper.addAutor(usuario);
			  System.out.println("Se ha asignado el paper "+paper.getId()+" al autor "+usuario.getNombre());
			  emanager.flush();
		  }	  
    	  emanager.getTransaction().commit();
	      
	  }
	  
	  public static boolean asignarPaperARevisor(int idPaper, int idUsuario) {
		  return UsuarioDAO.getInstance().assignUserToPaper(idUsuario, idPaper);
	  }
	  
	  public static void verificarPosiblesEvaluadores(int idPaper) {
		  EntityManager emanager = EMF.createEntityManager();
		  		boolean valido = false;
		      List <Usuario> resultado = new ArrayList<>();
		      Paper paper = emanager.find(Paper.class, idPaper);
		      List<Tema> conoce = new ArrayList<>();
		      List<Tema> requiere = paper.getTemasTratados();
		      System.out.println("Buscando posibles evaluadores que conozcan sobre los temas:");
		      for (Tema tema: requiere) {
		    	  System.out.println(tema.getTexto());
		      }
		      List<Usuario> usuarios = OtrosServicios.buscarTodosLosUsuarios(emanager);
		      // tenemos a todos los evaluadores, ahora por cada uno verificamos si su lista de temas conocidos satisface al paper
		      for (Usuario usuario: usuarios) {
		    	   conoce = usuario.getTemasConocidos();
		    	   if(usuario.getPapers().contains(paper)) {
					    System.out.println("El usuario "+usuario.getNombre()+" es autor del paper "+idPaper+" y no puede ser revisor del mismo");
	                    valido = false;			  
				  }
				   
				  //verifico si el evaluador posee el conocimiento para poder evaluar el paper
				  //si el paper es poster, con uno solo alcanza
				  if(paper.getCategoría()=="Poster") {
				      for (Tema tema: requiere) {
				  	       if (conoce.contains(tema)) {
						      valido = true;
						      System.out.println("Se ha encontrado el tema "+tema.getTexto()+" que es conocido por el evaluador. Como el paper es un poster, el conocimiento es suficiente");
					       } 			  
		              }
				    if(valido)
				      resultado.add(usuario);	    
				  }	  
				  //si el paper no es poster, tiene que cumplir con cada uno de los temas que el evaluador conoce 
				  valido = true;
				  if(paper.getCategoría()!="Poster") {
				      for (Tema tema: requiere) {
				    	  System.out.println("Buscando en la lista de conocimientos del revisor "+usuario.getNombre()+ " el tema "+tema.getTexto());
				  	       if (!conoce.contains(tema)) {
				  	    	   valido = false;
				  	    	 System.out.println("Se ha encontrado el tema "+tema.getTexto()+" que es no conocido por el evaluador. Como el paper es "+paper.getCategoría()+", el conocimiento minimo no cumple con los requisitos solicitados");
					       } 			  
		              }
				      if(valido)
					      resultado.add(usuario);    
				  }
  
		      }	  // aqui termina el foreach de usuarios 
		  // muestro la lista de usuarios resultante
		  System.out.println("Los usuarios que pueden asignarse al paper "+paper.getId()+" son:");    
		  for (Usuario usuario : resultado) {
			  System.out.println(usuario.getNombre());
		  }
	  }

	  public static void verificarPosiblesPapers(int idUsuario) {
		  EntityManager emanager = EMF.createEntityManager();
		  boolean valido = false;
	      List <Paper> resultado = new ArrayList<>();
	      Usuario usuario = emanager.find(Usuario.class, idUsuario);
	      List<Tema> conoce = usuario.getTemasConocidos();
	      List<Tema> requiere = new ArrayList<>();
	      System.out.println("Buscando posibles trabajos (papers) que traten sobre los temas:");
	      for (Tema tema: conoce) {
	    	  System.out.println(tema.getTexto());
	      }
	      List<Paper> papers = OtrosServicios.buscarTodosLosPapers(emanager);
	      // tenemos a todos los papers, ahora por cada uno verificamos si su lista de temas conocidos satisface al evaluador
	      for (Paper paper : papers) {
	    	   requiere = paper.getTemasTratados();
	    	   if(paper.getAutores().contains(usuario)) {
				    System.out.println("El paper "+paper.getId()+" tiene por autor del paper "+usuario.getNombre()+" y no puede ser revisor del mismo");
                    valido = false;			  
			  }
	    	  else { 
					  //verifico si el evaluador posee el conocimiento para poder evaluar el paper
					  //si el paper es poster, con uno solo alcanza
					  if(paper.getCategoría()=="Poster") {
					      for (Tema tema: requiere) {
					  	       if (conoce.contains(tema)) {
							      valido = true;
							      System.out.println("Se ha encontrado el tema "+tema.getTexto()+" que es conocido por el evaluador. Como el paper es un poster, el conocimiento es suficiente");
						       } 			  
			              }
					    if(valido)
					      resultado.add(paper);	    
					  }	  
					  //si el paper no es poster, tiene que cumplir con cada uno de los temas que el evaluador conoce 
					  valido = true;
					  if(paper.getCategoría()!="Poster") {
					      for (Tema tema: requiere) {
					    	  System.out.println("Buscando en la lista de conocimientos del revisor "+usuario.getNombre()+ " el tema "+tema.getTexto());
					  	       if (!conoce.contains(tema)) {
					  	    	   valido = false;
					  	    	 System.out.println("Se ha encontrado el tema "+tema.getTexto()+" que es no conocido por el evaluador. Como el paper es "+paper.getCategoría()+", el conocimiento minimo no cumple con los requisitos solicitados");
						       }
					  	       else
					  	    	 System.out.println("El tema "+tema.getTexto()+" está en la lista de conocimiento del revisor");
			              }
					      if(valido)
						      resultado.add(paper);    
					  }
	    	  }
	      }	  // aqui termina el foreach de papers 
	  // muestro la lista de papers resultante
	  if (resultado.size()== 0) {
		  System.out.println("No hay papers que puedan asignarse al evaluador "+usuario.getNombre());
	  }
	  else
	      {
			  System.out.println("Los papers que pueden asignarse al evaluador "+usuario.getNombre()+" son:");    
			  for (Paper paper : resultado) {
				  System.out.println(paper.getId());
			  }
	      }	  
  }
	    	  
	  public static void getAutoresPorPaper(int idPaper) {
		  EntityManager emanager = EMF.createEntityManager();
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
	  
	  public static List<Paper> getTrabajosPorEvaluador (int idUsuario){
		  EntityManager emanager = EMF.createEntityManager();
		  Usuario usuario = emanager.find(Usuario.class, idUsuario);
		  List<Paper> res = new ArrayList<>();
		  res = usuario.getTrabajosAsignados();
		  return res;
	  }
	  
	  public static List<Paper> getTrabajosPorAutor (int idUsuario){
		  EntityManager emanager = EMF.createEntityManager();
		  Usuario usuario = emanager.find(Usuario.class, idUsuario);
		  List<Paper> res = new ArrayList<>();
		  res = usuario.getPapers();
		  return res;
	  }
	  
	  
	  
	  public static void getDatosUsuario(int idUsuario) {
		  Usuario res = new Usuario();
		  res = UsuarioREST.getUsuarioPorId(idUsuario);
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
	  
	  public static void getTipoEvaluador(int idUsuario) {
		  EntityManager emanager = EMF.createEntityManager();
		  Usuario res = new Usuario();
		  res = OtrosServicios.getUsuarioPorId(idUsuario, emanager);
		  if(res.isEsAutor()) {
			  System.out.println("El usuario "+res.getNombre()+" es autor");
		  }
		  if(res.isEsEvaluador()) {
			  System.out.println("El usuario "+res.getNombre()+"es evaluador");
		  }
		  if(res.isEsExperto()) {
			  System.out.println("El usuario "+res.getNombre()+"es experto");
		  }
	  }
	  
	  public static void obtenerRevisionesPorRevisor(int idUsuario) {
		  EntityManager emanager = EMF.createEntityManager();
		  List<Revision> res = new ArrayList<>();
		  res = OtrosServicios.getRevisionesPorRevisor(idUsuario, emanager);
	      System.out.println("Las revisiones del revisor "+idUsuario+" son:");
	      for (Revision r: res) {
	    	  System.out.println(r.getId());
	      }
	  }
	  
	  public static void obtenerTrabajosAsignadosPorRevisor(int idUsuario) {
		  EntityManager emanager = EMF.createEntityManager();
		  List<Revision> res = new ArrayList<>();
		  res = OtrosServicios.getTrabajosAsignadosPorRevisor(idUsuario, emanager);
	      System.out.println("Los trabajos asignados al revisor "+idUsuario+" son:");
	      for (Revision r: res) {
	    	  System.out.println(r.getId());
	      }
	  }
	  
	  public static void getRevisionesPorRevisoryFecha(int idUsuario, Date fechaInicio, Date fechaFin) {
		  List<Revision> res = new ArrayList<>();
		  EntityManager emanager = EMF.createEntityManager();
		  res = OtrosServicios.buscarTodosLasRevisionesPorRevisorYFecha(idUsuario, fechaInicio, fechaFin, emanager);
          System.out.println("Las revisiones del revisor "+idUsuario+" entre "+fechaInicio+ " y "+fechaFin+" son:");	  
	      for (Revision r : res ) {
	    	  System.out.println(r.getId());
	      }
	  }

	public static Usuario obtenerUsuarioPorId(int id) {
		
		return UsuarioDAO.getInstance().findById(id);
	}
}	  
	  