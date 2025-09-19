# 🐾 API de Cadastro de Animais - Projeto `apiInitial`

Este projeto é uma API REST desenvolvida em **Java 17** com **Spring Boot**, utilizando **arquitetura hexagonal** para promover separação de responsabilidades e flexibilidade. 
A persistência é feita com **JPA** e **banco de dados H2**, acessado via **DBeaver**. O projeto também utiliza **Lombok** para reduzir boilerplate e **JUnit 5** para testes unitários. 
A comunicação é feita via **JSON**, e os testes de API são realizados com o **Postman**.

---
## 🧰 Tecnologias Utilizadas

- Java 17  
- Spring Boot  
- Spring Data JPA  
- Lombok  
- JUnit 5  
- Banco de dados H2 (acessado via DBeaver)  
- Postman  
- JSON  
- Maven  
- IntelliJ IDEA  

---
## 📦 Estrutura do Projeto

```
apiInitial/
├── .idea/
├── .mvn/
├── src/
│   ├── main/
│   │   └── java/
│   │       └── com.projetoInitial.apiInitial/
│   │           ├── config/         # Configurações (ex: WebConfig)
│   │           ├── controller/     # Controladores REST (ex: AnimalController)
│   │           ├── models/         # Entidades (ex: Animal)
│   │           ├── repositories/   # Interfaces JPA (ex: AnimailRepository)
│   │           ├── services/       # Regras de negócio (ex: AnimailService)
│   │           └── ApiInitialApplication.java
│   └── test/
│       └── java/
│           └── com.projetoInitial.apiInitial/
│               └── ApiInitialApplicationTests.java
```

---
## 🚀 Como Executar

1. **Pré-requisitos**:
   - Java 17
   - Maven
   - IntelliJ IDEA
   - DBeaver

2. **Clonar o repositório**:
   ```bash
   git clone https://github.com/seu-usuario/apiInitial.git
   cd apiInitial
   ```

3. **Executar a aplicação**:
   ```bash
   mvn spring-boot:run
   ```

4. **Acessar a API**:
   - Base URL: `http://localhost:8080/api/animais`
   - Utilize o Postman para testar os endpoints.

---
## 📬 Exemplos de Requisições

### ➕ Criar Animal

```http
POST /api/animais
Content-Type: application/json

{
  "nome": "Rex",
  "especie": "Cachorro",
  "idade": 5
}
```
### 🔍 Buscar Animal por ID

```http
GET /api/animais/1
```

---
## ✅ Testes

Os testes unitários estão em `src/test/java/com.projetoInitial.apiInitial`. Para executá-los:

```bash
mvn test
```

---
## 🗃️ Banco de Dados

- O banco H2 é configurado no `application.properties` e pode ser acessado via DBeaver com a URL JDBC.

---
## 📄 Licença

Este projeto está sob a licença MIT. Veja o arquivo `LICENSE` para mais detalhes.
