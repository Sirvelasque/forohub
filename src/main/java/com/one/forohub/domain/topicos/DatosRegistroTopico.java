package com.one.forohub.domain.topicos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DatosRegistroTopico(
        @NotNull
        Long userId,
        @NotBlank
        String message,
        @NotBlank
        String courseName,
        @NotBlank
        String title
) {
}
