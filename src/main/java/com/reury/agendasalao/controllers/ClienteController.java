package com.reury.agendasalao.controllers;

import com.reury.agendasalao.facades.ClienteFacade;
import com.reury.agendasalao.models.Cliente;
import com.reury.agendasalao.models.TipoAssinatura;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/clientes")
@Tag(name = "Clientes", description = "Gerenciamento de clientes e assinaturas")
public class ClienteController {

    @Autowired
    private ClienteFacade clienteFacade;

    @GetMapping
    @Operation(summary = "Listar Clientes", description = "Retorna todos os clientes cadastrados.")
    public List<Cliente> listarClientes() {
        return clienteFacade.listarClientes();
    }

    @PostMapping
    public Cliente cadastrarCliente(@RequestBody Cliente cliente) {
        return clienteFacade.cadastrarCliente(cliente);
    }

    @PatchMapping("/{id}/atualizar-assinatura")
    public Cliente atualizarAssinatura(
            @PathVariable UUID id,
            @RequestParam String tipoAssinatura,
            @RequestParam String metodoPagamento) {

        TipoAssinatura assinatura = TipoAssinatura.valueOf(tipoAssinatura.toUpperCase());
        return clienteFacade.atualizarAssinatura(id, assinatura, metodoPagamento);
    }

    @DeleteMapping("/{id}")
    public void deletarCliente(@PathVariable UUID id) {
        clienteFacade.deletarCliente(id);
}

    @PostMapping("/{id}/processar-pagamento")
    public String processarPagamento(@PathVariable UUID id) {
        return clienteFacade.processarPagamento(id);
    }

    @GetMapping("/{id}/verificar-assinatura")
    public boolean verificarAssinatura(@PathVariable UUID id) {
        return clienteFacade.verificarAssinatura(id);
    }
}