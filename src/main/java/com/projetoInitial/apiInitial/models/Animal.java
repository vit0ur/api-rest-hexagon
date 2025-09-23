package com.projetoInitial.apiInitial.models;

import jakarta.persistence.*;
import lombok.Data;


@Entity
@Data
public class Animal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String especie;
    private int idade;
    private String nome;
    private String raca;

    @ManyToOne
    @JoinColumn(name = "tutor_id")
    private Tutor tutor;

    public Animal() {
    }

    public Animal(Long id, String especie, int idade, String nome, String raca) {
       this.id = id;
       this.especie = especie;
       this.idade = idade;
       this.nome = nome;
       this.raca = raca;
    }

    public Animal(Long id, String especie, int idade, String nome, String raca, Tutor tutor) {
        this.id = id;
        this.especie = especie;
        this.idade = idade;
        this.nome = nome;
        this.raca = raca;
        this.tutor = tutor;
    }

    public Tutor getTutor() {
        return tutor;
    }

    public void setTutor(Tutor tutor) {
        this.tutor = tutor;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEspecie() {
        return especie;
    }

    public void setEspecie(String especie) {
        this.especie = especie;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getRaca() {
        return raca;
    }

    public void setRaca(String raca) {
        this.raca = raca;
    }
}