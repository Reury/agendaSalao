package com.reury.agendasalao.models;

import java.time.LocalDate;
import java.util.UUID;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Cliente {

    @Id
    @GeneratedValue(generator = "UUID", strategy = GenerationType.AUTO)
    private UUID id;
    private String nome;
    private String email;
    private String telefone;

    @Embedded
    private Endereco endereco;

    // Campos relacionados à assinatura
    @Enumerated(EnumType.STRING)
    private TipoAssinatura tipoAssinatura; // Ex.: "mensal", "anual"

    private boolean assinaturaAtiva;
    private LocalDate dataInicioAssinatura;
    private LocalDate dataFimAssinatura;
    private String metodoPagamentoPreferido; // Ex.: "cartao", "pix"

    public Cliente() {
    }

    public Cliente(String nome, String email, String telefone, Endereco endereco) {
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
        this.endereco = endereco;
        this.assinaturaAtiva = false; // Assinatura inativa por padrão
    }

    // Getters e Setters
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public TipoAssinatura getTipoAssinatura() {
        return tipoAssinatura;
    }

    public void setTipoAssinatura(TipoAssinatura tipoAssinatura) {
        this.tipoAssinatura = tipoAssinatura;
    }

    public boolean isAssinaturaAtiva() {
        return assinaturaAtiva;
    }

    public void setAssinaturaAtiva(boolean assinaturaAtiva) {
        this.assinaturaAtiva = assinaturaAtiva;
    }

    public LocalDate getDataInicioAssinatura() {
        return dataInicioAssinatura;
    }

    public void setDataInicioAssinatura(LocalDate dataInicioAssinatura) {
        this.dataInicioAssinatura = dataInicioAssinatura;
    }

    public LocalDate getDataFimAssinatura() {
        return dataFimAssinatura;
    }

    public void setDataFimAssinatura(LocalDate dataFimAssinatura) {
        this.dataFimAssinatura = dataFimAssinatura;
    }

    public String getMetodoPagamentoPreferido() {
        return metodoPagamentoPreferido;
    }

    public void setMetodoPagamentoPreferido(String metodoPagamentoPreferido) {
        this.metodoPagamentoPreferido = metodoPagamentoPreferido;
    }
}