package com.gogaming.montero_daniel.controller;

import com.gogaming.montero_daniel.entities.LineaTicket;
import com.gogaming.montero_daniel.entities.Ticket;
import com.gogaming.montero_daniel.repositories.LineaTicketRepository;
import com.gogaming.montero_daniel.repositories.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/tickets")
@CrossOrigin(origins = "*")
public class TicketsController {

    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private LineaTicketRepository lineaTicketRepository;

    @GetMapping
    public List<Ticket> list(@RequestParam(value = "usuarioId", required = false) Integer usuarioId) {
        if (usuarioId != null) {
            return ticketRepository.findByUsuarioId(usuarioId);
        }
        return ticketRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Ticket> get(@PathVariable Integer id) {
        return ticketRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/{id}/lineas")
    public List<LineaTicket> lineas(@PathVariable Integer id) {
        return lineaTicketRepository.findByTicketId(id);
    }

    @PostMapping
    public ResponseEntity<Ticket> create(@RequestBody Ticket ticket) {
        Ticket saved = ticketRepository.save(ticket);
        return ResponseEntity.created(URI.create("/api/tickets/" + saved.getId())).body(saved);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Ticket> update(@PathVariable Integer id, @RequestBody Ticket ticket) {
        return ticketRepository.findById(id)
                .map(existing -> {
                    ticket.setId(existing.getId());
                    return ResponseEntity.ok(ticketRepository.save(ticket));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        if (!ticketRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        ticketRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}

