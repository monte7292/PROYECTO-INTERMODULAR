package com.gogaming.montero_daniel.controller;

import com.gogaming.montero_daniel.entities.Producto;
import com.gogaming.montero_daniel.repositories.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/productos")
@CrossOrigin(origins = "*")
public class ProductosController {

    @Autowired
    private ProductoRepository productoRepository;

    @GetMapping
    public List<Producto> list(@RequestParam(value = "marca", required = false) String marca,
                               @RequestParam(value = "nombre", required = false) String nombre) {
        if (marca != null) {
            return productoRepository.findByMarcaContainingIgnoreCase(marca);
        }
        if (nombre != null) {
            return productoRepository.findByNombreContainingIgnoreCase(nombre);
        }
        return productoRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Producto> get(@PathVariable Integer id) {
        return productoRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/tipo/{tipo}")
    public List<Producto> byTipo(@PathVariable String tipo) {
        return productoRepository.findByTipoProducto(tipo);
    }

    @PostMapping
    public ResponseEntity<Producto> create(@RequestBody Producto producto) {
        Producto saved = productoRepository.save(producto);
        return ResponseEntity.created(URI.create("/api/productos/" + saved.getId())).body(saved);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Producto> update(@PathVariable Integer id, @RequestBody Producto producto) {
        return productoRepository.findById(id)
                .map(existing -> {
                    producto.setId(existing.getId());
                    return ResponseEntity.ok(productoRepository.save(producto));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        if (!productoRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        productoRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}

