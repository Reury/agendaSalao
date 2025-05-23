package com.reury.agendasalao.services;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.reury.agendasalao.models.Endereco;

@Service
public class ViaCepService {

    private static final String VIACEP_URL = "https://viacep.com.br/ws/{cep}/json/";

    public Endereco consultarCep(String cep) {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(VIACEP_URL, Endereco.class, cep);
    }
}