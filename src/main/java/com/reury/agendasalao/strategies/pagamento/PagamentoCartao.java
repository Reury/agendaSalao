package com.reury.agendasalao.strategies.pagamento;

public class PagamentoCartao implements MetodoPagamento {
    @Override
    public void processarPagamento(double valor) {
        System.out.println("Processando pagamento de R$" + valor + " com cartão de crédito.");
    }
}