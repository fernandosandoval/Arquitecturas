package com.tudai.tpespecial.tests;

import junit.framework.TestCase;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;

import static org.junit.Assert.assertEquals;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import org.junit.Test;

import com.tudai.tpespecial.entidades.Paper;
import com.tudai.tpespecial.entidades.Usuario;
import com.tudai.tpespecial.servicios.ServiciosPrimeraEntrega;

public class TestNoAsignarPapersACreador extends TestCase {

	protected void setUp() throws Exception {
		super.setUp();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}
@SuppressWarnings("restriction")
@Test
	public final void testAsignarRevisorAPaper() throws Exception  {
	EntityManagerFactory emf = null;
	EntityManager emanager = null;
	String nombre = "Nauj Zerep";
	int id = 1;
	ArrayList<Usuario> ListaUsuarios = new ArrayList<Usuario>();{  
		try {
			emf = Persistence.createEntityManagerFactory("TPEspecial-Arquitectura");
			emanager = emf.createEntityManager();            		
			
			ListaUsuarios.add(ServiciosPrimeraEntrega.altaUsuario("Juan Perez", "Instituto 1", "AAA", 1999, false, true, false));
			ListaUsuarios.add(ServiciosPrimeraEntrega.altaUsuario("Jose Lopez", "Instituto 2", "BBB", 2005, true, false, false));
			ListaUsuarios.add(ServiciosPrimeraEntrega.altaUsuario("Mario Gonzalez", "Instituto 3", "CCC", 2005, true, false, false));
			ListaUsuarios.add(ServiciosPrimeraEntrega.altaUsuario("Raul Estevez", "Instituto 1", "AAA", 1999, false, true, true));
			ListaUsuarios.add(ServiciosPrimeraEntrega.altaUsuario("Jose Lopez", "Instituto 4", "BBB", 2005, true, false, true));
			ListaUsuarios.add(ServiciosPrimeraEntrega.altaUsuario("Luis Ramirez", "Instituto 5", "CCC", 2005, true, true, false));
			ListaUsuarios.add(ServiciosPrimeraEntrega.altaUsuario("Carlos Sanchez", "Instituto 3", "AAA", 1999, false, true, true));
			ListaUsuarios.add(ServiciosPrimeraEntrega.altaUsuario("Pablo Gutierrez", "Instituto 2", "BBB", 2005, true, false, false));
			ListaUsuarios.add(ServiciosPrimeraEntrega.altaUsuario("Ariel Gomez", "Instituto 4", "CCC", 2005, true, true, true));
			ListaUsuarios.add(ServiciosPrimeraEntrega.altaUsuario("Juan Martinez", "Instituto 1", "AAA", 1999, false, true, true));
			ArrayList<Paper> ListaPapers = new ArrayList<Paper>();
			ListaPapers.add(ServiciosPrimeraEntrega.altaPaper("Articulo"));
			ListaPapers.add(ServiciosPrimeraEntrega.altaPaper("Resumen"));
			ListaPapers.add(ServiciosPrimeraEntrega.altaPaper("Poster"));
			ListaPapers.add(ServiciosPrimeraEntrega.altaPaper("Articulo"));
			ListaPapers.add(ServiciosPrimeraEntrega.altaPaper("Resumen"));
			ListaPapers.add(ServiciosPrimeraEntrega.altaPaper("Poster"));
			ListaPapers.add(ServiciosPrimeraEntrega.altaPaper("Articulo"));
			ListaPapers.add(ServiciosPrimeraEntrega.altaPaper("Resumen"));
			ListaPapers.add(ServiciosPrimeraEntrega.altaPaper("Poster"));
			ListaPapers.add(ServiciosPrimeraEntrega.altaPaper("Articulo"));
			ServiciosPrimeraEntrega.asignarAutorAPaper(ListaPapers.get(0).getId(), ListaUsuarios.get(0).getId());		
			ServiciosPrimeraEntrega.asignarAutorAPaper(ListaPapers.get(0).getId(), ListaUsuarios.get(1).getId());
			ServiciosPrimeraEntrega.asignarAutorAPaper(ListaPapers.get(1).getId(), ListaUsuarios.get(2).getId());		
			ServiciosPrimeraEntrega.asignarAutorAPaper(ListaPapers.get(1).getId(), ListaUsuarios.get(3).getId());
			ServiciosPrimeraEntrega.asignarAutorAPaper(ListaPapers.get(2).getId(), ListaUsuarios.get(4).getId());		
			ServiciosPrimeraEntrega.asignarAutorAPaper(ListaPapers.get(2).getId(), ListaUsuarios.get(5).getId());
	

			assertTrue(ServiciosPrimeraEntrega.asignarPaperARevisor(ListaPapers.get(0).getId(), ListaUsuarios.get(0).getId())); 
		
    

}
	catch (Exception e) {
		e.printStackTrace();
	}
	finally {
		if(emanager!=null) {
			emanager.close();
			emf.close();
		}
	}
	}

}

}
