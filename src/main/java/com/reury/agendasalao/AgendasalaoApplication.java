package com.reury.agendasalao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.reury.agendasalao.models.Cliente;
import com.reury.agendasalao.models.Endereco;
import com.reury.agendasalao.repositories.ClienteRepository;

@SpringBootApplication
public class AgendasalaoApplication implements CommandLineRunner {

    @Autowired
    private ClienteRepository clienteRepository;

    public static void main(String[] args) {
        SpringApplication.run(AgendasalaoApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        // Adicionando clientes de exemplo com endereço
        clienteRepository.save(new Cliente(
            "João Silva",
            "joao.silva@email.com",
            "123456789",
            new Endereco("01001-000", "Praça da Sé", "", "Sé", "São Paulo", "SP", "123")
        ));
    
        clienteRepository.save(new Cliente(
            "Maria Oliveira",
            "maria.oliveira@email.com",
            "987654321",
            new Endereco("20040-010", "Avenida Rio Branco", "Sala 1", "Centro", "Rio de Janeiro", "RJ", "456")
        ));
    
        clienteRepository.save(new Cliente(
            "Carlos Santos",
            "carlos.santos@email.com",
            "555555555",
            new Endereco("30130-010", "Rua dos Guajajaras", "Apto 101", "Centro", "Belo Horizonte", "MG", "789")
        ));

        System.out.println("Clientes de exemplo cadastrados!");
    }
}