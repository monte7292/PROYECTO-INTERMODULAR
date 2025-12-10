package com.gogaming.montero_daniel.repositories;

import com.gogaming.montero_daniel.entities.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductoRepository extends JpaRepository<Producto, Integer> {
    List<Producto> findByTipoProducto(String tipoProducto);
    List<Producto> findByMarcaContainingIgnoreCase(String marca);
    List<Producto> findByNombreContainingIgnoreCase(String nombre);
}

