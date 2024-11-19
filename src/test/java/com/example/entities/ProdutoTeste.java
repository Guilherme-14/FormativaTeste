package com.example.entities;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ProdutoTeste {

	private Produto produto;
	
	@BeforeEach 
	void setUp() {
		//Arrange
		produto = new Produto(1L , "Aerosol", "Importante", "R$ 20");
	}
	
	@Test
	@DisplayName("Testando getter e setter do Id")
	void testId() {
		produto.setId(2L);
		//Assert
		assertEquals(2L, produto.getId());
	}
	
	@Test
	@DisplayName("Testando getter e setter do Nome")
	void testNome() {
		produto.setNome("Aerosol");
		//Assert
		assertEquals("Aerosol", produto.getNome());
	}
	
	@Test
	@DisplayName("Testando getter e setter do Descricao")
	void testDescricao() {
		produto.setDescricao("Importante");
		//Assert
		assertEquals("Importante", produto.getDescricao());
	}
	
	@Test
	@DisplayName("Testando getter e setter do Preco")
	void testPreco() {
		produto.setNome("R$ 20");
		//Assert
		assertEquals("R$ 20", produto.getPreco());
	}
	
	@Test
	@DisplayName("Testando todos os argumentos")
	void testConstrutor() {
		// Act
		Produto novoProduto = new Produto(3L, "Aerosol", "Importante", "R$ 20");
		// Assert
		assertAll("novoProduto", () -> assertEquals(3L, novoProduto.getId()),
				() -> assertEquals("Aerosol", novoProduto.getNome()),
				() -> assertEquals("Importante", novoProduto.getDescricao()),
				() -> assertEquals("R$ 20", novoProduto.getPreco()));
	}

}
