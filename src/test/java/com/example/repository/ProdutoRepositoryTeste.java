package com.example.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.example.entities.Produto;

@DataJpaTest
class ProdutoRepositoryTeste {

	@Autowired
	private ProdutoRepository produtoRepository;
	
	@DisplayName("Testando o save")
	@Test
	void testSalvarRepository() {
		//Given / Arrange
		Produto produto1 = new Produto(null, "Aerosol", "Importante", "R$ 20");
		
		//When / Act
		Produto saveProduto = produtoRepository.save(produto1);
		
		//Then / Assert
		assertNotNull(saveProduto);
		assertTrue(saveProduto.getId() > 0);
	}

	@DisplayName("Testando o get para todos os produtos")
	@Test
	void testGetAllRepository() {
		//Given / Arrange
		Produto produto1 = new Produto(null, "Aerosol", "Importante", "R$ 20");
		Produto produto2 = new Produto(null, "Rollon", "Necessario", "R$ 10");
		
		produtoRepository.save(produto1);
		produtoRepository.save(produto2);
		
		//When / Act
		List<Produto> produtoList = produtoRepository.findAll();
		
		//Then / Assert
		assertNotNull(produtoList); 
		assertEquals(2, produtoList.size());
	}
	
	@DisplayName("Testando o Get By Id")
	@Test
	void testGetByIdRepository() {
		//Given / Arrange
		Produto produto1 = new Produto(null, "Aerosol", "Importante", "R$ 20");
		
		produtoRepository.save(produto1);
		
		//When / Act
		Produto saveProduto = produtoRepository.findById(produto1.getId()).get(); 
		
		//Then / Assert
		assertNotNull(saveProduto);
		assertEquals(produto1.getId(), saveProduto.getId());
	}
	
	@DisplayName("Testando o Update")
	@Test
	void testUpdateRepository() {
		//Given / Arrange
		Produto produto1 = new Produto(null, "Rollon", "Necessario", "R$ 10");
		produtoRepository.save(produto1);
		
		//When / Act
		Produto saveProduto = produtoRepository.findById(produto1.getId()).get(); 
		produto1.setNome("Rollon");
		produto1.setPreco("R$ 10");
		
		Produto updateProduto = produtoRepository.save(saveProduto);
		
		//Then / Assert
		assertNotNull(updateProduto);
		assertEquals("Rollon", updateProduto.getNome()); 
		assertEquals("R$ 10", updateProduto.getPreco());
	}
	
	@DisplayName("Testando o Delete")
	@Test
	void testDeleteRepository() {
		//Given / Arrange
		Produto produto1 = new Produto(null, "Aerosol", "Importante", "R$ 20");
		
		produtoRepository.save(produto1);
		
		//When / Act
		produtoRepository.deleteById(produto1.getId());
		Optional<Produto> produtoOptional = produtoRepository.findById(produto1.getId()); 
		
		//Then / Assert
		assertTrue(produtoOptional.isEmpty());
	}
}
