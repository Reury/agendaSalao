package com.reury.agendasalao.services;

import com.reury.agendasalao.models.Cliente;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class AssinaturaService {

    public void processarPagamento(Cliente cliente) {
        if (cliente.getMetodoPagamentoPreferido() == null) {
            throw new IllegalArgumentException("Método de pagamento não definido para o cliente.");
        }

        // Simula o processamento do pagamento
        System.out.println("Processando pagamento para o cliente: " + cliente.getNome());
        System.out.println("Método de pagamento: " + cliente.getMetodoPagamentoPreferido());

        // Atualiza o status da assinatura
        cliente.setAssinaturaAtiva(true);
        cliente.setDataInicioAssinatura(LocalDate.now());
        cliente.setDataFimAssinatura(LocalDate.now().plusMonths(1)); // Exemplo: assinatura válida por 1 mês
    }

    public boolean verificarAssinaturaAtiva(Cliente cliente) {
        if (cliente.getDataFimAssinatura() == null) {
            return false;
        }
        return LocalDate.now().isBefore(cliente.getDataFimAssinatura());
    }
}