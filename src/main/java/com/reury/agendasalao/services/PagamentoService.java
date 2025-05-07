package com.reury.agendasalao.services;

import com.reury.agendasalao.strategies.pagamento.MetodoPagamento;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class PagamentoService {

    private final Map<String, MetodoPagamento> metodosPagamento;

    @Autowired
    public PagamentoService(Map<String, MetodoPagamento> metodosPagamento) {
        this.metodosPagamento = metodosPagamento;
    }

    public void processarPagamento(String metodo, double valor) {
        MetodoPagamento metodoPagamento = metodosPagamento.get(metodo);
        if (metodoPagamento == null) {
            throw new IllegalArgumentException("Método de pagamento não encontrado: " + metodo);
        }
        metodoPagamento.processarPagamento(valor);
    }
}