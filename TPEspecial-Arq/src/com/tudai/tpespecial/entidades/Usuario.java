package com.tudai.tpespecial.entidades;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="TABLA_USUARIOS")
public class Usuario implements Serializable {
	   
	private static final long serialVersionUID = -7400600344621735783L;
	   @Id
	   @GeneratedValue(strategy = GenerationType.IDENTITY)
//	   @Column(name="idUsuario")
	   private int idUsuario;
	   @Column(nullable = false)
	   private String nombre;
	   @Column(nullable = false)
	   private String lugarTrabajo;
	   @Column(nullable = false)
	   private String titulo;
	   @Column(nullable = false)
	   private int anoEgreso;
	   @ManyToMany(mappedBy="autores")
	   private List<Paper> papers = new ArrayList<>();
	   @Column(nullable = false)
	   private boolean esAutor;
	   @Column(nullable = false)
	   private boolean esEvaluador;
	   @ManyToMany(mappedBy="revisores")
	   private List<Tema> temasConocidos = new ArrayList<>();
	   @Column(nullable = false)
	   private boolean esExperto;
	   @ManyToMany(cascade = { 
			    CascadeType.PERSIST, 
			    CascadeType.MERGE
			})
	   @JoinTable(name = "TABLA_PAPERS_TABLA_USUARIOS",
	              joinColumns = @JoinColumn(name = "idUsuario", referencedColumnName = "idUsuario"),
	              inverseJoinColumns = @JoinColumn(name = "idPaper", referencedColumnName = "idPaper"))
	   @Column
	   @ElementCollection(targetClass=Paper.class)
	   private List<Paper> trabajosAsignados = new ArrayList<>();
	   @OneToMany(mappedBy="usuario", targetEntity = Revision.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
       private List<Revision> revisiones;
	   

       public Usuario(String _nombre, String _lugarTrabajo, String _titulo, int _anoEgreso, Boolean _esEvaluador, Boolean _esAutor, Boolean _esExperto) {
    	   nombre = _nombre;
    	   lugarTrabajo = _lugarTrabajo;
    	   titulo = _titulo;
    	   anoEgreso = _anoEgreso;
    	   esEvaluador = _esEvaluador;
    	   esAutor = _esAutor;
    	   esExperto = _esExperto;
       }

    public Usuario() {
		// TODO Auto-generated constructor stub
	}

	public int getId() {
    	return idUsuario;
    }

	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public String getLugarTrabajo() {
		return lugarTrabajo;
	}


	public void setLugarTrabajo(String lugarTrabajo) {
		this.lugarTrabajo = lugarTrabajo;
	}


	public String getTitulo() {
		return titulo;
	}


	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}


	public int getAnoEgreso() {
		return anoEgreso;
	}


	public void setAnoEgreso(int anoEgreso) {
		this.anoEgreso = anoEgreso;
	}


	public List<Paper> getPapers() {
		return papers;
	}
	
	public List<Paper> getTrabajosAsignados() {
		return trabajosAsignados;
	}
	
	public void addTrabajoAsignado(Paper p) {
		this.trabajosAsignados.add(p);
	}


	public void setPapers(ArrayList<Paper> papers) {
		this.papers = papers;
	}
	
	public void addPaper(Paper p) {
		this.papers.add(p);
	}


	public boolean isEsAutor() {
		return esAutor;
	}


	public void setEsAutor(boolean esAutor) {
		this.esAutor = esAutor;
	}


	public boolean isEsEvaluador() {
		return esEvaluador;
	}


	public void setEsEvaluador(boolean esEvaluador) {
		this.esEvaluador = esEvaluador;
	}


	public List<Tema> getTemasConocidos() {
		return temasConocidos;
	}
	
	public List<Revision> getRevisiones() {
		return revisiones;
	}


	public boolean addTemaConocido(Tema tema) {
		if (!contieneTema(tema)){
		    this.temasConocidos.add(tema);
		    return true;
		}
		else
			return false;
	}
	
	public boolean contieneTema(Tema tema) {
		for (Iterator<Tema> iterator = temasConocidos.iterator(); iterator.hasNext();) {
			Tema t = (Tema) iterator.next();
			if (t.getTexto().equals(tema.getTexto())) {
				return true;
			}
		}
		return false;
	}


	public boolean isEsExperto() {
		return esExperto;
	}


	public void setEsExperto(boolean esExperto) {
		this.esExperto = esExperto;
	}
	
	


	public static long getSerialversionuid() {
		return serialVersionUID;
	}
       
       

}
