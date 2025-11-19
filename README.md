# Teste Jaya - Banco Digital (Microserviços simulados)

Objetivo
--------
Este projeto é um pequeno sistema de banco digital pensado para avaliar candidatos backend Java/Spring. Ele usa Spring Boot com H2 (banco embarcado). O repositório tem endpoints para abrir conta, depositar, retirar (não implementado) e transferir.

O que foi implementado
----------------------
- Abertura de conta (POST /accounts) — criado e testado.
- Depósito (POST /accounts/{id}/deposit) — criado e testado.
- Transferência (POST /transfers) — parcialmente implementada (debita e credita, sem controles concorrência/locks avançados).
- Retirada (POST /accounts/{id}/withdraw) — propositalmente não implementada. Esta é a tarefa do candidato.

Tarefas do candidato (o que você deve implementar)
-------------------------------------------------
1. Implementar o método withdraw em `org.example.bank.account.service.AccountService`.
   - Reduzir o saldo da conta.
   - Registrar uma `Transaction` do tipo `WITHDRAW` quando a operação ocorrer.
   - Impedir saque quando o saldo for insuficiente (lançar `org.example.bank.common.Exceptions.InsufficientFundsException`).
   - Marcar o método com `@Transactional` para garantir atomicidade.

2. Fazer os testes passarem (os testes estão em `src/test/java`). Em particular, `WithdrawServiceTest` falha propositalmente e deve passar após a implementação.

3. (Opcional) Melhorar a implementação de transferência para lidar com concorrência (locks, checagem de saldo antes de debitar, contabilidade em caso de falha).

Como rodar
---------
No macOS (zsh), execute:

```bash
./gradlew clean test
./gradlew bootRun
```

APIs
----
- POST /accounts
  - body: { "ownerName": "Name", "initialDeposit": 100 }
- POST /accounts/{id}/deposit
  - body: { "amount": 50 }
- POST /accounts/{id}/withdraw
  - body: { "amount": 40 }
- POST /transfers
  - body: { "fromAccountId": 1, "toAccountId": 2, "amount": 10 }

Swagger / OpenAPI
-----------------
Após iniciar a aplicação (`./gradlew bootRun`):
- Swagger UI: http://localhost:8080/swagger-ui/index.html
- Documentação OpenAPI JSON: http://localhost:8080/v3/api-docs

Você pode usar o Swagger UI para explorar e testar os endpoints sem precisar de uma ferramenta externa.

Critérios de avaliação
----------------------
- Código limpo e organizado.
- Testes passando.
- Uso correto de transações e tratamento de erros.
- Considerações sobre concorrência (pontos extras).

Submissão
---------
Envie um PR ou um zip do projeto com as mudanças. Inclua no README as decisões tomadas e pontos a serem melhorados.
