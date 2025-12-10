package com.gogaming.montero_daniel.controller;

import com.gogaming.montero_daniel.entities.Compra;
import com.gogaming.montero_daniel.entities.Producto;
import com.gogaming.montero_daniel.entities.Usuario;
import com.gogaming.montero_daniel.repositories.CompraRepository;
import com.gogaming.montero_daniel.repositories.ProductoRepository;
import com.gogaming.montero_daniel.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/compras")
@CrossOrigin(origins = "*")
public class ComprasController {

    @Autowired
    private CompraRepository compraRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private ProductoRepository productoRepository;

    @GetMapping
    public List<Compra> list(@RequestParam(value = "usuarioId", required = false) Integer usuarioId,
                             @RequestParam(value = "productoId", required = false) Integer productoId) {
        if (usuarioId != null) {
            return compraRepository.findByUsuarioId(usuarioId);
        }
        if (productoId != null) {
            return compraRepository.findByProductoId(productoId);
        }
        return compraRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Compra> get(@PathVariable Integer id) {
        return compraRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Compra> create(@RequestBody Compra compra) {
        Integer usuarioId = compra.getUsuario() != null ? compra.getUsuario().getId() : null;
        Integer productoId = compra.getProducto() != null ? compra.getProducto().getId() : null;
        if (usuarioId != null) {
            usuarioRepository.findById(usuarioId).ifPresent(compra::setUsuario);
        }
        if (productoId != null) {
            productoRepository.findById(productoId).ifPresent(compra::setProducto);
        }
        Compra saved = compraRepository.save(compra);
        return ResponseEntity.created(URI.create("/api/compras/" + saved.getId())).body(saved);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Compra> update(@PathVariable Integer id, @RequestBody Compra compra) {
        return compraRepository.findById(id)
                .map(existing -> {
                    compra.setId(existing.getId());
                    Integer usuarioId = compra.getUsuario() != null ? compra.getUsuario().getId() : null;
                    Integer productoId = compra.getProducto() != null ? compra.getProducto().getId() : null;
                    if (usuarioId != null) {
                        usuarioRepository.findById(usuarioId).ifPresent(compra::setUsuario);
                    }
                    if (productoId != null) {
                        productoRepository.findById(productoId).ifPresent(compra::setProducto);
                    }
                    return ResponseEntity.ok(compraRepository.save(compra));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        if (!compraRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        compraRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}

