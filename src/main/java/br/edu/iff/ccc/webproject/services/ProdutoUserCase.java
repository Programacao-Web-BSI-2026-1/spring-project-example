package br.edu.iff.ccc.webproject.services;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import br.edu.iff.ccc.webproject.dto.ProdutoRequest;
import br.edu.iff.ccc.webproject.entities.Produto;
import br.edu.iff.ccc.webproject.repository.ProdutoRepositorio;

@Service
public class ProdutoUserCase {

    private final ProdutoRepositorio produtoRepositorio;
    
    public ProdutoUserCase(ProdutoRepositorio produtoRepositorio) {
        this.produtoRepositorio = produtoRepositorio;
    }

    public void criarProduto(ProdutoRequest produto) {
        UUID id = UUID.randomUUID();
        Produto novoProduto = new Produto(id, produto.getNome(), produto.getDescricao());
        this.produtoRepositorio.salvar(novoProduto);
    } 

    public List<Produto> listarProdutos() {
        return this.produtoRepositorio.listarTodos();
    }

    public void atualizarProduto() {
        // Lógica para atualizar um produto
    }

    public void deletarProduto() {
        // Lógica para deletar um produto
    }

    public void buscarProduto() {
        // Lógica para buscar um produto
    }

    public void validarProduto() {
        // Lógica para validar um produto
    }   
}