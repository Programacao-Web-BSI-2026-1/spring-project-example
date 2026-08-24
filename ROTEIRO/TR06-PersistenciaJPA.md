📚 MATERIAL DIDÁTICO E ROTEIRO DE ENTREGASPersistência com Spring Data JPA, Banco H2 e Testes AutomatizadosPARTE 1: Guia Prático - Persistência com Spring Data JPA e Testes🎯 ObjetivoAté agora, nossa aplicação funcionou utilizando coleções em memória (como ArrayList). O problema dessa abordagem é a volatilidade: sempre que reiniciamos o servidor, todos os dados são perdidos.Neste guia, vamos dar o próximo passo arquitetural: substituir o repositório em memória por um banco de dados relacional real utilizando o Spring Data JPA e o banco de dados em memória H2. Também aprenderemos a garantir a qualidade do nosso código com Testes Automatizados.1. Configurando o Banco de Dados (H2)O H2 é um banco de dados relacional leve e rápido, ideal para desenvolvimento. Para que o Spring Boot saiba como se conectar a ele, precisamos configurar o arquivo application.properties (localizado em src/main/resources).Adicione as seguintes linhas:# Configurações de Conexão do Banco H2
spring.datasource.url=jdbc:h2:mem:testdb
spring.datasource.driverClassName=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=password

# Ativa a criação e atualização automática das tabelas (DDL) baseadas nas Nossas Entidades
spring.jpa.hibernate.ddl-auto=update

# Exibe no console os comandos SQL executados (Ótimo para debug e aprendizagem!)
spring.jpa.show-sql=true

# Habilita a interface web gráfica do banco de dados no navegador
spring.h2.console.enabled=true
spring.h2.console.path=/h2-console
💡 Dica: Com a aplicação rodando, você pode acessar http://localhost:8080/h2-console e fazer consultas SQL (SELECT, INSERT) diretamente no seu navegador!2. Mapeamento Objeto-Relacional (Anotações JPA)Precisamos ensinar ao Spring Boot como transformar nossa classe Java em uma tabela do banco de dados. Fazemos isso utilizando as anotações do pacote jakarta.persistence.Abra a sua entidade (ex: Produto.java) e atualize:import java.util.UUID;
import jakarta.persistence.*;

@Entity // 1. Define que esta classe é mapeada para uma tabela
@Table(name = "tb_produto") // 2. (Opcional) Nomeia explicitamente a tabela no banco
public class Produto {

    @Id // 3. Define este atributo como Chave Primária (PK)
    @GeneratedValue(strategy = GenerationType.UUID) // 4. O banco gera o UUID automaticamente
    private UUID id;
    
    // 5. Mapeia a coluna adicionando restrições no nível do banco de dados (DDL)
    @Column(name = "nm_produto", nullable = false, unique = true, length = 100)
    private String nome;

    @Column(name = "ds_descricao", nullable = false)
    private String descricao;

    // ⚠️ ATENÇÃO: O JPA exige um construtor padrão (vazio) sem argumentos!
    public Produto() {}

    public Produto(String nome, String descricao) {
        this.nome = nome;
        this.descricao = descricao;
    }   

    // Getters e Setters...
    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }
}
Entendendo as Restrições (@Column)nullable = false: O banco de dados rejeitará cadastros sem este campo (NOT NULL).unique = true: Garante no banco que não existirão dois registros com o mesmo valor nesta coluna.length = 100: Define o tamanho máximo de caracteres no banco de dados (VARCHAR(100)).3. A Mágica do Spring Data JPA (JpaRepository)Agora, podemos apagar a nossa antiga classe de repositório que usava o ArrayList e substituí-la por uma Interface. O Spring criará a implementação concreta dinamicamente em tempo de execução.Crie a interface ProdutoRepository:import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, UUID> {
    // Pronto! Não precisamos escrever SQL nem implementar métodos manualmente.
    // O JpaRepository já nos fornece gratuitamente:
    // save(), findAll(), findById(), deleteById(), count(), etc.
}
Atualizando a Camada de Serviço (UseCase)O nosso ProdutoUseCase (ou ProdutoService) agora utilizará os métodos nativos fornecidos pelo Spring Data JPA:import org.springframework.stereotype.Service;
import java.util.List;
import java.util.UUID;

@Service
public class ProdutoUseCase {

    private final ProdutoRepository repository;
    
    public ProdutoUseCase(ProdutoRepository repository) {
        this.repository = repository;
    }

    public Produto criarProduto(ProdutoRequest request) {
        // Não precisamos mais gerar o UUID manualmente via código!
        Produto produto = new Produto(request.getNome(), request.getDescricao());
        return this.repository.save(produto); // Faz o INSERT no banco de dados
    } 

    public List<Produto> listarProdutos() {
        return this.repository.findAll(); // Executa: SELECT * FROM tb_produto
    }

    public Produto buscarPorId(UUID id) {
        return this.repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado!"));
    }

