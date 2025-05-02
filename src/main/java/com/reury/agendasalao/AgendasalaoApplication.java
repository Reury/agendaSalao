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
            new Endereco("Rua das Flores", "123", "Centro", "São Paulo", "SP", "12345-678")
        ));

        clienteRepository.save(new Cliente(
            "Maria Oliveira",
            "maria.oliveira@email.com",
            "987654321",
            new Endereco("Avenida Brasil", "456", "Jardins", "Rio de Janeiro", "RJ", "23456-789")
        ));

        clienteRepository.save(new Cliente(
            "Carlos Santos",
            "carlos.santos@email.com",
            "555555555",
            new Endereco("Rua das Palmeiras", "789", "Vila Nova", "Belo Horizonte", "MG", "34567-890")
        ));

        System.out.println("Clientes de exemplo cadastrados!");
    }
}