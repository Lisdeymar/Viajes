package com.codingdojo.lisdey.repositorios;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.codingdojo.lisdey.modelos.Gasto;

//REPOSITORIO INTERFACE: RepositorioViajes, la tabla es viajes, las class es Gasto
@Repository //1.
public interface RepositorioViajes extends CrudRepository<Gasto, Long> { //extends CrudRepository<Gasto: el repositorio que estoy realizando es de Gasto, luego el identificador principal es de tipo Long
	//2. Hago funciones para encontrar los registros de gastos
	//REPOSITORIO DE QUERIES
	//2.1 Funcion: findAll para encontrar todos los registros de gastos
	List<Gasto> findAll(); //Query de modelos BD: SELECT* FROM viajes, select a toda la tabla viajes
	//2.2 Funcion: findAll para encontrar un gasto en base al Id identificador
	List<Gasto> findById(long id); //SELECT* FROM viajes WHERE id = '<ID>'
	
	//3. Haré una Funcion: save que guardará en automático cuando añado o actualizo algo
	Gasto save(Gasto gasto); //QUERY: INSERT into viajes(expense_name, vendor, amount, description) VALUES (Datos de objeto de Viajes)

	
	//4. Funcion: deleteById para eliminar un gasto en base al id 
	void deleteById(Long id);
	
}
