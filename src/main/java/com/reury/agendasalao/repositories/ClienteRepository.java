
package com.reury.agendasalao.repositories;

import com.reury.agendasalao.models.Cliente;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, UUID> {
}