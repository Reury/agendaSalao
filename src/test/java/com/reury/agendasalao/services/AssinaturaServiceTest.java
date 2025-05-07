package com.reury.agendasalao.services;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import com.reury.agendasalao.models.Cliente;

public class AssinaturaServiceTest {

    @Test
    void testVerificarAssinaturaAtiva() {
        AssinaturaService assinaturaService = new AssinaturaService();

        // Cenário: Assinatura válida (dataFimAssinatura no futuro)
        Cliente cliente = new Cliente();
        cliente.setDataFimAssinatura(LocalDate.now().plusDays(10)); // Assinatura válida

        boolean resultado = assinaturaService.verificarAssinaturaAtiva(cliente);

        assertTrue(resultado); // A assinatura deve estar ativa
    }

    @Test
    void testVerificarAssinaturaExpirada() {
        AssinaturaService assinaturaService = new AssinaturaService();

        // Cenário: Assinatura expirada (dataFimAssinatura no passado)
        Cliente cliente = new Cliente();
        cliente.setDataFimAssinatura(LocalDate.now().minusDays(1)); // Assinatura expirada

        boolean resultado = assinaturaService.verificarAssinaturaAtiva(cliente);

        assertFalse(resultado); // A assinatura deve estar inativa
    }

    @Test
    void testVerificarAssinaturaSemDataFim() {
        AssinaturaService assinaturaService = new AssinaturaService();

        // Cenário: Assinatura sem data de término (dataFimAssinatura é null)
        Cliente cliente = new Cliente();
        cliente.setDataFimAssinatura(null); // Sem data de término

        boolean resultado = assinaturaService.verificarAssinaturaAtiva(cliente);

        assertFalse(resultado); // A assinatura deve estar inativa
    }
}