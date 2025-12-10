package com.gogaming.montero_daniel.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "auriculares")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Auriculares {
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

    @Column(name = "tipo_auricular")
    private String tipoAuricular;

    @Column(name = "image", length = 255)
    private String image;
}
