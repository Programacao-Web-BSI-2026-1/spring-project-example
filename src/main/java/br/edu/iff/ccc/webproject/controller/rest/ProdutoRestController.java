package br.edu.iff.ccc.webproject.controller.rest;

import br.edu.iff.ccc.webproject.dto.ProdutoRequest;
import br.edu.iff.ccc.webproject.entities.Produto;
import br.edu.iff.ccc.webproject.services.ProdutoUserCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/produtos")
@Tag(name = "Produtos", description = "Gerenciamento do catálogo de produtos")
public class ProdutoRestController {

    private final ProdutoUserCase produtoUserCase;

    public ProdutoRestController(ProdutoUserCase produtoUserCase) {
        this.produtoUserCase = produtoUserCase;
    }

    @Operation(summary = "Criar um novo produto", description = "Adiciona um novo produto ao banco de dados.")
    @ApiResponses({
        @ApiResponse(responseCode = "201", description = "Produto criado com sucesso"),
        @ApiResponse(responseCode = "400", description = "Dados inválidos fornecidos no corpo da requisição")
    })
    @PostMapping
    public ResponseEntity<Void> criarProduto(@Valid @RequestBody ProdutoRequest produtoRequest) {
        produtoUserCase.criarProduto(produtoRequest);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Operation(summary = "Listar todos os produtos", description = "Retorna uma lista contendo todos os produtos cadastrados.")
    @ApiResponse(responseCode = "200", description = "Listagem recuperada com sucesso")
    @GetMapping
    public ResponseEntity<List<Produto>> listarProdutos() {
        List<Produto> produtos = produtoUserCase.listarProdutos();
        return ResponseEntity.ok(produtos);
    }

    @Operation(summary = "Buscar produto por ID", description = "Recupera os dados de um produto específico usando seu UUID.")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Produto encontrado"),
        @ApiResponse(responseCode = "404", description = "Produto não encontrado (Retorna formato RFC 7807)")
    })
    @GetMapping("/{id}")
    public ResponseEntity<Produto> buscarProduto(
            @Parameter(description = "UUID válido do produto", example = "d9b2d63d-a233-4123-8478-316827052594") 
            @PathVariable UUID id) {
        Produto produto = produtoUserCase.buscarProduto(id); 
        return ResponseEntity.ok(produto);
    }

    @Operation(summary = "Atualizar um produto", description = "Modifica os dados de um produto existente baseado no seu UUID.")
    @ApiResponses({
        @ApiResponse(responseCode = "204", description = "Produto atualizado com sucesso (Sem conteúdo no retorno)"),
        @ApiResponse(responseCode = "404", description = "Produto não encontrado (Retorna formato RFC 7807)"),
        @ApiResponse(responseCode = "400", description = "Dados inválidos fornecidos no corpo da requisição")
    })
    @PutMapping("/{id}")
    public ResponseEntity<Void> atualizarProduto(
            @Parameter(description = "UUID válido do produto") @PathVariable UUID id, 
            @Valid @RequestBody ProdutoRequest produtoRequest) {
        produtoUserCase.atualizarProduto();
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Deletar um produto", description = "Remove permanentemente um produto do sistema.")
    @ApiResponses({
        @ApiResponse(responseCode = "204", description = "Produto removido com sucesso (Sem conteúdo no retorno)"),
        @ApiResponse(responseCode = "404", description = "Produto não encontrado (Retorna formato RFC 7807)")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarProduto(
            @Parameter(description = "UUID válido do produto") @PathVariable UUID id) {
        produtoUserCase.deletarProduto();
        return ResponseEntity.noContent().build();
    }
}