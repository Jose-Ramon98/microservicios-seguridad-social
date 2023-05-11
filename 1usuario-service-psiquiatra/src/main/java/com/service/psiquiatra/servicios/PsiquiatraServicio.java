package com.service.psiquiatra.servicios;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.service.psiquiatra.entidades.Psiquiatra;
import com.service.psiquiatra.repositorio.PsiquiatraRepository;



@Service
public class PsiquiatraServicio {
	
	
	@Autowired
	private PsiquiatraRepository psiquiatraRepository;
	
	public List<Psiquiatra> getAll(){
		
		return psiquiatraRepository.findAll();
	}
	
	public Psiquiatra getPsiquiatraById(int id) {
		return psiquiatraRepository.findById(id).orElseThrow(null);
	}
	
	public Psiquiatra save(Psiquiatra psiquiatra) {
		Psiquiatra nuevoPsiquiatra = psiquiatraRepository.save(psiquiatra);
		return nuevoPsiquiatra;
	}
	
	public List<Psiquiatra> byUsuarioId(int usuarioId){
		return psiquiatraRepository.findByUsuarioId(usuarioId);
	}
	
	

}
