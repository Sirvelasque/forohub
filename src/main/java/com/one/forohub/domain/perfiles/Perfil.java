package com.one.forohub.domain.perfiles;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "profiles")
@Entity(name = "Profile")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Perfil {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    public Perfil(DatosRegistroPerfil datosRegistroPerfil) {
        this.name = datosRegistroPerfil.name();
    }

    public void updateProfile(DatosActualizarPerfil datosActualizarPerfil) {
        if (datosActualizarPerfil.name() != null) {
            this.name = datosActualizarPerfil.name();
        }
    }
}
