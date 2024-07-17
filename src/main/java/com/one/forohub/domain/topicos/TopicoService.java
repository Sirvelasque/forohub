package com.one.forohub.domain.topicos;

import com.one.forohub.domain.cursos.Curso;
import com.one.forohub.domain.cursos.CursoRepository;
import com.one.forohub.domain.usuarios.Usuario;
import com.one.forohub.domain.usuarios.UsuarioRepository;
import com.one.forohub.infra.errores.ErrorDeConsulta;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;

@Service
public class TopicoService {

    @Autowired
    private UsuarioRepository userRepository;
    @Autowired
    private CursoRepository courseRepository;
    @Autowired
    private TopicoRepository topicRepository;

    public DatosListadoTopico addTopic(DatosRegistroTopico topicData) {
        Curso course;
        Usuario user;

        if (!userRepository.existsById(topicData.userId())) {
            throw new ErrorDeConsulta("User not found");
        }

        if (courseRepository.findByName(topicData.courseName()).isPresent()) {
            course = courseRepository.findByName(topicData.courseName()).get();
        } else {
            throw new ErrorDeConsulta("Course not found");
        }

        user = userRepository.findById(topicData.userId()).get();

        Topico topic = new Topico(
                null,
                topicData.title(),
                topicData.message(),
                LocalDateTime.now(),
                true,
                user,
                course,
                new ArrayList<>()
        );

        Topico savedTopic = topicRepository.save(topic);
        return new DatosListadoTopico(
                savedTopic.getId(),
                savedTopic.getTitle(),
                savedTopic.getMessage(),
                savedTopic.getCreatedAt()
        );
    }

    public Page<DatosListadoTopico> getTopics(Pageable pageable) {
        return topicRepository.findAllByStatusTrue(pageable).map(DatosListadoTopico::new);
    }

    public DatosListadoTopico getTopic(Long id) {
        if (!topicRepository.findById(id).isPresent() || !topicRepository.findStatusById(id)) {
            throw new ErrorDeConsulta("Topic not found");
        }
        Topico foundTopic = topicRepository.findById(id).get();

        return new DatosListadoTopico(
                foundTopic.getId(),
                foundTopic.getTitle(),
                foundTopic.getMessage(),
                foundTopic.getCreatedAt()
        );
    }

    @Transactional
    public DatosListadoTopico updateTopic(DatosActualizarTopico updateData) {
        if (!topicRepository.existsById(updateData.id())) {
            throw new ErrorDeConsulta("Topic not found");
        }
        Topico topic = topicRepository.getReferenceById(updateData.id());
        topic.updateTopic(updateData);
        return new DatosListadoTopico(topic);
    }

    @Transactional
    public void deactivateTopic(Long id) {
        if (!topicRepository.existsById(id)) {
            throw new ErrorDeConsulta("Topic not found");
        }
        Topico topic = topicRepository.getReferenceById(id);
        topic.deactivateTopic();
    }


}
