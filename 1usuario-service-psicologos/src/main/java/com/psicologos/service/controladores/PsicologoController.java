package com.psicologos.service.controladores;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.psicologos.service.entidades.Psicologo;
import com.psicologos.service.servicios.PsicologoService;

@RestController// Cuando queremos hacer una aplicación web que pueda enviar y recibir datos, 
@RequestMapping("/psicologo")
public class PsicologoController {
	
	
	
	@Autowired
	private PsicologoService psicologoService;
	
	@GetMapping
	//public ResponseEntity<List<Usuario>> es una forma especial de decir que esta función enviará una lista de usuarios a 
	//la persona que visita la página.
	public ResponseEntity<List<Psicologo>> listarPsicologos(){
		List <Psicologo> psicologos = psicologoService.getAll();
		if(psicologos.isEmpty()) {
			return ResponseEntity.noContent().build();//quiere decir que si la lista esta vicia nos retorno un contenido
		}
		
		return ResponseEntity.ok(psicologos);// y si no esta vacia que nos retorne nuestra lista de usuarios
	}
	
	@GetMapping("/{id}")	
	public ResponseEntity<Psicologo> obtenerPsicologo(@PathVariable("id")int id){//se usa para obtener un usario
		Psicologo psicologo = psicologoService.getPsicologoById(id);
		if(psicologo == null) {
			return ResponseEntity.notFound().build();// aqui es not found pq no lo encuentra
		}
		return ResponseEntity.ok(psicologo);
	}
	
	@PostMapping
	public ResponseEntity<Psicologo> guardarPsiquiatra(@RequestBody Psicologo psicologo){//se usa para guardar un usario
		Psicologo nuevoPsicologo = psicologoService.save(psicologo);
		return ResponseEntity.ok(nuevoPsicologo);
	}
	
	@GetMapping("/usuario/{usuarioId}")
	public ResponseEntity<List<Psicologo>> listarPsiquiatraPorUsuarioId(@PathVariable("usuarioId")int id){
	List<Psicologo> psicologos = psicologoService.byUsuarioId(id);
		if(psicologos.isEmpty()) {
			return ResponseEntity.noContent().build();//quiere decir que si la lista esta vacia nos retorno un contenido
		}
		return ResponseEntity.ok(psicologos);
	}

}
