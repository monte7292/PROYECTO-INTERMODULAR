package com.gogaming.montero_daniel.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "skate")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Skate {
    @Id
    @Column(name = "idproducto")
    private Integer idProducto;

    @OneToOne
    @MapsId
    @JoinColumn(name = "idproducto")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @JsonIgnore
    private Producto producto;

    @Column(name = "velocidad")
    private String velocidad;

    @Column(name = "image", length = 255)
    private String image;
}

