package com.projetoInitial.apiInitial.services;

import com.projetoInitial.apiInitial.dto.TutorDTO;
import com.projetoInitial.apiInitial.models.Tutor;
import com.projetoInitial.apiInitial.repositories.TutorRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class TutorService {
    private TutorRepository tutorRepository;

    public TutorService(TutorRepository tutorRepository) {
        this.tutorRepository = tutorRepository;
    }

    public List<Tutor> buscarTodos(){
        return tutorRepository.findAll();
    }

    public Tutor cadastrarTutor(TutorDTO tutorDTO){
        Tutor tutor = Tutor.builder()
                .nome(tutorDTO.nome())
                .email(tutorDTO.email())
                .telefone(tutorDTO.telefone())
                .build();

        return tutorRepository.save(tutor);
    }

    public boolean deleteTutorPorId (Long id){
        if(tutorRepository.existsById(id)) {
            tutorRepository.deleteById(id);
            return true;
        }else {
            return false;
        }
    }

    public ResponseEntity<Tutor> atualizaTutor (Long id, TutorDTO tutorDTO){
        return tutorRepository.findById(id).map(tutor -> {
            tutor.setNome(tutorDTO.nome());
            tutor.setEmail(tutorDTO.email());
            tutor.setTelefone(tutorDTO.telefone());
            Tutor tutorSalvo = tutorRepository.save(tutor);
            return ResponseEntity.ok(tutorSalvo);
        }).orElseGet(() -> ResponseEntity.notFound().build());
    }

    public Tutor buscarPorId(Long id){
        Optional<Tutor> tutor = tutorRepository.findById(id);
        return tutor.orElse(null);
    }
}