package com.tudai.tpespecial.servicios;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.tudai.tpespecial.entidades.Paper;
import com.tudai.tpespecial.entidades.Usuario;

public class PaperDAO implements DAO<Paper, Integer>{
	private static PaperDAO paper;
	public PaperDAO() {
		// TODO Auto-generated constructor stub
	}
	public static PaperDAO getInstance() {
		if(paper == null) {
			paper = new PaperDAO();
		}
		return paper;
}
	@Override
	public Paper persist(Paper p) {
		EntityManager emanager = EMF.createEntityManager();
		emanager.getTransaction().begin();
		emanager.persist(p);
	    emanager.getTransaction().commit();
	    emanager.close();
		return p;
	}

	@Override
	public Paper update(Integer id, Paper p) {
		EntityManager emanager = EMF.createEntityManager();
		if(emanager.find(Paper.class, id) != null) {
			emanager.getTransaction().begin();
			emanager.merge(p);
		    emanager.getTransaction().commit();
		    emanager.close();
			return p;
		}
		
		return null;
	}

	@Override
	public Paper findById(Integer id) {
		EntityManager emanager = EMF.createEntityManager();
		return emanager.find(Paper.class, id);
	}

	@Override
	public List<Paper> findAll() {
		EntityManager emanager = EMF.createEntityManager();
		Query query = emanager.createNamedQuery("SELECT p FROM Paper p");
		emanager.close();
		return query.getResultList();
	}

	@Override
	public boolean delete(Integer id) {
		EntityManager emanager = EMF.createEntityManager();
		if(emanager.find(Paper.class, id) != null) {
			emanager.getTransaction().begin();
			emanager.remove(paper);
		    emanager.getTransaction().commit();
		    emanager.close();
		}
		if(emanager.find(Paper.class, id) != null) { //comprobamos que no esté más
			return false;
		}
		return true;
	}

}
