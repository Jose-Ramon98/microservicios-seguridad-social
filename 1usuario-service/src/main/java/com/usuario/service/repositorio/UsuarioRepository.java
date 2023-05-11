package com.usuario.service.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.usuario.service.entidades.Usuario;

@Repository//@Repository se utiliza para indicar que una clase es un componente de acceso a datos.se ocupa de la lógica de acceso a datos
public interface UsuarioRepository extends JpaRepository<Usuario, Integer>{

	
}
