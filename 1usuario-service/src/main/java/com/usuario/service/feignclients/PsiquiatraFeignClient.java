package com.usuario.service.feignclients;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.usuario.service.modelos.Psiquiatra;


@FeignClient(name = "psiquiatra-service", url="http://localhost:8003")
@RequestMapping("/psiquiatra")
public interface PsiquiatraFeignClient {

	@PostMapping()
	public Psiquiatra save(@RequestBody Psiquiatra psiquiatra);
	
@GetMapping("/usuario/{usuarioId}")
	
	public List<Psiquiatra> getPsiquiatras(@PathVariable("usuarioId")int usuarioId);
}
