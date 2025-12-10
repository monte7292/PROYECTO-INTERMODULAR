package com.gogaming.montero_daniel.controller;

import com.gogaming.montero_daniel.entities.Producto;
import com.gogaming.montero_daniel.entities.Raton;
import com.gogaming.montero_daniel.repositories.ProductoRepository;
import com.gogaming.montero_daniel.repositories.RatonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/ratones")
@CrossOrigin(origins = "*")
public class RatonController {

    @Autowired
    private RatonRepository ratonRepository;

    @Autowired
    private ProductoRepository productoRepository;

    @GetMapping
    public List<Raton> list() {
        return ratonRepository.findAll();
    }

    @GetMapping("/{idProducto}")
    public ResponseEntity<Raton> get(@PathVariable Integer idProducto) {
        return ratonRepository.findById(idProducto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Raton> create(@RequestBody Raton raton) {
        Integer prodId = raton.getProducto() != null ? raton.getProducto().getId() : null;
        if (prodId != null) {
            productoRepository.findById(prodId).ifPresent(raton::setProducto);
        }
        Raton saved = ratonRepository.save(raton);
        return ResponseEntity.created(URI.create("/api/ratones/" + saved.getIdProducto())).body(saved);
    }

    @PutMapping("/{idProducto}")
    public ResponseEntity<Raton> update(@PathVariable Integer idProducto, @RequestBody Raton raton) {
        return ratonRepository.findById(idProducto)
                .map(existing -> {
                    raton.setIdProducto(existing.getIdProducto());
                    Integer prodId = raton.getProducto() != null ? raton.getProducto().getId() : null;
                    if (prodId != null) {
                        productoRepository.findById(prodId).ifPresent(raton::setProducto);
                    }
                    return ResponseEntity.ok(ratonRepository.save(raton));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{idProducto}")
    public ResponseEntity<Void> delete(@PathVariable Integer idProducto) {
        if (!ratonRepository.existsById(idProducto)) {
            return ResponseEntity.notFound().build();
        }
        ratonRepository.deleteById(idProducto);
        return ResponseEntity.noContent().build();
    }
}

