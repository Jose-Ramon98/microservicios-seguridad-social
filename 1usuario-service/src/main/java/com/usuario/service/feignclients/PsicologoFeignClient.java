package com.usuario.service.feignclients;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.usuario.service.modelos.Psicologo;

@FeignClient(name = "psicologo-service", url="http://localhost:8002")
@RequestMapping("/psicologo")
public interface PsicologoFeignClient {

	@PostMapping()
	public Psicologo save(@RequestBody Psicologo psicologo);
	
	@GetMapping("/usuario/{usuarioId}")
	
	public List<Psicologo> getPsicologos(@PathVariable("usuarioId")int usuarioId);
	
	
}
