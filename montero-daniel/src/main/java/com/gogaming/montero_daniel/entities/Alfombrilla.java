package com.gogaming.montero_daniel.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.*;

@Entity
@Table(name = "alfombrilla")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Alfombrilla {
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

    @Size(max = 50)
    @Column(name = "medidas", length = 50)
    private String medidas;

    @Size(max = 255)
    @Column(name = "image", length = 255)
    private String image;
}
