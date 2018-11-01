package com.tudai.tpespecial.tests;

import java.io.IOException;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import org.junit.Test;
import com.tudai.tpespecial.entidades.Usuario;
import com.tudai.tpespecial.servicios.ServiciosPrimeraEntrega;

import junit.framework.TestCase;

public class TestPrueba extends TestCase {

	protected void setUp() throws Exception {
		super.setUp();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}
@SuppressWarnings("restriction")
@Test
	public final void testGetNombre() throws IOException {
		EntityManagerFactory emf = null;
		EntityManager emanager = null;
		String nombre = "Juan Pere";
		int id = 1;
		ArrayList<Usuario> ListaUsuarios = new ArrayList<Usuario>();{  
			try {
				emf = Persistence.createEntityManagerFactory("TPEspecial-Arquitectura");
				emanager = emf.createEntityManager();            		
				
				ListaUsuarios.add(ServiciosPrimeraEntrega.altaUsuario("Juan Perez", "Instituto 1", "AAA", 1999, false, true, false, emanager));
				ListaUsuarios.add(ServiciosPrimeraEntrega.altaUsuario("Jose Lopez", "Instituto 2", "BBB", 2005, true, false, false, emanager));
				ListaUsuarios.add(ServiciosPrimeraEntrega.altaUsuario("Mario Gonzalez", "Instituto 3", "CCC", 2005, true, false, false, emanager));
				ListaUsuarios.add(ServiciosPrimeraEntrega.altaUsuario("Raul Estevez", "Instituto 1", "AAA", 1999, false, true, true, emanager));
				ListaUsuarios.add(ServiciosPrimeraEntrega.altaUsuario("Jose Lopez", "Instituto 4", "BBB", 2005, true, false, true, emanager));
				ListaUsuarios.add(ServiciosPrimeraEntrega.altaUsuario("Luis Ramirez", "Instituto 5", "CCC", 2005, true, true, false, emanager));
				ListaUsuarios.add(ServiciosPrimeraEntrega.altaUsuario("Carlos Sanchez", "Instituto 3", "AAA", 1999, false, true, true, emanager));
				ListaUsuarios.add(ServiciosPrimeraEntrega.altaUsuario("Pablo Gutierrez", "Instituto 2", "BBB", 2005, true, false, false, emanager));
				ListaUsuarios.add(ServiciosPrimeraEntrega.altaUsuario("Ariel Gomez", "Instituto 4", "CCC", 2005, true, true, true, emanager));
				ListaUsuarios.add(ServiciosPrimeraEntrega.altaUsuario("Juan Martinez", "Instituto 1", "AAA", 1999, false, true, true, emanager));
		
			
			
		String sql = "SELECT nombre FROM tabla_usuarios WHERE idUsuario=" +id;
	    Query query = emanager.createNativeQuery(sql);
	    String resultado = query.getSingleResult().toString();
		assertEquals(nombre, resultado); 
	    
	   // System.out.println("nombre ingresado: " + nombre + ". Nombre en db: " + resultado);
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

}}
