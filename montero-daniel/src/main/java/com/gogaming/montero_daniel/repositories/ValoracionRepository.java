package com.gogaming.montero_daniel.repositories;

import com.gogaming.montero_daniel.entities.Valoracion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ValoracionRepository extends JpaRepository<Valoracion, Integer> {
    List<Valoracion> findByUsuarioId(Integer usuarioId);
    List<Valoracion> findByProductoId(Integer productoId);
}

