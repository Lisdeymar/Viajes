package com.codingdojo.lisdey.modelos;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

//MODELO

@Entity //1.2 agrego a entity que va a representar un modelo de entidad //importo con el persistance
@Table(name="gastos") //1.1 le digo que quiero hacer una tabla que se llama gastos
public class Gasto {
	//1. creamos los atributos y los comportamientos de cada atributo, importo con persistance
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull
	@Size(min=5, max=100) //message es la validacion para que los campos estén correctos antes de enviar
	private String expense_name; //la validacion es en cuanto a tamaño de caracteres y tipo de dato también puede ser
	
	@NotNull
	@Size(min=5, max=100)
	private String vendor;
	
	@NotNull
	@Min(1) //######
	private Double amount;
	
	@NotNull
	@Size(min=5, max=255)
	private String description;
	
	@Column(updatable=false) //para que esa columna no se actualice
    @DateTimeFormat(pattern="yyyy-MM-dd") //en formato fecha
	private Date created_at; //importamos java.util
	
	@DateTimeFormat(pattern="yyyy-MM-dd") //como se está actualizando no es necesario poner lo de arriba
	private Date updated_at;

	//2. haré el constructor vacio: anticlick-source- constructor
	public Gasto() {
		
	}
	
	//2.1 constructor con solo lo que se visualiza en el form
	public Gasto(String expense_name,
				 String vendor,
				 Double amount,
				 String description) {
		this.expense_name = expense_name;
		this.vendor = vendor;
		this.amount = amount;
		this.description = description;
	}
	
	
	public Gasto(Long id, String expense_name,
			 String vendor,
			 Double amount,
			 String description) {
		this.id = id;
		this.expense_name = expense_name;
		this.vendor = vendor;
		this.amount = amount;
		this.description = description;
	}

	//3.haremos los getter y setter de todo excepto created y updated: anticlick-source-generate getter y setter
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getExpense_name() {
		return expense_name;
	}

	public void setExpense_name(String expense_name) {
		this.expense_name = expense_name;
	}

	public String getVendor() {
		return vendor;
	}

	public void setVendor(String vendor) {
		this.vendor = vendor;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	
	//3.1 en automatico se actualicen los created y el update
	@PrePersist
    protected void onCreate(){
        this.created_at = new Date();
    }
	
    @PreUpdate
    protected void onUpdate(){
        this.updated_at = new Date();
    }
	
    //4. haremos el repositorio
  	//hasta acá tengo un modelo de gastos, me gustaría implementar ese modelo a mi plantila, asi que no vamos al controlador para dar las indicaciones
}
