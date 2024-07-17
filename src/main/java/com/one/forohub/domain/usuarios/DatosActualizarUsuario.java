package com.one.forohub.domain.usuarios;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

public record DatosActualizarUsuario(
        @NotNull
        Long id,
        @Nullable
        String name,
        @Nullable
        @Email
        String email,
        @Nullable
        String password
) {
}
