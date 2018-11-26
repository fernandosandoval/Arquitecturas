package com.tudai.tpespecial.servicios;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.tudai.tpespecial.entidades.Tema;
import com.tudai.tpespecial.entidades.Usuario;

public class TemaDAO implements DAO<Tema, Integer> {
	private static TemaDAO tema;
	public TemaDAO() {
		
	}
	public static TemaDAO getInstance() {
		if(tema == null) {
			tema = new TemaDAO();
		}
		return tema;
}
	@Override
	public Tema persist(Tema t) {
		EntityManager emanager = EMF.createEntityManager();
		System.out.println("-------------A----------------");
		emanager.getTransaction().begin();
		System.out.println("-------------B----------------");
		emanager.persist(t);
		System.out.println("-------------C----------------");
	    emanager.getTransaction().commit();
		System.out.println("-------------D----------------");
	    emanager.close();
		System.out.println("-------------E----------------");
		return t;
	}

	@Override
	public Tema update(Integer id, Tema t) {
		EntityManager emanager = EMF.createEntityManager();
		if(emanager.find(Tema.class, id) != null) {
			emanager.getTransaction().begin();
			emanager.merge(t);
		    emanager.getTransaction().commit();
		    emanager.close();
			return t;
		}
		
		return null;
	}

	@Override
	public Tema findById(Integer id) {
		EntityManager emanager = EMF.createEntityManager();
		return emanager.find(Tema.class, id);
	}

	@Override
	public List<Tema> findAll() {
		EntityManager emanager = EMF.createEntityManager();
		Query query = emanager.createNamedQuery("SELECT t FROM Tema t");
		emanager.close();
		return query.getResultList();
	}

	@Override
	public boolean delete(Integer id) {
		EntityManager emanager = EMF.createEntityManager();
		if(emanager.find(Tema.class, id) != null) {
			emanager.getTransaction().begin();
			emanager.remove(tema);
		    emanager.getTransaction().commit();
		    emanager.close();
		}
		if(emanager.find(Tema.class, id) != null) { //comprobamos que no esté más
			return false;
		}
		return true;
	}

}
