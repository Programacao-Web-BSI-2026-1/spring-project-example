package br.edu.iff.ccc.webproject.exception;

import java.net.URI;
import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(RecursoNaoEncontradoException.class)
    public ProblemDetail handleRecursoNaoEncontrado(RecursoNaoEncontradoException ex, WebRequest request) {

        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, ex.getMessage());
        
        problemDetail.setType(URI.create("/api/v1/erros/recurso-nao-encontrado"));
        problemDetail.setTitle("Recurso Não Encontrado");
        problemDetail.setInstance(URI.create(request.getDescription(false).replace("uri=", "")));
        
        problemDetail.setProperty("timestamp", Instant.now());
        
        return problemDetail;
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ProblemDetail handleValidationExceptions(MethodArgumentNotValidException ex, WebRequest request) {
        // Inicializa o ProblemDetail com status 400 (Bad Request)
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(
                HttpStatus.BAD_REQUEST, 
                "A requisição possui campos inválidos. Verifique os detalhes fornecidos."
        );
        
        problemDetail.setType(URI.create("api/v1/erros/dados-invalidos"));
        problemDetail.setTitle("Dados Inválidos");
        problemDetail.setInstance(URI.create(request.getDescription(false).replace("uri=", "")));
        problemDetail.setProperty("timestamp", Instant.now());

        // Mapeia o nome do campo para a mensagem de erro definida no DTO
        Map<String, String> errosDeCampo = new HashMap<>();
        for (FieldError error : ex.getBindingResult().getFieldErrors()) {
            errosDeCampo.put(error.getField(), error.getDefaultMessage());
        }

        // Adiciona a coleção de erros como uma propriedade extra no JSON final
        problemDetail.setProperty("invalid_params", errosDeCampo);

        return problemDetail;
    }
}