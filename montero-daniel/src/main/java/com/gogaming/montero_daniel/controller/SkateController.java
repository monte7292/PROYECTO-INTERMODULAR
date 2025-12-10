package com.gogaming.montero_daniel.controller;

import com.gogaming.montero_daniel.entities.Producto;
import com.gogaming.montero_daniel.entities.Skate;
import com.gogaming.montero_daniel.repositories.ProductoRepository;
import com.gogaming.montero_daniel.repositories.SkateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/skates")
@CrossOrigin(origins = "*")
public class SkateController {

    @Autowired
    private SkateRepository skateRepository;

    @Autowired
    private ProductoRepository productoRepository;

    @GetMapping
    public List<Skate> list() {
        return skateRepository.findAll();
    }

    @GetMapping("/{idProducto}")
    public ResponseEntity<Skate> get(@PathVariable Integer idProducto) {
        return skateRepository.findById(idProducto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Skate> create(@RequestBody Skate skate) {
        Integer prodId = skate.getProducto() != null ? skate.getProducto().getId() : null;
        if (prodId != null) {
            productoRepository.findById(prodId).ifPresent(skate::setProducto);
        }
        Skate saved = skateRepository.save(skate);
        return ResponseEntity.created(URI.create("/api/skates/" + saved.getIdProducto())).body(saved);
    }

    @PutMapping("/{idProducto}")
    public ResponseEntity<Skate> update(@PathVariable Integer idProducto, @RequestBody Skate skate) {
        return skateRepository.findById(idProducto)
                .map(existing -> {
                    skate.setIdProducto(existing.getIdProducto());
                    Integer prodId = skate.getProducto() != null ? skate.getProducto().getId() : null;
                    if (prodId != null) {
                        productoRepository.findById(prodId).ifPresent(skate::setProducto);
                    }
                    return ResponseEntity.ok(skateRepository.save(skate));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{idProducto}")
    public ResponseEntity<Void> delete(@PathVariable Integer idProducto) {
        if (!skateRepository.existsById(idProducto)) {
            return ResponseEntity.notFound().build();
        }
        skateRepository.deleteById(idProducto);
        return ResponseEntity.noContent().build();
    }
}

