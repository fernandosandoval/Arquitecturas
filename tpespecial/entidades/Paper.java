package com.tudai.tpespecial.entidades;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="TABLA_PAPERS")
public class Paper implements Serializable {
	
	private static final long serialVersionUID = 6055541870591892776L;
	   @Id
	   @GeneratedValue
	   @Column
	   private int idPaper;
	   @Column(nullable = false)
	   private String categoría;
       @ManyToMany(mappedBy="papers")	 
	   private List<Tema> temasTratados = new ArrayList<>();
	   @ManyToMany
	   private List<Usuario> usuarios = new ArrayList<>();
	   @OneToMany(mappedBy="paper" , targetEntity = Revision.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
       private List<Revision> revisiones = new ArrayList<>();
	   

       public Paper() {
             
       }

	public int getId() {
		return idPaper;
	}

	public void setId(int idPaper) {
		this.idPaper = idPaper;
	}

	public String getCategoría() {
		return categoría;
	}

	public void setCategoría(String categoría) {
		this.categoría = categoría;
	}

	public List<Revision> getRevisiones() {
		return revisiones;
	}

	public void setRevisiones(List<Revision> revisiones) {
		this.revisiones = revisiones;
	}
    
	public List<Tema> getTemasTratados() {
		return temasTratados;
	}

	public void setTemasTratados(List<Tema> temasTratados) {
		this.temasTratados = temasTratados;
	}
	
	public void addTemaTratado(Tema tema) {
		if (!contieneTema(tema)){
		    this.temasTratados.add(tema);
		    System.out.println("El paper trata sobre el tema "+tema.getTexto());
		}
		else
			System.out.println("El paper ya trataba sobre el tema "+tema.getTexto());
			
	}
	
	public boolean contieneTema(Tema tema) {
		for (Iterator<Tema> iterator = temasTratados.iterator(); iterator.hasNext();) {
			Tema t = (Tema) iterator.next();
			if (t.getTexto().equals(tema.getTexto())) {
				return true;
			}
		}
		return false;
	}


	public void addUsuario (Usuario u) {
		this.usuarios.add(u);
	}
	
	
}
