package com.gogaming.montero_daniel.controller;

import com.gogaming.montero_daniel.entities.Producto;
import com.gogaming.montero_daniel.entities.Teclado;
import com.gogaming.montero_daniel.repositories.ProductoRepository;
import com.gogaming.montero_daniel.repositories.TecladoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/teclados")
@CrossOrigin(origins = "*")
public class TecladoController {

    @Autowired
    private TecladoRepository tecladoRepository;

    @Autowired
    private ProductoRepository productoRepository;

    @GetMapping
    public List<Teclado> list() {
        return tecladoRepository.findAll();
    }

    @GetMapping("/{idProducto}")
    public ResponseEntity<Teclado> get(@PathVariable Integer idProducto) {
        return tecladoRepository.findById(idProducto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Teclado> create(@RequestBody Teclado teclado) {
        Integer prodId = teclado.getProducto() != null ? teclado.getProducto().getId() : null;
        if (prodId != null) {
            productoRepository.findById(prodId).ifPresent(teclado::setProducto);
        }
        Teclado saved = tecladoRepository.save(teclado);
        return ResponseEntity.created(URI.create("/api/teclados/" + saved.getIdProducto())).body(saved);
    }

    @PutMapping("/{idProducto}")
    public ResponseEntity<Teclado> update(@PathVariable Integer idProducto, @RequestBody Teclado teclado) {
        return tecladoRepository.findById(idProducto)
                .map(existing -> {
                    teclado.setIdProducto(existing.getIdProducto());
                    Integer prodId = teclado.getProducto() != null ? teclado.getProducto().getId() : null;
                    if (prodId != null) {
                        productoRepository.findById(prodId).ifPresent(teclado::setProducto);
                    }
                    return ResponseEntity.ok(tecladoRepository.save(teclado));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{idProducto}")
    public ResponseEntity<Void> delete(@PathVariable Integer idProducto) {
        if (!tecladoRepository.existsById(idProducto)) {
            return ResponseEntity.notFound().build();
        }
        tecladoRepository.deleteById(idProducto);
        return ResponseEntity.noContent().build();
    }
}

