package com.one.forohub.domain.topicos;

import com.one.forohub.domain.cursos.Curso;
import com.one.forohub.domain.usuarios.Usuario;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Table(name = "topics")
@Entity(name = "Topic")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Topico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String message;
    private LocalDateTime createdAt;
    private Boolean isActive;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private Usuario user;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "course_id")
    private Curso course;
    @OneToMany(mappedBy = "topic", fetch = FetchType.LAZY)

    public void updateTopic(DatosActualizarTopico datosActualizarTopico) {
        boolean isUpdated = false;
        if (datosActualizarTopico.title() != null) {
            this.title = datosActualizarTopico.title();
            isUpdated = true;
        }
        if (datosActualizarTopico.message() != null) {
            this.message = datosActualizarTopico.message();
            isUpdated = true;
        }
        if (isUpdated) {
            this.createdAt = LocalDateTime.now();
        }
    }

    public void deactivateTopic() {
        this.isActive = false;
    }

}
