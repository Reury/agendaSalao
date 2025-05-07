package com.reury.agendasalao.services;

import com.reury.agendasalao.strategies.pagamento.MetodoPagamento;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class PagamentoServiceTest {

    private PagamentoService pagamentoService;

    @Mock
    private MetodoPagamento metodoPagamentoMock;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        // Configura um mapa de métodos de pagamento mockados
        Map<String, MetodoPagamento> metodosPagamento = new HashMap<>();
        metodosPagamento.put("cartao", metodoPagamentoMock);

        pagamentoService = new PagamentoService(metodosPagamento);
    }

    @Test
    void testProcessarPagamentoComMetodoValido() {
        // Configura o mock para processar o pagamento
        doNothing().when(metodoPagamentoMock).processarPagamento(100.0);

        // Chama o método a ser testado
        pagamentoService.processarPagamento("cartao", 100.0);

        // Verifica se o método de pagamento foi chamado corretamente
        verify(metodoPagamentoMock, times(1)).processarPagamento(100.0);
    }

    @Test
    void testProcessarPagamentoComMetodoInvalido() {
        // Tenta processar um pagamento com um método inválido
        assertThrows(IllegalArgumentException.class, () -> {
            pagamentoService.processarPagamento("pix", 50.0);
        });
    }
}