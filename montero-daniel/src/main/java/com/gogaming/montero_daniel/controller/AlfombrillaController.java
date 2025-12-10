package com.gogaming.montero_daniel.controller;

import com.gogaming.montero_daniel.entities.Alfombrilla;
import com.gogaming.montero_daniel.entities.Producto;
import com.gogaming.montero_daniel.repositories.AlfombrillaRepository;
import com.gogaming.montero_daniel.repositories.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/alfombrillas")
@CrossOrigin(origins = "*")
public class AlfombrillaController {

    @Autowired
    private AlfombrillaRepository alfombrillaRepository;

    @Autowired
    private ProductoRepository productoRepository;

    @GetMapping
    public List<Alfombrilla> list() {
        return alfombrillaRepository.findAll();
    }

    @GetMapping("/{idProducto}")
    public ResponseEntity<Alfombrilla> get(@PathVariable Integer idProducto) {
        return alfombrillaRepository.findById(idProducto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Alfombrilla> create(@RequestBody Alfombrilla alfombrilla) {
        Integer prodId = alfombrilla.getProducto() != null ? alfombrilla.getProducto().getId() : null;
        if (prodId != null) {
            productoRepository.findById(prodId).ifPresent(alfombrilla::setProducto);
        }
        Alfombrilla saved = alfombrillaRepository.save(alfombrilla);
        return ResponseEntity.created(URI.create("/api/alfombrillas/" + saved.getIdProducto())).body(saved);
    }

    @PutMapping("/{idProducto}")
    public ResponseEntity<Alfombrilla> update(@PathVariable Integer idProducto, @RequestBody Alfombrilla alfombrilla) {
        return alfombrillaRepository.findById(idProducto)
                .map(existing -> {
                    alfombrilla.setIdProducto(existing.getIdProducto());
                    Integer prodId = alfombrilla.getProducto() != null ? alfombrilla.getProducto().getId() : null;
                    if (prodId != null) {
                        productoRepository.findById(prodId).ifPresent(alfombrilla::setProducto);
                    }
                    return ResponseEntity.ok(alfombrillaRepository.save(alfombrilla));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{idProducto}")
    public ResponseEntity<Void> delete(@PathVariable Integer idProducto) {
        if (!alfombrillaRepository.existsById(idProducto)) {
            return ResponseEntity.notFound().build();
        }
        alfombrillaRepository.deleteById(idProducto);
        return ResponseEntity.noContent().build();
    }
}

