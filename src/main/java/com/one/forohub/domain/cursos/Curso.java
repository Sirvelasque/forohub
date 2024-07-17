package com.one.forohub.domain.cursos;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "cursos")
@Entity(name = "Curso")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Curso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String categoria;

    public Curso(DatosRegistroCurso registroCurso) {
        this.nombre = registroCurso.getNombre();
        this.categoria = registroCurso.getCategoria();
    }

    public void actualizarCurso(DatosActualizarCurso actualizarCurso) {
        if (actualizarCurso.getNombre() != null) {
            this.nombre = actualizarCurso.getNombre();
        }
        if (actualizarCurso.getCategoria() != null) {
            this.categoria = actualizarCurso.getCategoria();
        }
    }
}
