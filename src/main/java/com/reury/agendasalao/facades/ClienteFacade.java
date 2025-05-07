package com.reury.agendasalao.facades;

import com.reury.agendasalao.models.Cliente;
import com.reury.agendasalao.models.Endereco;
import com.reury.agendasalao.models.TipoAssinatura;
import com.reury.agendasalao.repositories.ClienteRepository;
import com.reury.agendasalao.services.AssinaturaService;
import com.reury.agendasalao.services.ViaCepService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import com.reury.agendasalao.exceptions.CepInvalidoException;

import java.util.List;
import java.util.UUID;

@Component
public class ClienteFacade {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private ViaCepService viaCepService;

    @Autowired
    private AssinaturaService assinaturaService;
    @RestControllerAdvice
    public class GlobalExceptionHandler {

    @ExceptionHandler(CepInvalidoException.class)
    public ResponseEntity<String> handleCepInvalidoException(CepInvalidoException ex) {
        return ResponseEntity.badRequest().body(ex.getMessage());
    }
}

    public List<Cliente> listarClientes() {
        return clienteRepository.findAll();
    }

    public Cliente cadastrarCliente(Cliente cliente) {
        Endereco enderecoCompleto = viaCepService.consultarCep(cliente.getEndereco().getCep());
        if (enderecoCompleto == null || enderecoCompleto.getCep() == null) {
            throw new CepInvalidoException("Nao foi possivel cadastrar pois  o cep : " + cliente.getEndereco().getCep() + " é inválido ou não foi encontrado.");
        }
        cliente.setEndereco(enderecoCompleto);
        return clienteRepository.save(cliente);
    }

    public Cliente atualizarAssinatura(UUID id, TipoAssinatura tipoAssinatura, String metodoPagamento) {
        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));

        cliente.setTipoAssinatura(tipoAssinatura);
        cliente.setMetodoPagamentoPreferido(metodoPagamento);
        return clienteRepository.save(cliente);
    }

    public void deletarCliente(UUID id) {
        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));
        clienteRepository.delete(cliente);
    }

    public String processarPagamento(UUID id) {
        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));

        assinaturaService.processarPagamento(cliente);
        clienteRepository.save(cliente);

        return "Pagamento processado e assinatura ativada para o cliente: " + cliente.getNome();
    }

    public boolean verificarAssinatura(UUID id) {
        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));

        return assinaturaService.verificarAssinaturaAtiva(cliente);
    }
}

