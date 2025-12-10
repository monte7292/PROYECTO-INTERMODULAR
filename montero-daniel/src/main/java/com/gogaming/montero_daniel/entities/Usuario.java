package com.gogaming.montero_daniel.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "usuarios")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idusuario")
    private Integer id;

    @Size(max = 50)
    @Column(name = "nombre", length = 50)
    private String nombre;

    @Size(max = 50)
    @Column(name = "apellidos", length = 50)
    private String apellidos;

    @Column(name = "edad")
    private Integer edad;

    @Size(max = 20)
    @Column(name = "telefono", length = 20)
    private String telefono;

    @Size(max = 15)
    @Column(name = "dni", length = 15, unique = true)
    private String dni;

    @Size(max = 100)
    @Column(name = "correo_electronico", length = 100, unique = true)
    private String correoElectronico;

    @Size(max = 100)
    @Column(name = "contrasena", length = 100)
    private String contrasena;

    @Column(name = "tipo_agarre_raton")
    private String tipoAgarreRaton;

    @Column(name = "formato_teclado_favorito")
    private String formatoTecladoFavorito;

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = false)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private List<Compra> compras = new ArrayList<>();

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = false)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private List<Valoracion> valoraciones = new ArrayList<>();

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = false)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private List<Ticket> tickets = new ArrayList<>();
}
