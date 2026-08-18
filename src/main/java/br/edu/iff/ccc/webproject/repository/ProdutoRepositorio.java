package br.edu.iff.ccc.webproject.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.edu.iff.ccc.webproject.entities.Produto;

@Repository
public interface ProdutoRepositorio extends JpaRepository<Produto, UUID> {

}


