package com.tudai.tpespecial;
import java.util.ArrayList;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.tudai.tpespecial.entidades.Usuario;
import com.tudai.tpespecial.entidades.Paper;
import com.tudai.tpespecial.servicios.OtrosServicios;
import com.tudai.tpespecial.servicios.ServiciosPrimeraEntrega;

public class Main {
		public static void main (String [] args){
			
			EntityManagerFactory emf = null;
			EntityManager emanager = null;
			
			try {
				emf = Persistence.createEntityManagerFactory("TPEspecial-Arquitecturas");
				emanager = emf.createEntityManager();
				// creo usuarios
				ArrayList<Usuario> ListaUsuarios = new ArrayList<Usuario>();  
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
				//creo papers
				ArrayList<Paper> ListaPapers = new ArrayList<Paper>();
				ListaPapers.add(ServiciosPrimeraEntrega.altaPaper("Articulo", emanager));
				ListaPapers.add(ServiciosPrimeraEntrega.altaPaper("Resumen", emanager));
				ListaPapers.add(ServiciosPrimeraEntrega.altaPaper("Poster", emanager));
				ListaPapers.add(ServiciosPrimeraEntrega.altaPaper("Articulo", emanager));
				ListaPapers.add(ServiciosPrimeraEntrega.altaPaper("Resumen", emanager));
				ListaPapers.add(ServiciosPrimeraEntrega.altaPaper("Poster", emanager));
				ListaPapers.add(ServiciosPrimeraEntrega.altaPaper("Articulo", emanager));
				ListaPapers.add(ServiciosPrimeraEntrega.altaPaper("Resumen", emanager));
				ListaPapers.add(ServiciosPrimeraEntrega.altaPaper("Poster", emanager));
				ListaPapers.add(ServiciosPrimeraEntrega.altaPaper("Articulo", emanager));
				//creo temas
				ServiciosPrimeraEntrega.altaTema("Algoritmos", emanager);
				ServiciosPrimeraEntrega.altaTema("Lenguajes", emanager);
				ServiciosPrimeraEntrega.altaTema("Bases de Datos", emanager);
				ServiciosPrimeraEntrega.altaTema("Algebra", emanager);
				ServiciosPrimeraEntrega.altaTema("Java", emanager);
				ServiciosPrimeraEntrega.altaTema("Objetos", emanager);
				ServiciosPrimeraEntrega.altaTema("Agentes", emanager);
				ServiciosPrimeraEntrega.altaTema("Visualizaci�n", emanager);
				ServiciosPrimeraEntrega.altaTema("Redes", emanager);
				ServiciosPrimeraEntrega.altaTema("Metodolog�as", emanager);
				//asigno conocimiento de temas a revisores
				ServiciosPrimeraEntrega.asignarConocimiento(21, 1, emanager);
				ServiciosPrimeraEntrega.asignarConocimiento(22, 1, emanager);
				ServiciosPrimeraEntrega.asignarConocimiento(23, 1, emanager);
				//se repite tema para verificar si es aceptado
				ServiciosPrimeraEntrega.asignarConocimiento(21, 1, emanager);
				ServiciosPrimeraEntrega.asignarConocimiento(24, 2, emanager);
				ServiciosPrimeraEntrega.asignarConocimiento(25, 2, emanager);
				ServiciosPrimeraEntrega.asignarConocimiento(26, 2, emanager);
			    ServiciosPrimeraEntrega.asignarTemaAPaper(21, 11, emanager);
			    ServiciosPrimeraEntrega.asignarTemaAPaper(22, 11, emanager);
			    ServiciosPrimeraEntrega.asignarTemaAPaper(23, 11, emanager);
			    //asigno papers a usuarios autores
				ServiciosPrimeraEntrega.asignarAutorAPaper(11, 1, emanager);		
				ServiciosPrimeraEntrega.asignarAutorAPaper(12, 2, emanager);
				//asigno papers a usuarios revisores
				ServiciosPrimeraEntrega.asignarPaperARevisor(ListaPapers.get(0).getId(), ListaUsuarios.get(0).getId(), emanager);		
//				ServiciosPrimeraEntrega.asignarPaperARevisor(ListaPapers.get(0).getId(), ListaUsuarios.get(1).getId(), emanager);
//				ServiciosPrimeraEntrega.asignarPaperARevisor(ListaPapers.get(1).getId(), ListaUsuarios.get(2).getId(), emanager);		
//				ServiciosPrimeraEntrega.asignarPaperARevisor(ListaPapers.get(1).getId(), ListaUsuarios.get(3).getId(), emanager);
//				ServiciosPrimeraEntrega.asignarPaperARevisor(ListaPapers.get(2).getId(), ListaUsuarios.get(4).getId(), emanager);		
//				ServiciosPrimeraEntrega.asignarPaperARevisor(ListaPapers.get(2).getId(), ListaUsuarios.get(5).getId(), emanager);
//				ServiciosPrimeraEntrega.asignarPaperARevisor(ListaPapers.get(2).getId(), ListaUsuarios.get(4).getId(), emanager);
//				ServiciosPrimeraEntrega.asignarPaperARevisor(ListaPapers.get(2).getId(), ListaUsuarios.get(5).getId(), emanager);
//				ServiciosPrimeraEntrega.asignarPaperARevisor(ListaPapers.get(2).getId(), ListaUsuarios.get(0).getId(), emanager);
//				ServiciosPrimeraEntrega.asignarPaperARevisor(ListaPapers.get(2).getId(), ListaUsuarios.get(1).getId(), emanager);
//				ServiciosPrimeraEntrega.asignarPaperARevisor(ListaPapers.get(2).getId(), ListaUsuarios.get(2).getId(), emanager);
//				ServiciosPrimeraEntrega.asignarPaperARevisor(ListaPapers.get(2).getId(), ListaUsuarios.get(3).getId(), emanager);	
				//busco usuarios por paper
			//	ServiciosPrimeraEntrega.getAutoresPorPaper(12, emanager);
				//busco datos completos del usuario
				ServiciosPrimeraEntrega.getDatosUsuario(1, emanager);
				//busco posibles evaluadores por paper
				ServiciosPrimeraEntrega.verificarPosiblesEvaluadores(11, emanager);
				//busco posibles papers por evaluador
				ServiciosPrimeraEntrega.verificarPosiblesPapers(1, emanager);
				
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