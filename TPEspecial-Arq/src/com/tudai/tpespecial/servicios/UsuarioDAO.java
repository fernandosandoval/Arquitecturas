package com.tudai.tpespecial.servicios;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.tudai.tpespecial.entidades.Usuario;

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

}
