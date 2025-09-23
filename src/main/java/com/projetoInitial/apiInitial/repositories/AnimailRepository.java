package com.projetoInitial.apiInitial.repositories;

import com.projetoInitial.apiInitial.models.Animal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface AnimailRepository extends JpaRepository<Animal, Long> {
    List<Animal> findByIdadeGreaterThan(int idade);
    List<Animal> findByNome(String nome);

    @Query("DELETE FROM Animal a WHERE a.id = :id")
    void deleteById(@Param("id") Long id);

    @Query("SELECT MAX(a.id) + 1 FROM Animal a")
    Long findMaxId();
}