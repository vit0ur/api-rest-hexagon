package com.projetoInitial.apiInitial.services;

import com.projetoInitial.apiInitial.models.Animal;
import com.projetoInitial.apiInitial.repositories.AnimailRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class AnimailService {
    private AnimailRepository animailRepository;

    public AnimailService(AnimailRepository animailRepository) {
        this.animailRepository = animailRepository;
    }

    public List<Animal> buscarTodos(){
        return animailRepository.findAll();
    }

    public Animal buscarPorId(Long id){
        Optional<Animal> animal = animailRepository.findById(id);
        return animal.orElse(null);
    }

    public Animal cadastrarAnimal(Animal animal){
        return animailRepository.save(animal);
    }

    public List<Animal> buscarAnimaisPorIdadeMaiorQue(int idade){
        return animailRepository.findByIdadeGreaterThan(idade);
    }

    public List<Animal> buscarAnimaisPorNome(String nome){
        return animailRepository.findByNome(nome);
    }
    
    public boolean deleteAnimalPorId(Long id){
        if (animailRepository.existsById(id)) {
            animailRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }

    public ResponseEntity<Animal> AtualizaAnimal(Long id, Animal animalAtualizado){
        return animailRepository.findById(id)
                .map(animal -> {
                    animal.setEspecie(animalAtualizado.getEspecie());
                    animal.setIdade(animalAtualizado.getIdade());
                    animal.setNome(animalAtualizado.getNome());
                    animal.setRaca(animalAtualizado.getRaca());
                    Animal animalSalvo = animailRepository.save(animal);
                    return ResponseEntity.ok(animalSalvo);
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}