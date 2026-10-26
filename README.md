# Controle Financeiro API

API REST para registrar receitas e despesas pessoais, consultar transações e calcular um resumo financeiro.

## Tecnologias

- Java 21
- Spring Boot
- Spring Web (API REST)
- Spring Data JPA / Hibernate
- Bean Validation
- H2 Database (banco em memória para desenvolvimento)
- JUnit 5 e Mockito
- Maven

## Requisitos

- JDK 21 ou superior
- Maven 3.9+ (ou usar o Maven Wrapper, caso seja adicionado)

## Executar

```bash
mvn spring-boot:run
```

A API ficará disponível em `http://localhost:8080`.

## Endpoints

| Método | Rota | Descrição |
|---|---|---|
| `POST` | `/api/transacoes` | Criar transação |
| `GET` | `/api/transacoes` | Listar todas as transações |
| `GET` | `/api/transacoes/{id}` | Buscar por ID |
| `PUT` | `/api/transacoes/{id}` | Atualizar transação |
| `DELETE` | `/api/transacoes/{id}` | Excluir transação |
| `GET` | `/api/transacoes/resumo` | Consultar total de receitas, despesas e saldo |
| `GET` | `/api/transacoes?tipo=DESPESA` | Filtrar por tipo |
| `GET` | `/api/transacoes?categoria=Moradia` | Filtrar por categoria |

## Exemplo de criação

Requisição `POST /api/transacoes`:

```json
{
  "descricao": "Salário",
  "valor": 3000.00,
  "tipo": "RECEITA",
  "categoria": "Trabalho",
  "data": "2026-10-05"
}
```

Exemplo de resposta do resumo em `GET /api/transacoes/resumo`:

```json
{
  "totalReceitas": 3000.00,
  "totalDespesas": 1150.00,
  "saldo": 1850.00
}
```

## Testes

```bash
mvn test
```

O projeto inclui um teste unitário para validar o cálculo do resumo financeiro.

## Decisões técnicas

- `BigDecimal` é utilizado para valores monetários, evitando os problemas de precisão típicos de `float` e `double`.
- A aplicação separa controller, service, repository, model e DTO para manter responsabilidades distintas.
- O DTO de entrada usa Bean Validation para rejeitar campos vazios e valores menores que R$ 0,01.
- O H2 em memória reduz a configuração necessária para executar o projeto localmente. Os dados são perdidos quando a aplicação é encerrada.

## Próximas melhorias

- Persistência com PostgreSQL ou MySQL.
- Paginação e ordenação.
- Filtros por intervalo de datas.
- Tratamento padronizado de erros de validação.
- Autenticação e autorização.
- Testes de integração dos endpoints.

## Escopo atual

Projeto de portfólio educacional. Não inclui autenticação nem deve ser usado para armazenar dados financeiros reais.
