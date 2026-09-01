package br.edu.iff.ccc.webproject.controller.rest;

import br.edu.iff.ccc.webproject.dto.ProdutoRequest;
import br.edu.iff.ccc.webproject.entities.Produto;
import br.edu.iff.ccc.webproject.services.ProdutoUserCase;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/produtos")
public class ProdutoRestController {

    private final ProdutoUserCase produtoUserCase;

    public ProdutoRestController(ProdutoUserCase produtoUserCase) {
        this.produtoUserCase = produtoUserCase;
    }

    @PostMapping
    public ResponseEntity<Void> criarProduto(@RequestBody ProdutoRequest produtoRequest) {
        produtoUserCase.criarProduto(produtoRequest);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping
    public ResponseEntity<List<Produto>> listarProdutos() {
        List<Produto> produtos = produtoUserCase.listarProdutos();
        return ResponseEntity.ok(produtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Void> buscarProduto(@PathVariable UUID id) {
        produtoUserCase.buscarProduto(); 
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> atualizarProduto(@PathVariable UUID id, @RequestBody ProdutoRequest produtoRequest) {
        produtoUserCase.atualizarProduto();
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarProduto(@PathVariable UUID id) {
        produtoUserCase.deletarProduto();
        return ResponseEntity.noContent().build();
    }
}