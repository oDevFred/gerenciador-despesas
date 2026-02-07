# Gerenciador de Despesas

Aplicacao web para gerenciamento e acompanhamento de despesas pessoais, com API REST e interface web interativa.

## Tecnologias

- **Java 25**
- **Spring Boot 4.0.2** (Web MVC, Data JPA, Validation, Thymeleaf)
- **H2 Database** (banco em memoria)
- **Bootstrap 5.3.0** + JavaScript vanilla
- **Maven**

## Funcionalidades

- Cadastro, edicao e exclusao de despesas
- Categorias: Alimentacao, Transporte, Lazer, Saude
- Resumo com total gasto e quantidade de despesas
- Filtro por descricao e por categoria
- Ordenacao das colunas da tabela
- Validacao de campos no servidor (descricao, valor, data, categoria)
- Console H2 para inspecao do banco de dados

## Estrutura do Projeto

```
src/main/java/io/github/com/odevfred/gerenciador_despesas/
├── controller/
│   ├── ExpenseController.java   # API REST
│   ├── HomeController.java      # Paginas Thymeleaf
│   └── PageController.java      # Roteamento de views
├── model/
│   └── Expense.java             # Entidade JPA
├── repository/
│   └── ExpenseRepository.java   # Acesso a dados
├── dto/
│   └── ExpenseSummary.java      # DTO de resumo
└── GerenciadorDeDespesasApplication.java
```

## Endpoints da API

| Metodo | Rota | Descricao |
|--------|------|-----------|
| GET | `/api/expense` | Listar todas as despesas |
| GET | `/api/expense/{id}` | Buscar despesa por ID |
| GET | `/api/expense/summary` | Resumo (total e quantidade) |
| POST | `/api/expense` | Criar nova despesa |
| PUT | `/api/expense/{id}` | Atualizar despesa |
| DELETE | `/api/expense/{id}` | Remover despesa |

## Paginas Web

| Rota | Descricao |
|------|-----------|
| `/home` | Pagina inicial |
| `/view-expenses` | Gerenciamento de despesas |
| `/about` | Sobre o projeto |

## Como Executar

1. Clone o repositorio:
   ```bash
   git clone https://github.com/odevfred/gerenciador-despesas.git
   cd gerenciador-despesas
   ```

2. Execute com o Maven Wrapper:
   ```bash
   ./mvnw spring-boot:run
   ```

3. Acesse no navegador:
   - Aplicacao: `http://localhost:8080/home`
   - Console H2: `http://localhost:8080/h2-console`
     - JDBC URL: `jdbc:h2:mem:despesasdb`
     - Usuario: `sa`
     - Senha: *(vazio)*

## Modelo de Dados

**Expense**

| Campo | Tipo | Validacao |
|-------|------|-----------|
| id | Long | Gerado automaticamente |
| description | String | Obrigatorio, 3-100 caracteres |
| amount | BigDecimal | Positivo, minimo 0.01 |
| date | LocalDate | Data presente ou passada |
| category | String | ALIMENTACAO, TRANSPORTE, LAZER ou SAUDE |

## Licenca

Este projeto esta licenciado sob a [Licenca MIT](LICENSE).
