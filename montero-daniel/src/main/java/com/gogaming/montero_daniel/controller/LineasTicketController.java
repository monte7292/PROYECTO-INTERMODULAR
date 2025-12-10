package com.gogaming.montero_daniel.controller;

import com.gogaming.montero_daniel.entities.LineaTicket;
import com.gogaming.montero_daniel.repositories.LineaTicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/lineas-ticket")
@CrossOrigin(origins = "*")
public class LineasTicketController {

    @Autowired
    private LineaTicketRepository lineaTicketRepository;

    @GetMapping
    public List<LineaTicket> list(@RequestParam(value = "ticketId", required = false) Integer ticketId) {
        if (ticketId != null) {
            return lineaTicketRepository.findByTicketId(ticketId);
        }
        return lineaTicketRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<LineaTicket> get(@PathVariable Integer id) {
        return lineaTicketRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<LineaTicket> create(@RequestBody LineaTicket linea) {
        LineaTicket saved = lineaTicketRepository.save(linea);
        return ResponseEntity.created(URI.create("/api/lineas-ticket/" + saved.getId())).body(saved);
    }

    @PutMapping("/{id}")
    public ResponseEntity<LineaTicket> update(@PathVariable Integer id, @RequestBody LineaTicket linea) {
        return lineaTicketRepository.findById(id)
                .map(existing -> {
                    linea.setId(existing.getId());
                    return ResponseEntity.ok(lineaTicketRepository.save(linea));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        if (!lineaTicketRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        lineaTicketRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}

