package br.edu.iff.ccc.webproject.controller.view;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;



@Controller
@RequestMapping("/inicial")
public class MainViewController {

    // http://localhost:8080/inicial
    @GetMapping
    public String getPaginaInicial() {
        return "inicial.html";
    }  
     
    // http://localhost:8080/inicial/1?param=valor 
    @GetMapping("/{id}")
    public String getExemploParametros(@PathVariable("id") String id, @RequestParam("param") String param, Model model) {
        model.addAttribute("id", id);
        model.addAttribute("var1", param);
        System.out.println("ID: " + id);
        System.out.println("Param: " + param);
        return "inicial.html";
    }
    


}
