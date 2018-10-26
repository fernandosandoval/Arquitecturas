package com.tudai.tpespecial.entidades;
import java.io.Serializable;
import java.util.Calendar;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="TABLA_TEMAS")


public class Tema implements Serializable {

       	   private static final long serialVersionUID = -5753790977490549983L;
	       @Id
	       @GeneratedValue(strategy = GenerationType.AUTO)
	       @Column
	       private int idTema;
		   @Column(nullable = false)
		   private String texto;
		   @ManyToMany
		   private List<Paper> papers;
		   @ManyToMany
		   private List<Usuario> usuarios;

		   
		   public Tema() {
			   
		   }

	    public Tema(String texto) {
			   this.texto = texto;
	       }

		public int getIdTema() {
			return idTema;
		}

		public void setIdTema(int idTema) {
			this.idTema = idTema;
		}

		public String getTexto() {
			return texto;
		}

		public void setTexto(String texto) {
			this.texto = texto;
		}

        public void addTema(String texto) {
        	
        }
}
