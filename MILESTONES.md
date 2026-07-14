Tarefa para entrega até 25/05/2026

 Incluir milestone no projeto do GIthub: Documentação Inicial (TR01)
 
 Descrição: A documentação inicial do projeto deverá conter um README, com detalhamento sobre a aplicação proposta e uma pasta "docs" com os Diagrama de Classe e os requisitos básicos de negócio e o wireframe da aplicação
 O objetivo desta primeira etapa consiste também na organização do repositório e criação da estrutura para gerenciamento do projeto no Github
 
 Padrão de Nomeação do pullrequest: TR01-Documentação Inicial
 
=======================================================================================================================


Tarefa para entrega até 22/06/2026

Incluir milestone no projeto do GIthub: "Preparação Estrutura Projeto" 

Descrever as "issues" para a preparação do ambiente e estrutura básica do projeto
Incluir as dependências do projeto (Spring Web, Spring Data e Thymeleaf e Banco H2) 
Incluir, nome, descrição e nomes dos pacotes básicos (controller view e apirest ) 
Configurar os arquivos que não serão versionados (.gitignore)
A aplicação deverá estar respondendo minimamente para uma url que direciona para a página principal do projeto ex.: /home com o conteúdo informando que está em construção e para a URL /api/v1
Submeter o Pullrequest com as alterações


 Padrão de Nomeação do pullrequest: TR02-Preparacao Ambiente


=======================================================================================================================


Tarefa para entrega até 13/07/2026

Incluir milestone no projeto do GIthub: "Estruturação da Camada de Controllers View"  
Objetivo: Implementar a estrutura completa da camada de Controller da aplicação, ou seja todos os controllers necessários para gerir as requisições HTTP, para as páginas renderizadas com Thymeleaf (@Controller). Ao final desta milestone, todas as rotas principais da aplicação devem estar mapeadas, prontas para receber a lógica de negócio na próxima fase.


 Padrão de Nomeação do pullrequest: TR03-ViewController

=======================================================================================================================

Tarefa para entrega até 20/07/2026

Incluir milestone no projeto do Github: "Integração do Fluxo MVC: Views, Use Cases e Repositório em Memória"

Objetivo: Implementar o fluxo de ponta a ponta para uma entidade da aplicação, garantindo a correta separação de responsabilidades. Esta etapa exige interligar a camada de Interface (Controllers recebendo DTOs e renderizando formulários/listas com Thymeleaf), a camada de Aplicação (Use Cases isolando as regras de negócio e geração de identificadores) e a camada de Dados (Repositórios gerenciados pelo Spring gerindo o estado em memória). Ao final desta milestone, a funcionalidade de cadastro e listagem deve estar operando de forma integrada, com a injeção de dependências adequadamente configurada (@Service, @Repository) 

Padrão de Nomeação do pull request: TR04-EstruturaCoreBase