package com.one.forohub.controller;

import com.one.forohub.domain.topicos.DatosActualizarTopico;
import com.one.forohub.domain.topicos.DatosListadoTopico;
import com.one.forohub.domain.topicos.DatosRegistroTopico;
import com.one.forohub.domain.topicos.TopicoService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@ResponseBody
@RequestMapping("/topics")
@SecurityRequirement(name = "bearer-key")
public class TopicController {

    @Autowired
    private TopicoService topicService;

    @PostMapping
    public ResponseEntity<DatosListadoTopico> addTopic(@RequestBody @Valid DatosRegistroTopico topicData) {
        DatosListadoTopico newTopic = topicService.addTopic(topicData);
        return ResponseEntity.ok(newTopic);
    }

    @GetMapping
    public ResponseEntity<Page<DatosListadoTopico>> listTopics(@PageableDefault(size = 10) Pageable pageable) {
        return ResponseEntity.ok(topicService.getTopics(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<DatosListadoTopico> getTopic(@PathVariable Long id) {
        return ResponseEntity.ok(topicService.getTopic(id));
    }

    @PutMapping
    public ResponseEntity<DatosListadoTopico> updateTopic(@RequestBody @Valid DatosActualizarTopico updateData) {
        return ResponseEntity.ok(topicService.updateTopic(updateData));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTopic(@PathVariable Long id) {
        topicService.deactivateTopic(id);
        return ResponseEntity.noContent().build();
    }


}

