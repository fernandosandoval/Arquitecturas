package com.tudai.tpespecial.servicios;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.tudai.tpespecial.entidades.Revision;
import com.tudai.tpespecial.entidades.Usuario;

public class RevisionDAO implements DAO<Revision, Integer> {
	private static RevisionDAO revision;
	public RevisionDAO() {
		
	}
	public static RevisionDAO getInstance() {
		if(revision == null) {
			revision = new RevisionDAO();
		}
		return revision;
}
	@Override
	public Revision persist(Revision rev) {
		EntityManager emanager = EMF.createEntityManager();
		emanager.getTransaction().begin();
		emanager.persist(rev);
	    emanager.getTransaction().commit();
	    emanager.close();
		return rev;
	}

	@Override
	public Revision update(Integer id, Revision rev) {
		EntityManager emanager = EMF.createEntityManager();
		if(emanager.find(Revision.class, id) != null) {
			emanager.getTransaction().begin();
			emanager.merge(rev);
		    emanager.getTransaction().commit();
		    emanager.close();
			return rev;
		}
		
		return null;
	}

	@Override
	public Revision findById(Integer id) {
		EntityManager emanager = EMF.createEntityManager();
		return emanager.find(Revision.class, id);
	}

	@Override
	public List<Revision> findAll() {
		EntityManager emanager = EMF.createEntityManager();
		Query query = emanager.createNamedQuery("SELECT r FROM Revision r");
		emanager.close();
		return query.getResultList();
	}

	@Override
	public boolean delete(Integer id) {
		EntityManager emanager = EMF.createEntityManager();
		if(emanager.find(Revision.class, id) != null) {
			emanager.getTransaction().begin();
			emanager.remove(revision);
		    emanager.getTransaction().commit();
		    emanager.close();
		}
		if(emanager.find(Revision.class, id) != null) { //comprobamos que no esté más
			return false;
		}
		return true;
	}

}
