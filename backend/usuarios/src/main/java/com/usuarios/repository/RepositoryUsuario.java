package com.usuarios.repository;

import com.usuarios.model.Usuario;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

@Repository
public interface RepositoryUsuario extends JpaRepository<Usuario, String> {

    Optional<Usuario> findByRut(String rut);

}
