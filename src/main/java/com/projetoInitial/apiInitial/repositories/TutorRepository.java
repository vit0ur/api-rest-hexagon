package com.projetoInitial.apiInitial.repositories;

import com.projetoInitial.apiInitial.models.Tutor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TutorRepository extends JpaRepository<Tutor, Long> {
}