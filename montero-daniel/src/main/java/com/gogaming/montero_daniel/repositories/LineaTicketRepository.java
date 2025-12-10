package com.gogaming.montero_daniel.repositories;

import com.gogaming.montero_daniel.entities.LineaTicket;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LineaTicketRepository extends JpaRepository<LineaTicket, Integer> {
    List<LineaTicket> findByTicketId(Integer ticketId);
}

