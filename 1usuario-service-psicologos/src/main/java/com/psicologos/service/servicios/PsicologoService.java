package com.psicologos.service.servicios;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.psicologos.service.entidades.Psicologo;
import com.psicologos.service.repositorio.PsicologoRepository;

@Service
public class PsicologoService {
	
	@Autowired
	private PsicologoRepository psicologoRepository;
	
	public List<Psicologo> getAll(){
		
		return psicologoRepository.findAll();
	}
	
	public Psicologo getPsicologoById(int id) {
		return psicologoRepository.findById(id).orElseThrow(null);
	}
	
	public Psicologo save(Psicologo psicologo) {
		Psicologo nuevoPsicologo = psicologoRepository.save(psicologo);
		return nuevoPsicologo;
	}
	
	public List<Psicologo> byUsuarioId(int usuarioId){
		return psicologoRepository.findByUsuarioId(usuarioId);
	}
	
	
}
