package com.gogaming.montero_daniel.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.*;

@Entity
@Table(name = "raton")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Raton {
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

    @Column(name = "tipo_agarre")
    private String tipoAgarre;

    @Size(max = 50)
    @Column(name = "medidas", length = 50)
    private String medidas;

    @Size(max = 50)
    @Column(name = "sensor", length = 50)
    private String sensor;

    @Column(name = "peso")
    private Double peso;

    @Size(max = 50)
    @Column(name = "switches", length = 50)
    private String switches;

    @Size(max = 255)
    @Column(name = "image", length = 255)
    private String image;
}
