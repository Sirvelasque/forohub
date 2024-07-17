package com.one.forohub.domain.cursos;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotBlank;

public record DatosRegistroCurso(

        @NotBlank
        String name,
        @Nullable
        String category
) {
}
