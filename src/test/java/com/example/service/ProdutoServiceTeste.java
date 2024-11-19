package com.example.service;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.entities.Produto;
import com.example.repository.ProdutoRepository;

import jakarta.transaction.Transactional;

@SpringBootTest //Carrega o contexto completo do Spring para testes
@Transactional // Garante que as operações no banco de dados serão revertidas após cada teste
class ProdutoServiceTeste {

	@Autowired
	private ProdutoService produtoService;
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	@BeforeEach 
	void setUp() {
		produtoRepository.deleteAll(); //Limpa o banco de dados antes de cada teste
	}
	
	@DisplayName("Testando salvar Hóspede")
	@Test
	void testSalvarProduto() {
		Produto produto = new Produto (null, "Aerosol", "Importante", "R$ 20");
		
		Produto resultado = produtoService.salvarProduto(produto);
		
		assertNotNull(resultado);
		assertEquals("Aerosol", resultado.getNome());
		assertTrue(resultado.getId() > 0);
	}
	
	@DisplayName("Testando listar todos os Hóspedes")
	@Test
	void testListarTodosProduto() {
		Produto produto1 = new Produto (null, "Aerosol", "Importante", "R$ 20");
		Produto produto2 = new Produto (null, "Rollon", "Necessario", "R$ 10");
		
		produtoService.salvarProduto(produto1);
		produtoService.salvarProduto(produto2);
		
		List<Produto> resultado = produtoService.listarTodos();
		
		assertNotNull(resultado);
		assertEquals(2, resultado.size());
	}
	
	@DisplayName("Testando Hóspedes pelo Id")
	@Test
	void testBuscarPorId() {
		Produto produto = new Produto (null, "Aerosol", "Importante", "R$ 20");
		
		Produto salvo = produtoService.salvarProduto(produto);
		Optional<Produto> resultado = produtoService.buscarPorId(salvo.getId());
		
		assertTrue(resultado.isPresent());
		assertEquals("Aerosol" , resultado.get().getNome());
	}
	
	@DisplayName("Testando atualizar Hóspedes")
	@Test
	void testAtualizarProduto() {
		Produto produto = new Produto (null, "Rollon", "Necessario", "R$ 10");
		Produto salvo = produtoService.salvarProduto(produto);
		
		salvo.setNome("Rollon");
		salvo.setDescricao("Necessario");
		
		Produto atualizado = produtoService.atualizarProduto(salvo);
		
		assertNotNull(atualizado);
		assertEquals("Rollon", atualizado.getNome());
		assertEquals("Necessario" , atualizado.getDescricao());
	}
	
	@DisplayName("Testando deletar Hóspedes")
	@Test
	void testDeletarProduto() {
		Produto produto = new Produto (null, "Aerosol", "Importante", "R$ 20");
		
		Produto salvo = produtoService.salvarProduto(produto);
		produtoService.deletarProduto(salvo.getId());
		
		Optional<Produto> resultado = produtoService.buscarPorId(salvo.getId());

		assertTrue(resultado.isEmpty());
	}

}
