package com.gogaming.montero_daniel.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "lineas_ticket")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LineaTicket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idlinea")
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idticket")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @JsonIgnore
    private Ticket ticket;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idproducto")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @JsonIgnore
    private Producto producto;

    @Column(name = "cantidad")
    private Integer cantidad;

    @Column(name = "precio_unitario")
    private Double precioUnitario;
}
