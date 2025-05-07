package com.reury.agendasalao.models;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class ClienteTest {

    @Test
    void testEstadoInicialDoCliente() {
        // Cria um cliente com o construtor
        Endereco endereco = new Endereco("01001-000");
        Cliente cliente = new Cliente("João Silva", "joao@email.com", "123456789", endereco);

        // Verifica o estado inicial
        assertEquals("João Silva", cliente.getNome());
        assertEquals("joao@email.com", cliente.getEmail());
        assertEquals("123456789", cliente.getTelefone());
        assertEquals(endereco, cliente.getEndereco());
        assertFalse(cliente.isAssinaturaAtiva()); // Assinatura deve ser inativa por padrão
        assertNull(cliente.getTipoAssinatura());
        assertNull(cliente.getDataInicioAssinatura());
        assertNull(cliente.getDataFimAssinatura());
        assertNull(cliente.getMetodoPagamentoPreferido());
    }

    @Test
    void testSettersEGetters() {
        // Cria um cliente vazio
        Cliente cliente = new Cliente();

        // Configura os campos
        cliente.setNome("Maria Oliveira");
        cliente.setEmail("maria@email.com");
        cliente.setTelefone("987654321");
        cliente.setAssinaturaAtiva(true);
        cliente.setTipoAssinatura(TipoAssinatura.PREMIUM);
        cliente.setDataInicioAssinatura(LocalDate.now());
        cliente.setDataFimAssinatura(LocalDate.now().plusDays(30));
        cliente.setMetodoPagamentoPreferido("cartao");

        // Verifica os valores configurados
        assertEquals("Maria Oliveira", cliente.getNome());
        assertEquals("maria@email.com", cliente.getEmail());
        assertEquals("987654321", cliente.getTelefone());
        assertTrue(cliente.isAssinaturaAtiva());
        assertEquals(TipoAssinatura.PREMIUM, cliente.getTipoAssinatura());
        assertNotNull(cliente.getDataInicioAssinatura());
        assertNotNull(cliente.getDataFimAssinatura());
        assertEquals("cartao", cliente.getMetodoPagamentoPreferido());
    }
}