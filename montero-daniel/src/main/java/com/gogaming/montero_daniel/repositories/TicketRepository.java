package com.gogaming.montero_daniel.repositories;

import com.gogaming.montero_daniel.entities.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TicketRepository extends JpaRepository<Ticket, Integer> {
    List<Ticket> findByUsuarioId(Integer usuarioId);
    Optional<Ticket> findByNumeroTicket(String numeroTicket);
}

