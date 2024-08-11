<h1 align="center"> Api de viagem </h1>


![GitHub License](https://img.shields.io/github/license/pedro-henrique1/Destino-Viagem?style=flat)
![GitHub repo size](https://img.shields.io/github/repo-size/pedro-henrique1/Destino-Viagem?style=flat)
![GitHub last commit](https://img.shields.io/github/last-commit/pedro-henrique1/Destino-Viagem?style=flat)

<p style="font-size:16px;">Uma api de um site de viagem, onde nela e possível colocar depoimentos, locais para viagem, preço, e muito mais.</p>
<p style="font-size:16px;" >O projeto foi feito para exercitar minhas habilidades com spring boot e jpa.</p>

## Instalação

1. Clone do repositorio

```
$ git clone https://github.com/pedro-henrique1/Destino-Viagem.git

```

2. Copiar o arquivo de configuração

```
cd src/main/resources

cp application-example.properties  application.properties

```

3. instalar dependências do spring boot e maven

## Usando

1. iniciar aplicação pela ide ou com o maven
2. O acesso a api será por http://localhost:8080

## Endpoints da API

1. Para depoimentos

```
GET /depoimentos - Retorna 3 depoimentos aleatórios cadastrados no banco de dados.

POST /depoimentos - Cria os depoimentos.

PUT /depoimentos/{id} - Altera a descrição do depoimento.

DELETE /depoimentos/delete/{id} - Delete o depoimento desejado.
```

2. Para destinos

```
GET /destino/{name} - Retorna o preço, imagem e descriçao do lugar pesquisado cadastrados no banco de dados.

POST /destino - Cria os destino.

PUT /destino/{id} - Altera os dados do destino.

DELETE /destino/delete/{id} - Delete o destino desejado.

```

## Banco de dados

Para esse projeto usei Mysql como banco de dados. E o Flyway como gerenciador para criação das migrations do banco

Para instalar o Mysql click [aqui](https://www.mysql.com/)





