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
				ServiciosPrimeraEntrega.altaTema("Algoritmos");
				ServiciosPrimeraEntrega.altaTema("Lenguajes");
				ServiciosPrimeraEntrega.altaTema("Bases de Datos");
				ServiciosPrimeraEntrega.altaTema("Algebra");
				ServiciosPrimeraEntrega.altaTema("Java");
				ServiciosPrimeraEntrega.altaTema("Objetos");
				ServiciosPrimeraEntrega.altaTema("Agentes");
				ServiciosPrimeraEntrega.altaTema("Visualización");
				ServiciosPrimeraEntrega.altaTema("Redes");
				ServiciosPrimeraEntrega.altaTema("Metodologías");
				//asigno conocimiento de temas a revisores
				ServiciosPrimeraEntrega.asignarConocimiento(1, 1, emanager);
				ServiciosPrimeraEntrega.asignarConocimiento(2, 1, emanager);
				ServiciosPrimeraEntrega.asignarConocimiento(3, 1, emanager);
				//se repite tema para verificar si es aceptado
				ServiciosPrimeraEntrega.asignarConocimiento(1, 1, emanager);
				ServiciosPrimeraEntrega.asignarConocimiento(4, 2, emanager);
				ServiciosPrimeraEntrega.asignarConocimiento(5, 2, emanager);
				ServiciosPrimeraEntrega.asignarConocimiento(6, 2, emanager);
			    ServiciosPrimeraEntrega.asignarTemaAPaper(1, 5, emanager);
			    ServiciosPrimeraEntrega.asignarTemaAPaper(2, 5, emanager);
			    ServiciosPrimeraEntrega.asignarTemaAPaper(3, 5, emanager);
			    //asigno papers a usuarios autores
				ServiciosPrimeraEntrega.asignarAutorAPaper(1, 1, emanager);		
				ServiciosPrimeraEntrega.asignarAutorAPaper(2, 2, emanager);
				//asigno papers a usuarios revisores
				ServiciosPrimeraEntrega.asignarPaperARevisor(ListaPapers.get(0).getId(), ListaUsuarios.get(0).getId(), emanager);		
				System.err.println("***********************ACA***********************");
				System.out.println("--------------------1----------------");
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
				ServiciosPrimeraEntrega.getDatosUsuario(1);
				System.out.println("--------------------2----------------");
				//busco posibles evaluadores por paper
				ServiciosPrimeraEntrega.verificarPosiblesEvaluadores(1, emanager);
				System.out.println("--------------------3----------------");
				//busco posibles papers por evaluador
				ServiciosPrimeraEntrega.verificarPosiblesPapers(1, emanager);
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