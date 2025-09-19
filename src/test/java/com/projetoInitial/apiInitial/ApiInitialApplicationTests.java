package com.projetoInitial.apiInitial;

import com.projetoInitial.apiInitial.models.Animal;
import com.projetoInitial.apiInitial.repositories.AnimailRepository;
import com.projetoInitial.apiInitial.services.AnimailService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class ApiInitialApplicationTests {
	private AnimailRepository animailRepository;
	private AnimailService animailService;

	@BeforeEach
	void setUp() {
		animailRepository = mock(AnimailRepository.class);
		animailService = new AnimailService(animailRepository);
	}

	@Test
	void testBuscarTodos() {
		Animal a1 = new Animal(1L, "Cachorro", 5, "Rex", "Labrador");
		Animal a2 = new Animal(1L, "Gato", 5, "Mimi", "Persa");

		when(animailRepository.findAll()).thenReturn(Arrays.asList(a1, a2));

		List<Animal> result = animailService.buscarTodos();

		assertEquals(2, result.size());
		verify(animailRepository).findAll();
	}

	@Test
	void testBuscarPorId_Encontrado() {
		Animal a1 = new Animal(1L, "Cachorro", 5, "Rex", "Labrador");
		when(animailRepository.findById(1L)).thenReturn(Optional.of(a1));

		Animal result = animailService.buscarPorId(1L);

		assertNotNull(result);
		assertEquals("Rex", result.getNome());
	}

	@Test
	void testBuscarPorId_NaoEncontrado() {
		when(animailRepository.findById(1L)).thenReturn(Optional.empty());

		Animal result = animailService.buscarPorId(1L);

		assertNull(result);
	}

	@Test
	void testCadastrarAnimal() {
		Animal a1 = new Animal(null, "Cachorro", 5, "Rex", "Labrador");
		when(animailRepository.save(a1)).thenReturn(a1);

		Animal result = animailService.cadastrarAnimal(a1);

		assertEquals("Rex", result.getNome());
		verify(animailRepository).save(a1);
	}

	@Test
	void testBuscarAnimaisPorIdadeMaiorQue() {
		Animal a1 = new Animal(1L, "Cachorro", 5, "Rex", "Labrador");
		when(animailRepository.findByIdadeGreaterThan(4)).thenReturn(List.of(a1));

		List<Animal> result = animailService.buscarAnimaisPorIdadeMaiorQue(4);

		assertEquals(1, result.size());
		assertEquals("Rex", result.get(0).getNome());
	}

	@Test
	void testBuscarAnimaisPorNome() {
		Animal a1 = new Animal(1L, "Cachorro", 5, "Rex", "Labrador");
		when(animailRepository.findByNome("Rex")).thenReturn(List.of(a1));

		List<Animal> result = animailService.buscarAnimaisPorNome("Rex");

		assertEquals(1, result.size());
		assertEquals("Rex", result.get(0).getNome());
	}

	@Test
	void testDeleteAnimalPorId_Existe() {
		when(animailRepository.existsById(1L)).thenReturn(true);

		boolean result = animailService.deleteAnimalPorId(1L);

		assertTrue(result);
		verify(animailRepository).deleteById(1L);
	}

	@Test
	void testDeleteAnimalPorId_NaoExiste() {
		when(animailRepository.existsById(1L)).thenReturn(false);

		boolean result = animailService.deleteAnimalPorId(1L);

		assertFalse(result);
		verify(animailRepository, never()).deleteById(anyLong());
	}

	@Test
	void testAtualizaAnimal_Encontrado() {
		Animal original = new Animal(1L, "Cachorro", 5, "Rex", "Labrador");
		Animal atualizado = new Animal(null, "Gato", 5, "Mimi", "Persa");

		when(animailRepository.findById(1L)).thenReturn(Optional.of(original));
		when(animailRepository.save(any(Animal.class))).thenReturn(atualizado);

		ResponseEntity<Animal> response = animailService.AtualizaAnimal(1L, atualizado);

		assertEquals(200, response.getStatusCodeValue());
		assertEquals("Mimi", response.getBody().getNome());
	}

	@Test
	void testAtualizaAnimal_NaoEncontrado() {
		Animal atualizado = new Animal(null, "Gato", 5, "Mimi", "Persa");
		when(animailRepository.findById(1L)).thenReturn(Optional.empty());

		ResponseEntity<Animal> response = animailService.AtualizaAnimal(1L, atualizado);

		assertEquals(404, response.getStatusCodeValue());
	}
}