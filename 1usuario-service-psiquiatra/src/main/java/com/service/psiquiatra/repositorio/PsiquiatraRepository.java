package com.service.psiquiatra.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.service.psiquiatra.entidades.Psiquiatra;

@Repository
public interface PsiquiatraRepository extends JpaRepository<Psiquiatra, Integer>{

	List<Psiquiatra> findByUsuarioId(int usuarioId);
	
	
	
	
	
	
	
	
	
	
	
}
