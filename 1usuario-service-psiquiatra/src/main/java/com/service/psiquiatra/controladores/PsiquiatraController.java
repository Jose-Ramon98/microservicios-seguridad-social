package com.service.psiquiatra.controladores;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.service.psiquiatra.entidades.Psiquiatra;
import com.service.psiquiatra.servicios.PsiquiatraServicio;



@RestController// Cuando queremos hacer una aplicación web que pueda enviar y recibir datos, 
@RequestMapping("/psiquiatra")
public class PsiquiatraController {

	@Autowired
	private PsiquiatraServicio psiquiatraServicio;
	
	@GetMapping
	//public ResponseEntity<List<Usuario>> es una forma especial de decir que esta función enviará una lista de usuarios a 
	//la persona que visita la página.
	public ResponseEntity<List<Psiquiatra>> listarPsiquiatras(){
		List <Psiquiatra> psiquiatras = psiquiatraServicio.getAll();
		if(psiquiatras.isEmpty()) {
			return ResponseEntity.noContent().build();//quiere decir que si la lista esta vicia nos retorno un contenido
		}
		
		return ResponseEntity.ok(psiquiatras);// y si no esta vacia que nos retorne nuestra lista de usuarios
	}
	
	@GetMapping("/{id}")	
	public ResponseEntity<Psiquiatra> obtenerPsiquiatra(@PathVariable("id")int id){//se usa para obtener un usario
		Psiquiatra psiquiatra = psiquiatraServicio.getPsiquiatraById(id);
		if(psiquiatra == null) {
			return ResponseEntity.notFound().build();// aqui es not found pq no lo encuentra
		}
		return ResponseEntity.ok(psiquiatra);
	}
	
	@PostMapping
	public ResponseEntity<Psiquiatra> guardarPsiquiatra(@RequestBody Psiquiatra psiquiatra){//se usa para guardar un usario
		Psiquiatra nuevoPsiquiatra = psiquiatraServicio.save(psiquiatra);
		return ResponseEntity.ok(nuevoPsiquiatra);
	}
	
	@GetMapping("/usuario/{usuarioId}")
	public ResponseEntity<List<Psiquiatra>> listarPsiquiatraPorUsuarioId(@PathVariable("usuarioId")int id){
	List<Psiquiatra> psiquiatras = psiquiatraServicio.byUsuarioId(id);
		if(psiquiatras.isEmpty()) {
			return ResponseEntity.noContent().build();//quiere decir que si la lista esta vacia nos retorno un contenido
		}
		return ResponseEntity.ok(psiquiatras);
	}
	
	
	
	
	
	
	
}