    public void deletarProduto(UUID id) {
        this.repository.deleteById(id); // Executa: DELETE FROM tb_produto WHERE id = ?
    }
}
4. Testes Automatizados de Repositório (@DataJpaTest)Para garantir que nossas restrições de banco (como unique = true ou nullable = false) estejam funcionando corretamente, criamos testes automatizados.O Spring nos fornece a anotação @DataJpaTest, que sobe um ambiente leve com o banco H2 isolado exclusivamente para o teste. Cada teste é executado dentro de uma transação que sofre Rollback automático ao final, deixando o banco limpo para o próximo teste.Crie a classe ProdutoRepositoryTest.java no diretório src/test/java/.../repository/:import static org.junit.jupiter.api.Assertions.*;

import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.DataIntegrityViolationException;

@DataJpaTest 
public class ProdutoRepositoryTest {

    @Autowired
    private ProdutoRepository repository;

    @Test
    void deveSalvarProdutoEGerarId() {
        // Arrange (Preparação)
        Produto produto = new Produto("Teclado Mecânico", "Switch Blue RGB");

        // Act (Ação)
        Produto salvo = repository.save(produto);

        // Assert (Verificação)
        assertNotNull(salvo.getId(), "O ID no formato UUID deveria ter sido gerado pelo banco");
        assertEquals("Teclado Mecânico", salvo.getNome());
    }

    @Test
    void deveBuscarProdutoPorId() {
        // Arrange
        Produto produto = repository.save(new Produto("Mouse Gamer", "16000 DPI"));

        // Act
        Optional<Produto> encontrado = repository.findById(produto.getId());

        // Assert
        assertTrue(encontrado.isPresent());
        assertEquals("Mouse Gamer", encontrado.get().getNome());
    }

    @Test
    void deveLancarExcecaoQuandoNomeDuplicado() {
        // Arrange
        Produto p1 = new Produto("Monitor 24", "144Hz IPS");
        Produto p2 = new Produto("Monitor 24", "Outra descrição com mesmo nome");
        repository.save(p1);

        // Act & Assert (Espera a exceção devido ao unique = true)
        assertThrows(DataIntegrityViolationException.class, () -> {
            repository.save(p2);
            repository.flush(); // Força a execução imediata do SQL no banco
        });
    }
}
Como executar os testes:Na sua IDE (IntelliJ, Eclipse ou VS Code), clique com o botão direito na classe de teste e selecione "Run As -> JUnit Test".Ou via terminal na raiz do projeto:./mvnw test
Se a barra do JUnit ficar verde, a persistência e as restrições do seu banco de dados estão validadas e funcionando!PARTE 2: Roteiro Geral de Entregas da Disciplina📌 Avaliação P1 (Publicando a Release v1.0.0)A entrega final da P1 exige a publicação de uma Release oficial da versão v1.0.0 no GitHub.Requisitos Obrigatórios:Aderência ao Domínio Próprio: Código alinhado à documentação inicial.CRUD Completo: Create, Read, Update, Delete para TODAS as entidades.Operações de Negócio e Relacionamentos: Integração entre entidades funcionando.Arquitetura (MVC Isolado): Controller, UseCase/Service, Repository.Branch Obrigatória: feature/P1-entrega-finalComandos Git para a P1:# 1. Preparar e enviar a branch
git checkout -b feature/P1-entrega-final
git add .
git commit -m "feat: finaliza implementacao do CRUD e operacoes da P1"
git push origin feature/P1-entrega-final

# 2. Criar e enviar a Tag
git tag -a v1.0.0 -m "Entrega P1 - CRUD Completo de Todas Entidades e Regras de Negocio"
git push origin v1.0.0
🚩 Milestone 05 (TR05) - Persistência com JPA e TestesPrazo para entrega: 28/08/2026Padrão de Nomeação do Pull Request: TR05-PersistenciaJPAPadrão da Branch: feature/TR05-persistenciaObjetivo: Evoluir a aplicação substituindo o repositório em memória por um banco de dados relacional real (H2) utilizando o Spring Data JPA e garantindo a qualidade com testes automatizados.📋 Requisitos Obrigatórios da TR05:Configuração do Banco de Dados (H2):Configurar application.properties (jdbc:h2:mem:testdb).Habilitar o console do H2 (spring.h2.console.enabled=true) e o log SQL (spring.jpa.show-sql=true).Mapeamento Objeto-Relacional (JPA):Anotações @Entity e @Table.Chave primária UUID com @Id e @GeneratedValue(strategy = GenerationType.UUID).Restrições nas colunas com @Column (nullable = false, unique = true, length = 100).Repositórios e Use Cases:Interfaces estendendo JpaRepository<Entidade, UUID>.Refatoração da camada Service/UseCase com métodos nativos (save, findAll, findById, deleteById).Testes Automatizados:Classes de teste com @DataJpaTest.Teste de gravação com geração de UUID, busca por ID e lançamento de DataIntegrityViolationException em violações de restrição.📝 Passo a Passo da Entrega da TR05:Desenvolver na branch feature/TR05-persistencia.Abrir Pull Request para a branch main com o título TR05-PersistenciaJPA.Vincular o Pull Request à Issue correspondente no GitHub.Garantir que a barra do JUnit fique verde.