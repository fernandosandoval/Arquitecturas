package com.tudai.tpespecial.entidades;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="TABLA_USUARIOS")
public class Usuario implements Serializable {
	   
	private static final long serialVersionUID = -7400600344621735783L;
	   @Id
	   @GeneratedValue
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
	   @ManyToMany(mappedBy="usuarios")
	   private List<Paper> papers = new ArrayList<>();
	   @Column(nullable = false)
	   private boolean esAutor;
	   @Column(nullable = false)
	   private boolean esEvaluador;
	   @ManyToMany(mappedBy="usuarios")
	   private List<Tema> temasConocidos = new ArrayList<>();
	   @Column(nullable = false)
	   private boolean esExperto;
	   @OneToMany(mappedBy="usuario", targetEntity = Paper.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
       private List<Paper> trabajosAsignados;
	   @OneToMany(mappedBy="usuario", targetEntity = Revision.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
       private List<Revision> revisiones;
	   

       public Usuario() {

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
