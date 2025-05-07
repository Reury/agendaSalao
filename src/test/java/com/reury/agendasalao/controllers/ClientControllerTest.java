package com.reury.agendasalao.controllers;

import com.reury.agendasalao.models.Cliente;
import com.reury.agendasalao.models.Endereco;
import com.reury.agendasalao.models.TipoAssinatura;
import com.reury.agendasalao.repositories.ClienteRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class ClienteControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ClienteRepository clienteRepository;

    @BeforeEach
    void setUp() {
        // Limpa o banco de dados antes de cada teste
        clienteRepository.deleteAll();
    }

    @Test
    void testCadastrarCliente() throws Exception {
        String clienteJson = """
                {
                    "nome": "João Silva",
                    "email": "joao.silva@email.com",
                    "telefone": "123456789",
                    "endereco": {
                        "cep": "01001-000"
                    },
                    "tipoAssinatura": "PREMIUM",
                    "metodoPagamentoPreferido": "cartao"
                }
                """;

        mockMvc.perform(post("/clientes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(clienteJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nome").value("João Silva"))
                .andExpect(jsonPath("$.tipoAssinatura").value("PREMIUM"));
    }

    @Test
    void testListarClientes() throws Exception {
        // Adiciona clientes ao banco de dados
        clienteRepository.save(new Cliente("João Silva", "joao@email.com", "123456789", new Endereco("01001-000")));
        clienteRepository.save(new Cliente("Maria Oliveira", "maria@email.com", "987654321", new Endereco("20040-010")));

        // Faz a requisição GET e verifica o status e o conteúdo da resposta
        mockMvc.perform(get("/clientes")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2)) // Verifica se há 2 clientes na resposta
                .andExpect(jsonPath("$[0].nome").value("João Silva"))
                .andExpect(jsonPath("$[1].nome").value("Maria Oliveira"));
    }

    @Test
    void testProcessarPagamento() throws Exception {
        // Salva o cliente no banco de dados com o método de pagamento definido
        Cliente cliente = new Cliente("João Silva", "joao@email.com", "123456789", new Endereco("01001-000"));
        cliente.setMetodoPagamentoPreferido("cartao");
        clienteRepository.save(cliente);

        // Faz a requisição POST para processar o pagamento
        mockMvc.perform(post("/clientes/" + cliente.getId() + "/processar-pagamento")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string("Pagamento processado e assinatura ativada para o cliente: João Silva"));
    }

    @Test
    void testVerificarAssinatura() throws Exception {
        // Salva o cliente no banco de dados com a assinatura ativa
        Cliente cliente = new Cliente("João Silva", "joao@email.com", "123456789", new Endereco("01001-000"));
        cliente.setAssinaturaAtiva(true);
        cliente.setDataInicioAssinatura(LocalDate.now());
        cliente.setDataFimAssinatura(LocalDate.now().plusDays(30)); // Assinatura válida por 30 dias
        clienteRepository.save(cliente);

        // Faz a requisição GET para verificar a assinatura
        mockMvc.perform(get("/clientes/" + cliente.getId() + "/verificar-assinatura")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string("true"));
    }

    @Test
    void testDeletarCliente() throws Exception {
        // Salva o cliente no banco de dados
        Cliente cliente = clienteRepository.save(new Cliente("João Silva", "joao@email.com", "123456789", new Endereco("01001-000")));

        // Faz a requisição DELETE para remover o cliente
        mockMvc.perform(delete("/clientes/" + cliente.getId())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        // Verifica se o cliente foi removido
        assertFalse(clienteRepository.findById(cliente.getId()).isPresent());
    }
}