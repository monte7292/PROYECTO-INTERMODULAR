package com.gogaming.montero_daniel.controller;

import com.gogaming.montero_daniel.entities.Valoracion;
import com.gogaming.montero_daniel.repositories.ValoracionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/valoraciones")
@CrossOrigin(origins = "*")
public class ValoracionesController {

    @Autowired
    private ValoracionRepository valoracionRepository;

    @GetMapping
    public List<Valoracion> list(@RequestParam(value = "usuarioId", required = false) Integer usuarioId,
                                 @RequestParam(value = "productoId", required = false) Integer productoId) {
        if (usuarioId != null) {
            return valoracionRepository.findByUsuarioId(usuarioId);
        }
        if (productoId != null) {
            return valoracionRepository.findByProductoId(productoId);
        }
        return valoracionRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Valoracion> get(@PathVariable Integer id) {
        return valoracionRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Valoracion> create(@RequestBody Valoracion valoracion) {
        Valoracion saved = valoracionRepository.save(valoracion);
        return ResponseEntity.created(URI.create("/api/valoraciones/" + saved.getId())).body(saved);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Valoracion> update(@PathVariable Integer id, @RequestBody Valoracion valoracion) {
        return valoracionRepository.findById(id)
                .map(existing -> {
                    valoracion.setId(existing.getId());
                    return ResponseEntity.ok(valoracionRepository.save(valoracion));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        if (!valoracionRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        valoracionRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}

