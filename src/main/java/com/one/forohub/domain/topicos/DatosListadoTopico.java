package com.one.forohub.domain.topicos;

import java.time.LocalDateTime;

public record DatosListadoTopico(
        Long id,
        String title,
        String message,
        LocalDateTime createdAt
) {
    public DatosListadoTopico(Topico topico) {
        this(topico.getId(), topico.getTitle(), topico.getMessage(), topico.getCreatedAt());
    }
}
