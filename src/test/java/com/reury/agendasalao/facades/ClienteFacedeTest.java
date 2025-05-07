package com.reury.agendasalao.facades;

import com.reury.agendasalao.models.Cliente;
import com.reury.agendasalao.models.Endereco;
import com.reury.agendasalao.models.TipoAssinatura;
import com.reury.agendasalao.repositories.ClienteRepository;
import com.reury.agendasalao.services.AssinaturaService;
import com.reury.agendasalao.services.ViaCepService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ClienteFacadeTest {

    @Mock
    private ClienteRepository clienteRepository;

    @Mock
    private ViaCepService viaCepService;

    @Mock
    private AssinaturaService assinaturaService;

    @InjectMocks
    private ClienteFacade clienteFacade;

    public ClienteFacadeTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAtualizarAssinatura() {
        UUID clienteId = UUID.randomUUID();
        Cliente cliente = new Cliente("João Silva", "joao@email.com", "123456789", new Endereco());
        when(clienteRepository.findById(clienteId)).thenReturn(Optional.of(cliente));
    
        TipoAssinatura tipoAssinatura = TipoAssinatura.PREMIUM;
        String metodoPagamento = "cartao";
    
        // Configura o mock para retornar o cliente atualizado ao salvar
        when(clienteRepository.save(cliente)).thenReturn(cliente);
    
        Cliente atualizado = clienteFacade.atualizarAssinatura(clienteId, tipoAssinatura, metodoPagamento);
    
        // Verifica se os campos foram atualizados corretamente
        assertNotNull(atualizado);
        assertEquals(tipoAssinatura, atualizado.getTipoAssinatura());
        assertEquals(metodoPagamento, atualizado.getMetodoPagamentoPreferido());
    
        // Verifica se o método save foi chamado
        verify(clienteRepository, times(1)).save(cliente);
    }
    @Test
    void testDeletarCliente() {
        UUID clienteId = UUID.randomUUID();
        Cliente cliente = new Cliente();
        cliente.setId(clienteId);
    
        when(clienteRepository.findById(clienteId)).thenReturn(Optional.of(cliente));
    
        clienteFacade.deletarCliente(clienteId);
    
        verify(clienteRepository, times(1)).delete(cliente);
    }
}