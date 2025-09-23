package com.projetoInitial.apiInitial.services;

import com.projetoInitial.apiInitial.dto.AnimalDTO;
import com.projetoInitial.apiInitial.dto.TutorDTO;
import com.projetoInitial.apiInitial.models.Animal;
import com.projetoInitial.apiInitial.models.Tutor;
import com.projetoInitial.apiInitial.repositories.AnimailRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class AnimailService {
    private AnimailRepository animailRepository;
    private TutorService tutorService;


    public AnimailService(AnimailRepository animailRepository, TutorService tutorService) {
        this.animailRepository = animailRepository;
        this.tutorService = tutorService;
    }


    public List<Animal> buscarTodos(){
        return animailRepository.findAll();
    }

    public Animal buscarPorId(Long id){
        Optional<Animal> animal = animailRepository.findById(id);
        return animal.orElse(null);
    }

    public Animal cadastrarAnimal(AnimalDTO animalDTO) {
        Tutor tutor = null;

        if (animalDTO.tutor().getId() != null) {
            tutor = tutorService.buscarPorId(animalDTO.tutor().getId());
        }

        if (tutor == null) {
            TutorDTO tutorDTO = new TutorDTO(
                    animalDTO.tutor().getNome(),
                    animalDTO.tutor().getTelefone(),
                    animalDTO.tutor().getEmail()
            );
            tutor = tutorService.cadastrarTutor(tutorDTO);
        }

        Animal animal = new Animal();
        animal.setNome(animalDTO.nome());
        animal.setEspecie(animalDTO.especie());
        animal.setIdade(animalDTO.idade());
        animal.setRaca(animalDTO.raca());
        animal.setTutor(tutor);

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

    public ResponseEntity<Animal> AtualizaAnimal(Long id, AnimalDTO animalAtualizado){
        return animailRepository.findById(id)
                .map(animal -> {
                    animal.setEspecie(animalAtualizado.especie());
                    animal.setIdade(animalAtualizado.idade());
                    animal.setNome(animalAtualizado.nome());
                    animal.setRaca(animalAtualizado.raca());
                    Animal animalSalvo = animailRepository.save(animal);
                    return ResponseEntity.ok(animalSalvo);
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}