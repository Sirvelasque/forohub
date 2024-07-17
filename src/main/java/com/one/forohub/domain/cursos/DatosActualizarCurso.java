package com.one.forohub.domain.cursos;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotNull;

public record DatosActualizarCurso(
        @NotNull
        Long id,
        @Nullable
        String name,
        @Nullable
        String category
) {
}
