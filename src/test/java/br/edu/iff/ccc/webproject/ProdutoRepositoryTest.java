package br.edu.iff.ccc.webproject;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.dao.DataIntegrityViolationException;

import br.edu.iff.ccc.webproject.entities.Produto;
import br.edu.iff.ccc.webproject.repository.ProdutoRepositorio;



@DataJpaTest // Configura um banco H2 em memória exclusivo para o teste e gerencia transações
public class ProdutoRepositoryTest {

    @Autowired
    private ProdutoRepositorio produtoRepository;

    @Test
    @DisplayName("Deve salvar um produto com sucesso e gerar o UUID")
    void deveSalvarProdutoComSucesso() {
        // Arrange (Preparação)
        Produto produto = new Produto("Teclado Mecânico", "Teclado RGB switch blue");

        // Act (Ação)
        Produto produtoSalvo = produtoRepository.save(produto);

        // Assert (Verificação)
        assertNotNull(produtoSalvo.getId(), "O ID (UUID) não deveria ser nulo após salvar");
        assertEquals("Teclado Mecânico", produtoSalvo.getNome());
    }

    @Test
    @DisplayName("Deve buscar um produto existente pelo ID")
    void deveBuscarProdutoPorId() {
        // Arrange
        Produto produto = new Produto("Mouse Gamer", "Mouse 3200 DPI");
        Produto produtoSalvo = produtoRepository.save(produto);

        // Act
        Optional<Produto> produtoEncontrado = produtoRepository.findById(produtoSalvo.getId());

        // Assert
        assertTrue(produtoEncontrado.isPresent(), "O produto deveria ter sido encontrado");
        assertEquals(produtoSalvo.getId(), produtoEncontrado.get().getId());
    }

    @Test
    @DisplayName("Deve lançar exceção ao tentar salvar produtos com nomes duplicados (unique=true)")
    void deveLancarExcecaoQuandoNomeDuplicado() {
        // Arrange
        Produto produto1 = new Produto("Monitor 24", "Monitor Full HD");
        Produto produto2 = new Produto("Monitor 24", "Outro monitor com o mesmo nome");

        // Salvamos o primeiro (deve funcionar)
        produtoRepository.save(produto1);

        // Act & Assert
        // Tentamos salvar o segundo e o JUnit verifica se o Spring lançou a exceção de integridade
        assertThrows(DataIntegrityViolationException.class, () -> {
            produtoRepository.save(produto2);
            // O flush força a sincronização com o banco imediatamente, disparando a restrição
            produtoRepository.flush(); 
        }, "Deveria lançar erro pois configuramos @Column(unique=true) no nome");
    }
}
