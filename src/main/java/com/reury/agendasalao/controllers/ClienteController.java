package com.reury.agendasalao.controllers;

import com.reury.agendasalao.models.Cliente;
import com.reury.agendasalao.models.Endereco;
import com.reury.agendasalao.repositories.ClienteRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    

    @GetMapping
    public List<Cliente> listarClientes() {
        return clienteRepository.findAll();
    }

    @PostMapping
    public Cliente cadastrarCliente(@RequestBody Cliente cliente) {
        // Endereco enderecoCompleto = viaCepService.consultarCep(cliente.getEndereco().getCep());
        // cliente.setEndereco(enderecoCompleto);
        return clienteRepository.save(cliente);
    }

    @GetMapping("/{id}")
    public Cliente buscarCliente(@PathVariable UUID id) {
        return clienteRepository.findById(id).orElseThrow(() -> new RuntimeException("Cliente não encontrado"));
    }

    @PutMapping("/{id}")
    public Cliente atualizarCliente(@PathVariable UUID id, @RequestBody Cliente clienteAtualizado) {
        Cliente cliente = clienteRepository.findById(id).orElseThrow(() -> new RuntimeException("Cliente não encontrado"));
        cliente.setNome(clienteAtualizado.getNome());
        cliente.setEmail(clienteAtualizado.getEmail());
        cliente.setTelefone(clienteAtualizado.getTelefone());
        return clienteRepository.save(cliente);
    }

    @DeleteMapping("/{id}")
    public void deletarCliente(@PathVariable UUID id) {
        clienteRepository.deleteById(id);
    }
}