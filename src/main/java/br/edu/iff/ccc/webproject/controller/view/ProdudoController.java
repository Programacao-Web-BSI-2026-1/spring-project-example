package br.edu.iff.ccc.webproject.controller.view;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.edu.iff.ccc.webproject.dto.ProdutoRequest;
import br.edu.iff.ccc.webproject.services.ProdutoUserCase;
import org.springframework.web.bind.annotation.GetMapping;



@Controller
@RequestMapping("/produto")
public class ProdudoController {

    private final ProdutoUserCase produtoUserCase;

    ProdudoController(ProdutoUserCase produtoUserCase) {
        this.produtoUserCase = produtoUserCase;
    }

    @GetMapping("/novo")
    public String novoProduto(Model model) {
        ProdutoRequest novoProduto  = new ProdutoRequest();
        model.addAttribute("produto", novoProduto); 
        return "produtoForm.html";
    }
    

    @PostMapping()
    public String criarProduto(ProdutoRequest produtoRequest) {
        // Lógica para criar um produto
        this.produtoUserCase.criarProduto(produtoRequest);
        return "produtos.html";
    }   

}
