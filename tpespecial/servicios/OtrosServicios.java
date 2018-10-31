package com.tudai.tpespecial.servicios;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import com.tudai.tpespecial.entidades.Usuario;
import com.tudai.tpespecial.entidades.Revision;
import com.tudai.tpespecial.entidades.Tema;
import com.tudai.tpespecial.entidades.Paper;

public class OtrosServicios {

	public static List<Usuario> buscarTodosLosUsuarios(EntityManager emanager){
          String jpql = "SELECT u FROM Usuario u";
          Query query = emanager.createQuery(jpql);
          List<Usuario> resultados = query.getResultList();
          return resultados;
	}
	
	public static List<Paper> buscarTodosLosPapers(EntityManager emanager){
        String jpql = "SELECT p FROM Paper p";
        Query query = emanager.createQuery(jpql);
        List<Paper> resultados = query.getResultList();
        return resultados;
	}
	
	public static List<Usuario> buscarTodosLosUsuariosPorPaper(int idPaper, EntityManager emanager){
          String sql = "SELECT * from tabla_papers_tabla_usuarios t JOIN tabla_usuarios u on t.usuarios_idUsuario = u.idUsuario WHERE t.papers_idPaper = ?1";   
          Query query = emanager.createNativeQuery(sql, Usuario.class);  
          query.setParameter("1", idPaper);
          List<Usuario> resultados = query.getResultList();
          return resultados;
	}      
	
	
	public static Usuario getUsuarioPorId(int idUsuario, EntityManager emanager) {
    	  String jpql = "SELECT u FROM Usuario u WHERE u.idUsuario = ?1";
          Query query = emanager.createQuery(jpql);
          query.setParameter("1", idUsuario);
          Usuario resultado = (Usuario) query.getSingleResult(); //query.getResultList(); 
		  return resultado;
	}
	
	public static List<Revision> getRevisionesPorRevisor(int idUsuario, EntityManager emanager) {
		  String jpql = "SELECT revisiones from Usuario u WHERE idUsuario = ?1";   
	      Query query = emanager.createQuery(jpql);  
	      query.setParameter("1", idUsuario);
	      List<Revision> resultados = query.getResultList();
	      return resultados;
	}
	
	public static List<Revision> getTrabajosAsignadosPorRevisor(int idUsuario, EntityManager emanager) {
		  String jpql = "SELECT trabajosAsignados from Usuario u WHERE idUsuario = ?1";   
	      Query query = emanager.createQuery(jpql);  
	      query.setParameter("1", idUsuario);
	      List<Revision> resultados = query.getResultList();
	      return resultados;
	}
	
//	public static List<Tema> getTemasPorTrabajo(int idPaper, EntityManager emanager) {
//		  String jpql = "SELECT temasTratados from Paper p WHERE idPaper = ?1";   
//	      Query query = emanager.createQuery(jpql);  
//	      query.setParameter("1", idPaper);
//	      List<Tema> resultados = query.getResultList();
//	      return resultados;
//	}

}
