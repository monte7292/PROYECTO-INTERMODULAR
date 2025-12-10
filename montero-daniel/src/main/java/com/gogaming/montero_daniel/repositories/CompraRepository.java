package com.gogaming.montero_daniel.repositories;

import com.gogaming.montero_daniel.entities.Compra;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CompraRepository extends JpaRepository<Compra, Integer> {
    List<Compra> findByUsuarioId(Integer usuarioId);
    List<Compra> findByProductoId(Integer productoId);
}

