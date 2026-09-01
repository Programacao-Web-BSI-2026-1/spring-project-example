package br.edu.iff.ccc.webproject.controller.rest;

import br.edu.iff.ccc.webproject.services.StatusService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import java.util.Map;

@RestController
@RequestMapping("/api/v1")
@Tag(name = "Health & Status", description = "Monitoramento da infraestrutura da API")
public class MainRestViewController {

    private final StatusService statusService;

    public MainRestViewController(StatusService statusService) {
        this.statusService = statusService;
    }
    // @Operation explica o que o método faz
    @Operation(summary = "Verifica a saúde da API e suas dependências")
    // @ApiResponses documenta os possíveis retornos HTTP mapeados
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Sistemas operando normalmente"),
        @ApiResponse(responseCode = "503", description = "Degradação em serviços dependentes (ex: Banco de Dados indisponível)")
    })
    @GetMapping("/status")
    public ResponseEntity<Map<String, String>> getStatus() {
        Map<String, String> resposta = statusService.obterStatusDetalhado();
        return ResponseEntity.ok(resposta);
    }
    @RequestMapping(value = "/status", method = RequestMethod.HEAD)
    public ResponseEntity<Void> headStatus() {
        Map<String, String> statusData = statusService.obterStatusDetalhado();
        
        HttpHeaders headers = new HttpHeaders();
        headers.add("X-API-Version", statusData.get("version"));
        headers.add("X-API-Status", statusData.get("health"));
        
        return ResponseEntity.ok().headers(headers).build();
    }

    @RequestMapping(value = "/status", method = RequestMethod.OPTIONS)
    public ResponseEntity<Void> optionsStatus() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Allow", "GET, HEAD, OPTIONS");
        return ResponseEntity.ok().headers(headers).build();
    }
}