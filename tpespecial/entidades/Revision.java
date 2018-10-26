package com.tudai.tpespecial.entidades;

import java.io.Serializable;
import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="TABLA_REVISIONES")


public class Revision implements Serializable {
	
       private static final long serialVersionUID = -7617415053157533023L;
       @Id
       @GeneratedValue(strategy = GenerationType.AUTO)
       @Column
       private int idRevision;
	   @Column(nullable = false)
	   private String texto;
	   @Column(nullable = false)
	   private Calendar fecha;
	   @ManyToOne
	   @JoinColumn(name="ID_US")
	   private Usuario usuario;
	   @ManyToOne
	   @JoinColumn(name = "ID_PAP")
	   private Paper paper;
	   
	   public Revision() {
		   
	   }

       public Revision(String texto, Calendar fecha) {
		   this.texto = texto;
		   this.fecha = fecha;
       }

    public int getId() {
   		return idRevision;
   	}
       
    public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}

	public Calendar getFecha() {
		return fecha;
	}

	public void setFecha(Calendar fecha) {
		this.fecha = fecha;
	}
	
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	}
