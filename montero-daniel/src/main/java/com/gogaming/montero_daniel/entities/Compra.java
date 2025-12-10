package com.gogaming.montero_daniel.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "compras")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Compra {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idcompra")
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idusuario")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @JsonIgnore
    private Usuario usuario;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idproducto")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @JsonIgnore
    private Producto producto;

    @Column(name = "fecha_compra")
    private LocalDate fechaCompra;

    @Column(name = "precio_final")
    private Double precioFinal;

    @Column(name = "estado_compra")
    private String estadoCompra;
}
