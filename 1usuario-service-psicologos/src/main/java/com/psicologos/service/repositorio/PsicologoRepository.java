package com.psicologos.service.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.psicologos.service.entidades.Psicologo;


public interface PsicologoRepository extends JpaRepository<Psicologo, Integer>{
	
	 List<Psicologo> findByUsuarioId(int usuarioId);
	
	

}
