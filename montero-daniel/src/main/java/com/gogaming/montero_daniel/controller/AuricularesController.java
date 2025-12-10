package com.gogaming.montero_daniel.controller;

import com.gogaming.montero_daniel.entities.Auriculares;
import com.gogaming.montero_daniel.entities.Producto;
import com.gogaming.montero_daniel.repositories.AuricularesRepository;
import com.gogaming.montero_daniel.repositories.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/auriculares")
@CrossOrigin(origins = "*")
public class AuricularesController {

    @Autowired
    private AuricularesRepository auricularesRepository;

    @Autowired
    private ProductoRepository productoRepository;

    @GetMapping
    public List<Auriculares> list() {
        return auricularesRepository.findAll();
    }

    @GetMapping("/{idProducto}")
    public ResponseEntity<Auriculares> get(@PathVariable Integer idProducto) {
        return auricularesRepository.findById(idProducto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Auriculares> create(@RequestBody Auriculares auriculares) {
        Integer prodId = auriculares.getProducto() != null ? auriculares.getProducto().getId() : null;
        if (prodId != null) {
            productoRepository.findById(prodId).ifPresent(auriculares::setProducto);
        }
        Auriculares saved = auricularesRepository.save(auriculares);
        return ResponseEntity.created(URI.create("/api/auriculares/" + saved.getIdProducto())).body(saved);
    }

    @PutMapping("/{idProducto}")
    public ResponseEntity<Auriculares> update(@PathVariable Integer idProducto, @RequestBody Auriculares auriculares) {
        return auricularesRepository.findById(idProducto)
                .map(existing -> {
                    auriculares.setIdProducto(existing.getIdProducto());
                    Integer prodId = auriculares.getProducto() != null ? auriculares.getProducto().getId() : null;
                    if (prodId != null) {
                        productoRepository.findById(prodId).ifPresent(auriculares::setProducto);
                    }
                    return ResponseEntity.ok(auricularesRepository.save(auriculares));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{idProducto}")
    public ResponseEntity<Void> delete(@PathVariable Integer idProducto) {
        if (!auricularesRepository.existsById(idProducto)) {
            return ResponseEntity.notFound().build();
        }
        auricularesRepository.deleteById(idProducto);
        return ResponseEntity.noContent().build();
    }
}

