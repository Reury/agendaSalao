package com.reury.agendasalao.strategies.pagamento;

public class PagamentoPix implements MetodoPagamento {
    @Override
    public void processarPagamento(double valor) {
        System.out.println("Processando pagamento de R$" + valor + " via Pix.");
    }
}