package br.edu.iff.ccc.webproject.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository; // Importação necessária

import br.edu.iff.ccc.webproject.entities.Produto;

@Repository // Permite que o Spring gerencie esta classe
public class ProdutoRepositorio {

    private List<Produto> produtos;

    public ProdutoRepositorio() {
        this.produtos = new ArrayList<>(); 
    }

    public void salvar(Produto produto) {
        this.produtos.add(produto);
        System.out.println("Produto salvo: " + produto.getNome());
    }

    // Novo método para permitir a listagem
    public List<Produto> listarTodos() {
        return this.produtos;
    }

    public Produto buscarPorId(String id) {
       return null; 
    }
}
