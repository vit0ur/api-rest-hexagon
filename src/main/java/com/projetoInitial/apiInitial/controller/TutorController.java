package com.projetoInitial.apiInitial.controller;

import com.projetoInitial.apiInitial.dto.TutorDTO;
import com.projetoInitial.apiInitial.models.Tutor;
import com.projetoInitial.apiInitial.services.TutorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tutor")
public class TutorController {
    @Autowired
    private TutorService tutorService;

    @GetMapping
    public List<Tutor> getAll(){
        return tutorService.buscarTodos();
    }

    @PostMapping
    public Tutor add(@RequestBody TutorDTO tutorDTO){
        return tutorService.cadastrarTutor(tutorDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Tutor> deleteAnimalById(@PathVariable Long id){
        boolean excluido = tutorService.deleteTutorPorId(id);

        if (excluido){
            return ResponseEntity.noContent().build();
        }else {
            return ResponseEntity.notFound().build();
        }
    }

}