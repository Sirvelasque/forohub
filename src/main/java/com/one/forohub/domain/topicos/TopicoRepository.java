package com.one.forohub.domain.topicos;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface TopicoRepository extends JpaRepository<Topico, Long> {

    @Query("SELECT t.isActive FROM Topic t WHERE t.id = :topicId")
    Boolean findStatusById(Long topicId);

    @Query("SELECT t FROM Topic t WHERE t.isActive = TRUE")
    Page<Topico> findAllByStatusTrue(Pageable pageable);

}
