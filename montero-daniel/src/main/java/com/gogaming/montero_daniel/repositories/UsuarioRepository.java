package com.gogaming.montero_daniel.repositories;

import com.gogaming.montero_daniel.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
    Optional<Usuario> findByDni(String dni);
    Optional<Usuario> findByCorreoElectronico(String correoElectronico);
}

