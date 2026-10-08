# Connectivity Forecast API

Reimplementação Java/Spring Boot da [API de referência](https://github.com/LiniiS/connectivity-forecast-api), usada como artefato educacional de APS II.

## Executar

Requisitos: Java 17 e Maven 3.9+.

```bash
mvn test
mvn spring-boot:run
```

Swagger UI: `http://localhost:8080/swagger-ui.html`  
OpenAPI: `http://localhost:8080/v3/api-docs`

## Limitações e Escopo do Projeto

**Atenção:** Este projeto possui fins estritamente educacionais para a disciplina de APS II. Os consumidores desta API não devem interpretar os endpoints como funcionalidades de produção plenas, devido às seguintes restrições:

* **Dados Estáticos (Mock):** A API não se conecta a bancos de dados reais nem consulta a rede RIPE Atlas ao vivo. Todas as previsões e métricas fornecidas são baseadas em dados estáticos (fixtures) pré-carregados a partir do arquivo `predictions.csv`.
* **Modelos Preditivos Simulados:** Os algoritmos de Machine Learning listados (como Random Forest ou Decision Tree) não realizam treinamentos ou inferências reais. Eles existem apenas como metadados no catálogo `models.json`.
* **Regras Experimentais:** A classificação da qualidade da internet (GOOD, MODERATE, UNSTABLE) e as recomendações de uso de rede operam sobre regras de negócio fixas no código, sem validação científica rigorosa.
## Estrutura

Separação em controllers, services, repositories, modelos de domínio/DTOs e configuração. API versionada em `/api/v1`, com recursos de health, modelos, localizações, previsões e atividades.

## Documentação

README e OpenAPI são pontos de partida. Completar em exercício: descrição dos endpoints, parâmetros e validações, exemplos de requisição/resposta, códigos de erro e origem dos campos. Referência à licença MIT da API de origem preservada neste projeto.
