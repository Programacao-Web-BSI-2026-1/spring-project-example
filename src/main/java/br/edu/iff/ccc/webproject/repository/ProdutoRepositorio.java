package br.edu.iff.ccc.webproject.repository;

import java.util.ArrayList;
import java.util.List;

import br.edu.iff.ccc.webproject.entities.Produto;

public class ProdutoRepositorio {

    private List<Produto> produtos;

    public ProdutoRepositorio() {
        // Inicialização do repositório, se necessário
        this.produtos = new ArrayList<Produto>(); 

    }

    public void salvar(Produto produto) {
        // Lógica para salvar o produto no repositório
        this.produtos.add(produto);
        System.out.println("Produto salvo: " + produto.getNome());
    }

    public Produto buscarPorId(String id) {
        // Lógica para buscar um produto pelo ID no repositório
       return null; // Retornar o produto encontrado ou null se não encontrado
    }

}
