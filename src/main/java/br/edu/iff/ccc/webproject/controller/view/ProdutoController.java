package br.edu.iff.ccc.webproject.controller.view;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.edu.iff.ccc.webproject.dto.ProdutoRequest;
import br.edu.iff.ccc.webproject.services.ProdutoUserCase;

@Controller
@RequestMapping("/produto")
public class ProdutoController {
    
    private final ProdutoUserCase produtoUserCase;

    public ProdutoController(ProdutoUserCase produtoUserCase) {
        this.produtoUserCase = produtoUserCase;
    }

    @GetMapping("/novo")
    public String novoProduto(Model model) {
        ProdutoRequest novoProduto = new ProdutoRequest();
        // A chave "produto" é o que usaremos no th:object do Thymeleaf
        model.addAttribute("produto", novoProduto); 
        return "produtoForm"; // Retorna templates/produtoForm.html
    }
    
    @PostMapping
    public String criarProduto(ProdutoRequest produtoRequest) {
        this.produtoUserCase.criarProduto(produtoRequest);
        // Redireciona para a listagem após salvar, evitando reenvio do formulário
        return "redirect:/produto"; 
    }   

    @GetMapping
    public String listarProdutos(Model model) {
        // Para a página produtos.html funcionar, precisamos injetar a lista nela
        model.addAttribute("produtos", this.produtoUserCase.listarProdutos());
        return "produtos"; // Retorna templates/produtos.html
    }
}