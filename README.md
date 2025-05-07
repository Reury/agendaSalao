# Agenda Salão

Este é um projeto de API REST para gerenciar o cadastro de clientes e assinaturas de um salão de beleza. A aplicação foi desenvolvida utilizando **Spring Boot**, com foco em boas práticas de desenvolvimento, padrões de projeto e escalabilidade.

---

## 🚀 Funcionalidades

- **Listar Clientes**: Retorna todos os clientes cadastrados.
- **Cadastrar Cliente**: Adiciona um novo cliente ao sistema, preenchendo automaticamente o endereço com base no CEP.
- **Atualizar Assinatura**: Permite alterar o tipo de assinatura e o método de pagamento do cliente.
- **Processar Pagamento**: Processa o pagamento de uma assinatura e ativa a assinatura do cliente.
- **Verificar Assinatura**: Verifica se a assinatura de um cliente está ativa.
- **Deletar Cliente**: Remove um cliente do sistema.

---

## 🛠️ Tecnologias Utilizadas

- **Java 17**
- **Spring Boot**
  - Spring Web
  - Spring Data JPA
  - Spring Context
- **H2 Database** (banco de dados em memória)
- **Maven**
- **JUnit 5** (para testes unitários e de integração)
- **MockMvc** (para simular requisições HTTP em testes)

---

## 📐 Padrões de Projeto Utilizados

O projeto foi desenvolvido com foco em boas práticas e padrões de projeto. Abaixo estão os principais padrões aplicados:

### **1. Facade**
- **Descrição**: Centraliza a lógica de negócios relacionada aos clientes.
- **Exemplo no Projeto**:
  - A classe `ClienteFacade` encapsula operações como cadastro de clientes, atualização de assinaturas, processamento de pagamentos e verificação de assinaturas.
  - Isso simplifica o controlador (`ClienteController`), que delega a lógica de negócios ao `ClienteFacade`.

### **2. Strategy**
- **Descrição**: Implementa diferentes métodos de pagamento.
- **Exemplo no Projeto**:
  - A interface `MetodoPagamento` define o contrato para os métodos de pagamento.
  - As classes `PagamentoCartao` e `PagamentoPix` implementam a lógica específica para cada método de pagamento.
  - O `PagamentoService` utiliza o Spring para injetar dinamicamente a estratégia de pagamento apropriada.

### **3. Inversão de Controle (IoC) e Injeção de Dependência (DI)**
- **Descrição**: O Spring gerencia os objetos do projeto, promovendo o desacoplamento e a reutilização de código.
- **Exemplo no Projeto**:
  - As classes `ClienteFacade`, `ViaCepService` e `AssinaturaService` são anotadas com `@Component` ou `@Service` e injetadas automaticamente onde necessário usando `@Autowired`.

### **4. Repository Pattern**
- **Descrição**: Abstrai o acesso ao banco de dados.
- **Exemplo no Projeto**:
  - A interface `ClienteRepository` estende `JpaRepository`, fornecendo métodos prontos para operações CRUD.

### **5. Singleton**
- **Descrição**: Gerencia beans como singletons implicitamente pelo Spring.
- **Exemplo no Projeto**:
  - Classes como `ClienteFacade`, `ViaCepService` e `AssinaturaService` são gerenciadas como singletons pelo contêiner de IoC do Spring.

---

## 📦 Como Executar o Projeto

### Pré-requisitos
- **Java 17** ou superior instalado.
- **Maven** instalado.

### Passos
1. Clone o repositório:
   ```bash
   git clone https://github.com/seu-usuario/agendaSalao.git
   cd agendaSalao
   ```

2. Compile o projeto:
   ```bash
   mvn clean install
   ```

3. Execute a aplicação:
   ```bash
   mvn spring-boot:run
   ```

4. Acesse a API em:
   ```
   http://localhost:8080
   ```

---

## 📖 Exemplos de Uso da API

### **1. Cadastrar Cliente**
**Endpoint**: `POST /clientes`  
**Exemplo de Corpo da Requisição**:
```json
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
```

### **2. Atualizar Assinatura**
**Endpoint**: `PATCH /clientes/{id}/atualizar-assinatura`  
**Exemplo de Requisição**:
```
PATCH http://localhost:8080/clientes/13f6712f-b40b-4a29-8fd1-28f1e16e01e6/atualizar-assinatura?tipoAssinatura=VIP&metodoPagamento=pix
```

### **3. Processar Pagamento**
**Endpoint**: `POST /clientes/{id}/processar-pagamento`  
**Exemplo de Requisição**:
```
POST http://localhost:8080/clientes/13f6712f-b40b-4a29-8fd1-28f1e16e01e6/processar-pagamento
```

---

## 🧪 Testes

O projeto pode ser testado utilizando ferramentas como **Postman** ou **cURL** para interagir com os endpoints da API.

---

## 📜 Licença

Este projeto não está licenciado.

---

## 👨‍💻 Autor

- **Reury Araujo** - [GitHub](https://github.com/Reury)