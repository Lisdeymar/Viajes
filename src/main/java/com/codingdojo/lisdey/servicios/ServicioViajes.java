package com.codingdojo.lisdey.servicios;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.codingdojo.lisdey.modelos.Gasto; //modelo class: Gasto
import com.codingdojo.lisdey.repositorios.RepositorioViajes; //RepositorioTablasName

@Service //1. anotacion para declarar que es un servicio
public class ServicioViajes {
	//2. Dependencia del repositorio
	private final RepositorioViajes repositorio; //final significa que nunca va a cambiar
	
	//3. Realizar el constructor con la única variable colocada arrriba
	public ServicioViajes(RepositorioViajes repositorio) {
		this.repositorio = repositorio;
	}
	
	//4. Hacer funciones que van a mandar a llamar a mi funcion del archivo RepositorioViajes
	//4. en la linea public así se llamarán las funciones que utilizaremos en el controlador
	//EJECUCION DE REPOSITORIOS PARA LOS QUERIES
	//4.1 Get_gastos de la lista de Gasto de RepositorioViajes quiero obtener todos los gastos para desplegar la tabla de gastos
	public List<Gasto> get_gastos() {
		return repositorio.findAll(); //acá stoy mandando a llamar a la funcion del archivo RepositorioViajes
	}
	//4.2 Find_gasto lo que quiero recibir es el Id, que lo encuentre o me diga que no existe el id que coloco
	public Gasto find_gasto(Long id) {
		Optional<Gasto> optionalGasto = repositorio.findById(id);
		if(optionalGasto.isPresent()) { //si está presente, sí existe
			return optionalGasto.get(); //si existe el id
		} else {
			return null; //no existe el id
		}
	}
	
	//4.3 Funcion para guardar un nuevo Gasto y para guardar el actualizado
	public Gasto save_gasto(Gasto gasto) {
		return repositorio.save(gasto);
	}
	
	//4.3 Funcion delete_gasto lo usaré dentro de los controladores
	public void delete_gasto(Long id) { 
		repositorio.deleteById(id);//Delete 4.3 mando a llamar a la funcion del repositorio
	}
}
