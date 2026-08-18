# Cronograma de Entregas (Milestones)

Este documento detalha as etapas de desenvolvimento do projeto da disciplina. Cada etapa (milestone) possui um objetivo específico e deve ser entregue através de um Pull Request no repositório.

---

## 🚩 Milestone 1: Documentação Inicial (TR01)
* **Prazo para entrega:** 25/05/2026
* **Padrão de Nomeação do Pull Request:** `TR01-Documentação Inicial`

**Descrição:**
A documentação inicial do projeto deverá conter:
* Um arquivo `README.md` com o detalhamento sobre a aplicação proposta.
* Uma pasta `docs` contendo:
  * O Diagrama de Classes.
  * Os requisitos básicos de negócio.
  * O wireframe da aplicação.

O objetivo desta primeira etapa consiste também na organização do repositório e criação da estrutura para o gerenciamento do projeto utilizando o GitHub.

---

## 🚩 Milestone 2: Preparação da Estrutura do Projeto
* **Prazo para entrega:** 22/06/2026
* **Padrão de Nomeação do Pull Request:** `TR02-Preparacao Ambiente`

**Descrição e Requisitos:**
* Descrever as "issues" para a preparação do ambiente e estrutura básica do projeto.
* Incluir as dependências do projeto (Spring Web, Spring Data, Thymeleaf e Banco de Dados H2).
* Incluir o nome do projeto, descrição e criar os pacotes básicos (ex.: `controller.view` e `controller.apirest`).
* Configurar os arquivos que não serão versionados através do `.gitignore`.
* A aplicação deverá estar respondendo minimamente para:
  * Uma URL que direciona para a página principal (ex.: `/home`) com um conteúdo informando que está "em construção".
  * Uma URL direcionada para a API (ex.: `/api/v1`).

*Atenção: Submeter o Pull Request somente após aplicar todas estas alterações.*

---

## 🚩 Milestone 3: Estruturação da Camada de Controllers View
* **Prazo para entrega:** 13/07/2026
* **Padrão de Nomeação do Pull Request:** `TR03-ViewController`

**Objetivo:**
Implementar a estrutura completa da camada de Controller da aplicação, ou seja, todos os controllers necessários para gerir as requisições HTTP para as páginas renderizadas com Thymeleaf (`@Controller`). Ao final desta milestone, todas as rotas principais da aplicação devem estar mapeadas, prontas para receber a lógica de negócio na próxima fase.

---

## 🚩 Milestone 4: Integração do Fluxo MVC (Views, Use Cases e Repositório em Memória)
* **Prazo para entrega:** 20/07/2026
* **Padrão de Nomeação do Pull Request:** `TR04-EstruturaCoreBase`

**Objetivo:**
Implementar o fluxo de ponta a ponta para uma entidade da aplicação, garantindo a correta separação de responsabilidades. Esta etapa exige interligar a camada de Interface (Controllers recebendo DTOs e renderizando formulários/listas com Thymeleaf), a camada de Aplicação (Use Cases isolando as regras de negócio e geração de identificadores) e a camada de Dados (Repositórios gerenciados pelo Spring gerindo o estado em memória). 

Ao final desta milestone, a funcionalidade de cadastro e listagem deve estar operando de forma integrada, com a injeção de dependências adequadamente configurada utilizando as anotações `@Service` e `@Repository`.


---

## 🚩 Milestone 5: Persistência com Spring Data JPA e Testes
**Prazo para entrega:** 28/08/2026
**Padrão de Nomeação do Pull Request:** TR05-PersistenciaJPA
**Padrão da Branch:** TR05-persistencia

**Objetivo:** 
Evoluir a aplicação substituindo o repositório em memória por um banco de dados real (H2) utilizando o Spring Data JPA. O sistema deve mapear as entidades e garantir a integridade dos dados através de restrições no banco de dados (DDL via Hibernate). Por fim, a nova camada de repositório deve ter seu funcionamento garantido através de testes automatizados.

### 📋 Requisitos Obrigatórios da Entrega:

1. **Configuração do Banco de Dados (H2):**
   * Configurar corretamente o arquivo application.properties para conectar ao banco em memória H2.
   * Habilitar o console do H2 e o log de comandos SQL (spring.jpa.show-sql=true).

2. **Mapeamento Objeto-Relacional (JPA):**
   * Transformar as classes de domínio em tabelas utilizando a anotação @Entity.
   * Configurar a geração automática de chaves primárias (@Id e @GeneratedValue).
   * Adicionar restrições de banco de dados mapeadas nas colunas (ex: @Column(nullable = false, unique = true)).

3. **Repositórios e Use Cases:**
   * Criar as interfaces de repositório estendendo JpaRepository (eliminando as antigas classes com listas estáticas).
   * Refatorar a camada de Service/UseCase para utilizar os métodos fornecidos pelo Spring Data (save, findAll, findById, deleteById).

4. **Testes Automatizados:**
   * Criar uma classe de testes para o Repositório utilizando a anotação @DataJpaTest.
   * Implementar testes automatizados (JUnit) que verifiquem: a gravação com sucesso no banco, a busca por ID, e o lançamento de exceções ao violar as restrições da entidade (ex: tentar salvar um registro com nome duplicado).

### 📝 Padrão de Entrega:
O código deve ser desenvolvido na branch TR05-persistencia.
A entrega é feita através da abertura de um **Pull Request** para a branch main.
O código deve compilar sem erros, o fluxo MVC (telas) deve continuar funcionando normalmente e todos os testes automatizados devem estar passando (cor verde no JUnit).