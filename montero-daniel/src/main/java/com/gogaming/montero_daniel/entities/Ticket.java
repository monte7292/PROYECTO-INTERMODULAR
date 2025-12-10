package com.gogaming.montero_daniel.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tickets")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idticket")
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idusuario")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Usuario usuario;

    @Size(max = 20)
    @Column(name = "numero_ticket", length = 20, unique = true)
    private String numeroTicket;

    @Column(name = "fecha")
    private LocalDateTime fecha;

    @Column(name = "total")
    private Double total;

    @Column(name = "impuestos")
    private Double impuestos;

    @Column(name = "metodo_pago")
    private String metodoPago;

    @OneToMany(mappedBy = "ticket", cascade = CascadeType.ALL, orphanRemoval = true)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private List<LineaTicket> lineas = new ArrayList<>();
}
