# HidroSafe API Containers ⛈️

## 👥 Integrantes

| Nome               | RM     |
|--------------------|--------|
| Guilherme Guimarães| [RM557074] Turma:  2TDSA
| Matheus Oliveira de Luna | [RM555547] Turma: 2TDSA
| Nicollas Guedes Pontes | [RM556850] Turma:  2TDSB

## 🎥 Links para os vídeos:
* Demonstração da solução completa: [Link do vídeo](https://www.youtube.com/)
* Demonstração da conteinerização (DevOps): https://youtu.be/LnIVyvO_IWc
* Pitch de apresentação: https://youtu.be/EpZtc4Rvggw

## 🔎 Visão Geral

O projeto **HidroSafe** foi concebido como uma resposta tecnológica ao desafio crescente das enchentes em áreas urbanas e periféricas, especialmente em comunidades ribeirinhas e regiões de encosta. A proposta visa oferecer um sistema de monitoramento de nível da água baseado em Internet das Coisas (IoT), capaz de emitir alertas em tempo real, contribuindo diretamente para a segurança da população e para a prevenção de desastres.
O público-alvo da solução são moradores de regiões de risco e agentes da defesa civil, que frequentemente enfrentam situações de alagamento sem aviso prévio. A solução também se destina a gestores públicos que precisam monitorar dados de risco em diferentes pontos da cidade
A aplicação conta com uma API Java Spring Boot conectada a um banco de dados PostgreSQL, com autenticação JWT.

## 🎯 Objetivo

A proposta do **HidroSafe** é implantar um sistema com sensores de ultrassom chamado HC-SR04  que calcular a distância da água até uma possível enchente (simulados no Wokwi), conectados via protocolo MQTT ao Node-RED, que é responsável por interpretar os dados e alimentar um dashboard com os níveis em tempo real.
O sistema funcionará da seguinte forma:
* Cada ponto de monitoramento terá um sensor de nível (HC-SR04) que envia leituras frequentes.
* Caso o nível da água atinja um valor crítico, o sistema aciona visualizações e alertas em tempo real no painel da Defesa Civil.
* A solução também envia esses dados para uma API centralizada (desenvolvida em .NET ou Java), que armazena as leituras e alerta aplicativos móveis com notificações para os moradores.
* O painel (dashboard) também é acessível por navegador local (localhost:1880/ui) e permite o acompanhamento contínuo de todos os sensores instalados.


## 🚀 Tecnologias Utilizadas

- Java 17
- Spring Boot
- Spring Security + JWT
- PostgreSQL
- Docker + Docker Compose
- Swagger (OpenAPI)

## 🏠 Arquitetura

O sistema é composto por:

- **API Java:** Gerenciamento de usuários, autenticação, endereços e denúncias.
- **PostgreSQL:** Banco de dados relacional que armazena as entidades.
- **Docker:** Ambiente conteinerizado para execução local da aplicação.

## 🚧 Execução do Projeto

### 1. Clone o repositório

```bash
git clone https://github.com/mlunahodov/hidrosafe-api-containers.git
cd hidrosafe-api-containers
```

### 2. Execute os containers

```bash
docker-compose up --build -d
```

A API estará acessível em: [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)

### 3. Encerrar containers

```bash
docker-compose down -v
```

## 📋 Exemplos de Requisições JSON

### Cadastrar Usuário

POST /usuarios

```json
{
  "nomeCompleto": "Helena Silva",
  "email": "helena@gmail.com",
  "password": "123456",
  "cargo": "ADMIN",
  "endereco": {
    "logradouro": "Rua Z",
    "bairro": "Bairro W",
    "cidade": "São Paulo",
    "estado": "São Paulo",
    "cep": "01101-020"
  }
}
```

Resposta (201 Created):

```json
{
  "id": 2,
  "nomeCompleto": "Helena Silva",
  "email": "helena@gmail.com",
  "cargo": "ADMIN",
  "endereco": {
    "id": 2,
    "logradouro": "Rua Z",
    "bairro": "Bairro W",
    "cidade": "São Paulo",
    "estado": "São Paulo",
    "cep": "01101-020"
  }
}
```

### Login de usuário

POST /login

```json
{
  "email": "helena@gmail.com",
  "password": "123456"
}
```

Resposta (200 OK):

```json
{
  "token": "<JWT Token>",
  "id": 2,
  "email": "helena@gmail.com",
  "cargo": "ADMIN"
}
```

### Criar Denúncia

POST /registros

Header: `Authorization: Bearer <JWT Token>`

```json
{
  "assunto": "Inundação em casa",
  "descricao": "Nível da água subiu e invadiu minha residência.",
  "logradouro": "Rua A, 123"
}
```

Resposta (201 Created):

```json
{
  "id": 1,
  "assunto": "Inundação em casa",
  "descricao": "Nível da água subiu e invadiu minha residência.",
  "logradouro": "Rua A, 123",
  "usuario": {
    "id": 2,
    "nomeCompleto": "Helena Silva"
  }
}
```

### Buscar Denúncia por ID

GET /registros/{id}

Resposta:

```json
{
  "id": 1,
  "assunto": "Inundação em casa",
  "descricao": "Nível da água subiu e invadiu minha residência.",
  "logradouro": "Rua A, 123",
  "usuario": {
    "id": 2,
    "nomeCompleto": "Helena Silva"
  }
}
```

### Atualizar Denúncia

PUT /registros/{id}

```json
{
  "assunto": "Rua completamente alagada",
  "descricao": "A rua inteira está intransitável.",
  "logradouro": "Rua B, 456"
}
```

Resposta:

```json
{
  "id": 1,
  "assunto": "Rua completamente alagada",
  "descricao": "A rua inteira está intransitável.",
  "logradouro": "Rua B, 456"
}
```

### Deletar Denúncia

DELETE /registros/{id}

Resposta:
204 No Content

## 📈 Consultar via terminal PostgreSQL (opcional)

```bash
docker exec -it hidrosafe-db psql -U admin -d hidrosafe
```

Dentro do terminal:

```sql
SELECT * FROM usuario;
SELECT * FROM denuncia;
SELECT * FROM registro;
```

---

> ✅ Projeto desenvolvido para fins acadêmicos com foco em resiliência urbana e suporte digital em cenários de desastre.