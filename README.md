# moviesbattle

## Descrição
Aplicação ao estilo card game, onde serão informados dois 
filmes e o jogador deve acertar aquele que possui melhor avaliação no IMDB. 

## Próxima features
* Melhoria dos testes de integração/unitários/cobertura
* Paginação da listagem do recurso /ranking
* Mecanismo de logout/black-list JWT
* Criptografia das propriedades sensíveis do config-serve com chave JKS
* Adição do padrão Data, Meta e Links no response (Padrão Restfull)
* Melhoria dos handlers para response dos cenários de exceções
* Documentação da API

### Tecnologia

Tecnologias utilizadas

* [java] - Versão 11
* [spring-boot] - Versão 2.6.3
* [spring-security] - config-server https://github.com/TaylorOliveira/config-server
* [spring-data-jpa] - Versão 2.6.3
* [spring-cloud] - Versão 3.1.0
* [jUnit] - Version 5
* [devtools] - Version 2.2
* [lombok] - Version 2.2
* [h2] - Version 2.2
* [jjwt] - Version 0.9.1
* [jsoup] - Version 1.14.3
* [rest-assured] - Version 4.5.0

### Documentação

Para autenticação e autorização foram utilizados Spring Security e JWT. 
O Token é gerado no login e precisa ser passado em todos os outros recursos.

Dois usuários já estão configurados na aplicação:
- username: user_test01 password: 123456789
- username: user_test02 password: 123456789

Caso querira, também é possível realizar o cadastro de um novo jogador.

1. Recurso para cadastrar novo jogador
```
curl --location --request POST 'http://localhost:8080/auth/signup' \
--header 'Content-Type: application/json' \
--data-raw '{
    "username": "tr",
    "email": "tr@letscode.com",
    "password": "123456789",
    "role": ["mod", "user"]
}'
```
2. Recurso para realizar login
```
curl --location --request POST 'http://localhost:8080/auth/signin' \
--header 'Content-Type: application/json' \
--data-raw '{
    "username": "user_test01",
    "password": "123456789"
}'
```
3. Recruso para inicia o game
```
curl --location --request POST 'http://localhost:8080/game/initialize' \
--header 'Authorization: Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ0YXlsb3IiLCJpYXQiOjE2NDQzNjQ2MjcsImV4cCI6MTY0NDQ1MTAyN30.hAzV8K8drVIE6QIoyVEtfBLL50bGQbo8IkVTl0WCYhqvHD5SAQ6RlmDWjQL43lkrGvcPoqtaZpy_gyifoEkM1Q'
```
4. Recurso para validar a escolha do jogador
```curl --location --request PUT 'http://localhost:8080/game/quiz/validate/{roundId}?choice=LEFT' \
--header 'Authorization: Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ0YXlsb3IiLCJpYXQiOjE2NDQzNjQzNDYsImV4cCI6MTY0NDQ1MDc0Nn0.3qAXysH9rTjQsGjJaUagmCyxX5uCfBOSbJFzB-9piKCyQJxusGoQ8fCTf9TCO5GkzZfU673S8MPXjMBXfqKA_A'
```
5. Recruso para o próximo round/quiz
```
curl --location --request POST 'http://localhost:8080/game/next-quiz' \
--header 'Authorization: Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ0YXlsb3IiLCJpYXQiOjE2NDQzNjQ2MjcsImV4cCI6MTY0NDQ1MTAyN30.hAzV8K8drVIE6QIoyVEtfBLL50bGQbo8IkVTl0WCYhqvHD5SAQ6RlmDWjQL43lkrGvcPoqtaZpy_gyifoEkM1Q'
```
6. Recurso para finalizar um game
```
curl --location -g --request PUT 'http://localhost:8080/game/finalize/{gameId}' \
--header 'Authorization: Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ0YXlsb3IiLCJpYXQiOjE2NDQzNDkyNjksImV4cCI6MTY0NDQzNTY2OX0.QqSsdqNUWO16xlMiXjollwGdQe8DaE-Cv-x0e9ejch8KDXpNrO83RbP9jUjZAVLLq9zoxlgOv7qTbCS3tqH0FQ'
```
