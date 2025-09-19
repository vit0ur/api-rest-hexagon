package com.projetoInitial.apiInitial.controller;

import com.projetoInitial.apiInitial.models.Animal;
import com.projetoInitial.apiInitial.services.AnimailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/animal")
public class AnimalController {

    @Autowired
    private AnimailService animailService;

    @GetMapping
    public List<Animal> getAll() {
        return animailService.buscarTodos();
    }

    @GetMapping("/{id}")
    public Animal getById(@PathVariable Long id) {
        return animailService.buscarPorId(id);
    }

    @PostMapping
    public Animal add(@RequestBody Animal animal) {
        return animailService.cadastrarAnimal(animal);
    }

    @GetMapping("/idadeMaiorQue/{idade}")
    public List<Animal> getByIdadeMaiorQue(@PathVariable int idade) {
        return animailService.buscarAnimaisPorIdadeMaiorQue(idade);
    }

    @GetMapping("/nome/{nome}")
    public List<Animal> getAnimalByNome(@PathVariable String nome) {
        return animailService.buscarAnimaisPorNome(nome);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Animal> deleteAnimalById(@PathVariable Long id) {
        boolean excluido =   animailService.deleteAnimalPorId(id);
        if(excluido){
            return ResponseEntity.noContent().build();
        }else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Animal> putAnimal(@PathVariable Long id, @RequestBody Animal animalAtualizado) {
        return animailService.AtualizaAnimal(id, animalAtualizado);
    }
}