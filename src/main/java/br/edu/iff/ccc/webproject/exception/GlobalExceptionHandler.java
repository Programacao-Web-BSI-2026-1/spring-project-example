package br.edu.iff.ccc.webproject.exception;

import java.net.URI;
import java.time.Instant;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
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
}