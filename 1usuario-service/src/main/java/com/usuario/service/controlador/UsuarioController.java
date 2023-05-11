package com.usuario.service.controlador;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.usuario.service.entidades.Usuario;
import com.usuario.service.modelos.Psicologo;
import com.usuario.service.modelos.Psiquiatra;
import com.usuario.service.servicio.UsuarioService;

@RestController// Cuando queremos hacer una aplicación web que pueda enviar y recibir datos, 
@RequestMapping("/usuario")
public class UsuarioController {
	
	@Autowired
	private UsuarioService usuarioService;
	
	@GetMapping
	//public ResponseEntity<List<Usuario>> es una forma especial de decir que esta función enviará una lista de usuarios a 
	//la persona que visita la página.
	public ResponseEntity<List<Usuario>> listarUsuario(){
		List <Usuario> usuarios = usuarioService.getAll();
		if(usuarios.isEmpty()) {
			return ResponseEntity.noContent().build();//quiere decir que si la lista esta vicia nos retorno un contenido
		}
		
		return ResponseEntity.ok(usuarios);// y si no esta vacia que nos retorne nuestra lista de usuarios
	}
	
	@GetMapping("/{id}")	
	public ResponseEntity<Usuario> obtenerUsuario(@PathVariable("id")int id){//se usa para obtener un usario
		Usuario usuario = usuarioService.getUsuarioById(id);
		if(usuario == null) {
			return ResponseEntity.notFound().build();// aqui es not found pq no lo encuentra
		}
		return ResponseEntity.ok(usuario);
	}
	
	@PostMapping
	public ResponseEntity<Usuario> guardar(@RequestBody Usuario usuario){//se usa para guardar un usario
		Usuario nuevoUsuario = usuarioService.save(usuario);
		return ResponseEntity.ok(nuevoUsuario);
	}
	
	@GetMapping("/psicologo/{usuarioId}")
	public ResponseEntity<List<Psicologo>> getPsicologos(@PathVariable("usuarioId")int id){
		Usuario usuario = usuarioService.getUsuarioById(id);
		if(usuario == null) {
			return ResponseEntity.notFound().build();
		}
		List<Psicologo> psicologos = usuarioService.getPsicologo(id);
		return ResponseEntity.ok(psicologos);
	}
	@GetMapping("/psiquiatra/{usuarioId}")
	public ResponseEntity<List<Psiquiatra>> getPsiquiatras(@PathVariable("usuarioId")int id){
		Usuario usuario = usuarioService.getUsuarioById(id);
		if(usuario == null) {
			return ResponseEntity.notFound().build();
		}
		List<Psiquiatra> psiquiatras = usuarioService.getPsiquiatra(id);
		return ResponseEntity.ok(psiquiatras);
	}
	
	@PostMapping("/psicologo/{usuarioId}")
	
	public ResponseEntity<Psicologo> guardarPsicologo(@PathVariable("usuarioId")int usuarioId, @RequestBody Psicologo psicologo){
		Psicologo nuevoPsicologo = usuarioService.savePsicologo(usuarioId, psicologo);
		return ResponseEntity.ok(nuevoPsicologo);
	}
	
	
	@PostMapping("/psiquiatra/{usuarioId}")
	
	public ResponseEntity<Psiquiatra> guardarPsiquiatra(@PathVariable("usuarioId")int usuarioId, @RequestBody Psiquiatra psiquiatra){
		Psiquiatra nuevoPsiquiatra = usuarioService.savePsiquiatra(usuarioId, psiquiatra);
		return ResponseEntity.ok(nuevoPsiquiatra);
	
	
	
}
	
	
	@GetMapping("/todos/{usuarioId}")
	public ResponseEntity<Map<String, Object>> listarTodosLosProfesionales(@PathVariable("usuarioId")int usuarioId){
	
	Map<String,Object> resultado = usuarioService.getUsuariosAndProfesionales(usuarioId);
	return ResponseEntity.ok(resultado);
		
		
		
		
	}
	
	
		
}
	
	
	


