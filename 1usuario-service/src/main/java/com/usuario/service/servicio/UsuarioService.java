package com.usuario.service.servicio;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.usuario.service.entidades.Usuario;
import com.usuario.service.feignclients.PsicologoFeignClient;
import com.usuario.service.feignclients.PsiquiatraFeignClient;
import com.usuario.service.modelos.Psicologo;
import com.usuario.service.modelos.Psiquiatra;
import com.usuario.service.repositorio.UsuarioRepository;

@Service // @Service se utiliza para marcar una clase como un componente de servicio que
			// proporciona la lógica de negocio de la aplicación
public class UsuarioService {

	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private UsuarioRepository usuarioRepository;

	public List<Usuario> getAll() {
		return usuarioRepository.findAll();
	}

	public Usuario getUsuarioById(int id) {
		return usuarioRepository.findById(id).orElse(null);

	}

	public Usuario save(Usuario usuario) {
		Usuario nuevoUsuario = usuarioRepository.save(usuario);
		return nuevoUsuario;

	}

	@Autowired
	private PsicologoFeignClient psicologoFeignClient;

	@Autowired
	private PsiquiatraFeignClient psiquiatraFeignClient;

	public List<Psicologo> getPsicologo(int usuarioId) {

		List<Psicologo> psicologos = restTemplate.getForObject("http://localhost:8002/psicologo/usuario/" + usuarioId,
				List.class);
		return psicologos;
	}

	public List<Psiquiatra> getPsiquiatra(int usuarioId) {

		List<Psiquiatra> psiquiatras = restTemplate
				.getForObject("http://localhost:8003/psiquiatra/usuario/" + usuarioId, List.class);
		return psiquiatras;
	}

	public Psicologo savePsicologo(int usuarioId, Psicologo psicologo) {
		psicologo.setUsuarioId(usuarioId);
		Psicologo nuevoPsicologo = psicologoFeignClient.save(psicologo);
		return nuevoPsicologo;
	}

	public Psiquiatra savePsiquiatra(int usuarioId, Psiquiatra psiquiatra) {
		psiquiatra.setUsuarioId(usuarioId);
		Psiquiatra nuevoPsiquiatra = psiquiatraFeignClient.save(psiquiatra);
		return nuevoPsiquiatra;
	}
	
	public Map<String, Object> getUsuariosAndProfesionales(int usuarioId){
		Map<String,Object> resultado = new HashMap<>();
		Usuario usuario = usuarioRepository.findById(usuarioId).orElse(null);
		
		if(usuario == null) {
			resultado.put("Mensaje","El centro de salud no existe");
			return resultado;
		}
		resultado.put("Usuario", usuario);
		List<Psicologo> psicologos = psicologoFeignClient.getPsicologos(usuarioId);
		if(psicologos.isEmpty()) {
			resultado.put("Psicologos","El centro de salud no tiene psicologos");
		}
		else {
			resultado.put("Psicologos", psicologos);
		}
		
		List<Psiquiatra> psiquiatras = psiquiatraFeignClient.getPsiquiatras(usuarioId);
		
	if(psiquiatras.isEmpty()) {
		resultado.put("Psiquiatras","El centro de salud no tiene psiquiatras");
	}
	else {
		resultado.put("Psiquiatras", psiquiatras);

		
	}
	return resultado;
	}
	
}

