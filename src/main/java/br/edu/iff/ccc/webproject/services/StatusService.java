package br.edu.iff.ccc.webproject.services;

import org.springframework.stereotype.Service;
import java.util.Map;

@Service
public class StatusService {

    public Map<String, String> obterStatusDetalhado() {
        
        boolean dependenciasOk = true; 

        return Map.of(
            "status", dependenciasOk ? "API is running" : "Degraded",
            "version", "1.0.0",
            "health", dependenciasOk ? "Active" : "Unavailable"
        );
    }
}