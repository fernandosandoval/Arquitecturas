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
				//creo papers
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
				//creo temas
				System.out.println("-------------X----------------");
				ServiciosPrimeraEntrega.altaTema("Algoritmos");
				ServiciosPrimeraEntrega.altaTema("Lenguajes");
				ServiciosPrimeraEntrega.altaTema("Bases de Datos");
				ServiciosPrimeraEntrega.altaTema("Algebra");
				ServiciosPrimeraEntrega.altaTema("Java");
				ServiciosPrimeraEntrega.altaTema("Objetos");
				System.out.println("-------------Y----------------");
				ServiciosPrimeraEntrega.altaTema("Agentes");
				ServiciosPrimeraEntrega.altaTema("Visualización");
				ServiciosPrimeraEntrega.altaTema("Redes");
				ServiciosPrimeraEntrega.altaTema("Metodologías");
				System.out.println("-------------Z----------------");
				//asigno conocimiento de temas a revisores
				ServiciosPrimeraEntrega.asignarConocimiento(1, 1);
				System.out.println("-------------ZZZ----------------");
				ServiciosPrimeraEntrega.asignarConocimiento(2, 1);
				ServiciosPrimeraEntrega.asignarConocimiento(3, 1);
				//se repite tema para verificar si es aceptado
				ServiciosPrimeraEntrega.asignarConocimiento(1, 1);
				ServiciosPrimeraEntrega.asignarConocimiento(4, 2);
				ServiciosPrimeraEntrega.asignarConocimiento(5, 2);
				ServiciosPrimeraEntrega.asignarConocimiento(6, 2);
			    ServiciosPrimeraEntrega.asignarTemaAPaper(1, 5);
			    ServiciosPrimeraEntrega.asignarTemaAPaper(2, 5);
			    ServiciosPrimeraEntrega.asignarTemaAPaper(3, 5);
			    //asigno papers a usuarios autores
				ServiciosPrimeraEntrega.asignarAutorAPaper(1, 1);		
				ServiciosPrimeraEntrega.asignarAutorAPaper(2, 2);
				//asigno papers a usuarios revisores
				ServiciosPrimeraEntrega.asignarPaperARevisor(ListaPapers.get(0).getId(), ListaUsuarios.get(0).getId());		
				System.err.println("***********************ACA***********************");
				System.out.println("--------------------1----------------");
				//				ServiciosPrimeraEntrega.asignarPaperARevisor(ListaPapers.get(0).getId(), ListaUsuarios.get(1).getId());
//				ServiciosPrimeraEntrega.asignarPaperARevisor(ListaPapers.get(1).getId(), ListaUsuarios.get(2).getId());		
//				ServiciosPrimeraEntrega.asignarPaperARevisor(ListaPapers.get(1).getId(), ListaUsuarios.get(3).getId());
//				ServiciosPrimeraEntrega.asignarPaperARevisor(ListaPapers.get(2).getId(), ListaUsuarios.get(4).getId());		
//				ServiciosPrimeraEntrega.asignarPaperARevisor(ListaPapers.get(2).getId(), ListaUsuarios.get(5).getId());
//				ServiciosPrimeraEntrega.asignarPaperARevisor(ListaPapers.get(2).getId(), ListaUsuarios.get(4).getId());
//				ServiciosPrimeraEntrega.asignarPaperARevisor(ListaPapers.get(2).getId(), ListaUsuarios.get(5).getId());
//				ServiciosPrimeraEntrega.asignarPaperARevisor(ListaPapers.get(2).getId(), ListaUsuarios.get(0).getId());
//				ServiciosPrimeraEntrega.asignarPaperARevisor(ListaPapers.get(2).getId(), ListaUsuarios.get(1).getId());
//				ServiciosPrimeraEntrega.asignarPaperARevisor(ListaPapers.get(2).getId(), ListaUsuarios.get(2).getId());
//				ServiciosPrimeraEntrega.asignarPaperARevisor(ListaPapers.get(2).getId(), ListaUsuarios.get(3).getId());	
				//busco usuarios por paper
			//	ServiciosPrimeraEntrega.getAutoresPorPaper(12);
				//busco datos completos del usuario
				ServiciosPrimeraEntrega.getDatosUsuario(1);
				System.out.println("--------------------2----------------");
				//busco posibles evaluadores por paper
				ServiciosPrimeraEntrega.verificarPosiblesEvaluadores(1);
				System.out.println("--------------------3----------------");
				//busco posibles papers por evaluador
				ServiciosPrimeraEntrega.verificarPosiblesPapers(1);
				System.out.println("--------------------4----------------");
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