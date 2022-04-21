package com.codingdojo.lisdey.controladores;

import java.util.List;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.codingdojo.lisdey.modelos.Gasto;
import com.codingdojo.lisdey.servicios.ServicioViajes;


@Controller //X. escribo Controller e importo
@RequestMapping("/expenses") //Y. todas las rutas segun el framework comienzan con /expenses, por eso coloco esto
public class ControladorViajes {
	//Z. implementamos el servicio dentro del controlador, acá agregamos el servicio
	private final ServicioViajes servicio; //import: ServicioViajes
	
	//Z.1 Debo inicializar mi servicio, haré un constructor para ControladorViajes para que inicialice el servicio
	public ControladorViajes(ServicioViajes servicio) {
		this.servicio = servicio;
	}
	
	//HAGO TODAS MIS RUTAS
	//1 LA RUTA INICIAL la ruta del index,como en la ruta está la tabla de gastos, aprovecho y mando la var lista_gastos para que el index.jsp lo itere
	@RequestMapping(value="", method=RequestMethod.GET)
	// creo una Funcion 
	public String index(Model model) { //8.1 Quiero mandar al index a todos mis gastos, sabemos que con model model podemos enviar datos a mis plantillas jsp, agrego eso
		
		//8.1 quiero que me regrese los gastos en una lista
		List<Gasto> lista_gastos = servicio.get_gastos();
		//8.1 De la lista Gasto voy a colocar todos los valores en la var: lista_gastos y = mi servicio va a mandar a llamar a la funcion get_gastos para que obtenga todos los datos
		//y me regrese esa lista de datos
		
		//8.2 Quiero que despliegue toda la iformacion en el dashboard
		//estoy mandando a mi lista_gastos azul de los valores que tiene la var lista_gastos marron que arriba he recogido con servicio.
		model.addAttribute("lista_gastos", lista_gastos);
		
		return "index.jsp"; //acá está retornando la plantilla index.jsp html que está en el WEB INF
	}
	
	//2 RUTA DESPLIEGA NEW GET
	@RequestMapping(value="/new", method=RequestMethod.GET)
	//9.1 creo una Funcion: register
	//9.1(@ModelAttribute("gasto") pongo gasto porque debe ser el mismo del ModelAttribute que puse en new.jsp html
	//9.1 Gasto gasto), será de la class Gasto con el nombre gasto
	public String new_expense(@ModelAttribute("gasto") Gasto gasto) {
		return "new.jsp"; //9.1acá está retornando la plantilla new.jsp que es para añadir que está en el WEB INF
	}
	
	//2A RUTA REGISTRAR GASTO POST
	//es el form con la validacion
	//para el form de /create de registro lo recibo a través de POST, para crear nuevo usuario y guardarlo en el dashboad
	@PostMapping("/create") //######## 2A RUTAAAA lo que está dentro del JSP
	//crear funcion para que valide los campos de registro ruta /create
	public String create(@Valid @ModelAttribute("gasto") Gasto gasto, //pongo gasto porque debe ser el mismo del ModelAttribute que puse en registro.jsp html, pongo class Gasto y utilizaré la var usuario
						 BindingResult result) { //BindingResult result que se cumpla lo que puse en class Gasto para llenar los campos
		if(result.hasErrors()) { //10.1 si el resultado tiene errores en el form, 
			return "new.jsp"; //regrésame a la pagn
		} else {
			servicio.save_gasto(gasto); //si no hay errores, guárdamelo
			return "redirect:/expenses"; //ruta del index expenses POST REDIRECT: /
		}
	}
	
	//1B RUTA DELETE POST
	// Para eliminar gasto con el id por eso pongo id
	@RequestMapping(value="/delete/{id_url}", method=RequestMethod.DELETE) //####### 1B RUTAAAA lo que está dentro del JSP 
	public String eliminate(@PathVariable("id_url") Long id) { //hago uso de la funcion de servicio delete
		servicio.delete_gasto(id);
		return "redirect:/expenses"; //POST REDIRECT: /
	}
	
	//EDIT:Html 1. copio lo mismo de añadir gasto new.jsp
	//3 RUTA DESPLIEGA EDIT GET
	@GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, Model model, @ModelAttribute("gasto") Gasto gasto) {
        Gasto gasto_edit = servicio.find_gasto(id);
        System.out.println(gasto.getExpense_name());
        model.addAttribute("gasto", gasto_edit);
        return "edit.jsp";
    }
	
	//3A CAJA EDIT POST
	@PutMapping("/edit/{id}")
	public String update(@PathVariable("id") Long id, @Valid @ModelAttribute("gasto") Gasto gasto, BindingResult result) {
		if(result.hasErrors()) {
			return "edit.jsp";
		}else {
			servicio.save_gasto(gasto);
			return "redirect:/expenses";
		}
	}
	/*
	@RequestMapping(value="/delete/{id_url}", method=RequestMethod.PUT)
	public String update(@PathVariable("id") Long id, @Valid @ModelAttribute("gasto") Gasto gasto, BindingResult result) {
		if(result.hasErrors()) {
			return "edit.jsp";
		} else {
			servicio.save_gasto(gasto);
			return "redirect:/expenses";
		}
	}*/
}

//8.3 HTML INDEX: desplegar una tabla de gastos en dashboard, como es una lista de gastos, debo recorrerlo con for y estoy
//agarrando cada elemento de items="${gastos}" para ponerlo en la var="gasto"
//<c:forEach var="gasto" items="${gastos}"></c:forEach>
//impresion de todas mis filas con la funcion de Modelos: <td><c:out value="${gasto.getExpense_name()}"/></td>
//EL controlador de mi servicio, manda a llamar a las funciones



