package br.edu.iff.ccc.webproject.controller.view;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.edu.iff.ccc.webproject.dto.ProdutoRequest;
import br.edu.iff.ccc.webproject.services.ProdutoUserCase;
import jakarta.validation.Valid;

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
        model.addAttribute("produtoRequest", novoProduto); 
        return "produtoForm"; // Retorna templates/produtoForm.html
    }
    
    
    // Quando o formulário é enviado, os dados são mapeados para o objeto ProdutoRequest, por ter o mesmo nome de atributos do formulário
    // O @ModelAttribute é usado para vincular o objeto ProdutoRequest ao formulário, caso o nome do parâmetro seja diferente do nome do objeto no formulário, podemos usar @ModelAttribute("produto") ProdutoRequest produtoRequest
    // O @Valid é usado para ativar a validação do objeto ProdutoRequest
    // O BindingResult é usado para capturar os erros de validação

    @PostMapping
    public String criarProduto(@Valid ProdutoRequest produtoRequest, BindingResult result) {
        if (result.hasErrors()) {
            // Se houver erros de validação, retorna para o formulário com os erros
            return "produtoForm";
        }
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