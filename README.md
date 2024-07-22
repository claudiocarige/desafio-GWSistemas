# Sistema de Gerenciamento de Entregas da Tartaruga Cometa 🐢🚀

Este projeto Java Web foi desenvolvido para atender aos requisitos de um desafio técnico proposto pela GW Sistemas,
simulando um mini mundo de entregas para a fictícia empresa "Tartaruga Cometa".

## Descrição do Problema

A Tartaruga Cometa necessitava de um sistema para gerenciar suas entregas de produtos, controlando informações cruciais
como:

* **Dados do Remetente e Destinatário:**  Nome/Razão Social, CPF/CNPJ e Endereço.
* **Detalhes do Produto:** Nome, Peso, Volume e Valor.
* **Status da Entrega:**  Acompanhamento se a entrega foi realizada ou não.

## Solução Implementada

A aplicação web foi desenvolvida em Java, utilizando as seguintes tecnologias:

* **Backend:** Java (JDK 17)
* **Servidor de Aplicação:** Heroku
* **Banco de Dados:** PostgreSQL
* **Framework:**  Spring Framework
* **Frontend:** Angular

**Funcionalidades Principais:**

* Cadastro de clientes (pessoas físicas e jurídicas) com informações completas.
* Cadastro de produtos com detalhes relevantes para o transporte.
* Lançamento de entregas, associando remetentes, destinatários e produtos.
* Cálculo automático do valor do frete (considerando distância, peso, volume e taxas).
* Geração de senha única para Confirmação de cada entrega(aumentar a segurança).
* Atualização do status da entrega.
* Envio de emails de confirmação de entrega e notificação de senha.
* Integração com a API do Google Maps para cálculo de distância.

## Estrutura do Projeto

O código-fonte está organizado em pacotes para facilitar a manutenção:

* **`br.com.claudiocarige.mspersistencedb.core.domain.entities`:** Contém as entidades do sistema, representando os
  objetos de negócio como clientes, entregas e produtos.
* **`br.com.claudiocarige.mspersistencedb.core.exceptions`:** Contém as classes personalizadas de exceção.
* **`br.com.claudiocarige.mspersistencedb.core.dtos`:**  Classes DTO (Data Transfer Object) para transferência de dados
  entre camadas.
* **`br.com.claudiocarige.mspersistencedb.core.usecases`:** Camada de casos de uso, definindo as regras de negócio.
* **`br.com.claudiocarige.mspersistencedb.core.usecases.impl`:** Implementação dos casos de uso.
* **`br.com.claudiocarige.mspersistencedb.infra.persistence.repositories.postgresrepository`:** Repositórios para
  persistência de dados (usando Spring Data JPA).
* **`br.com.claudiocarige.mspersistencedb.infra.adapters.google_mail_services`:** Adaptadores para serviços externos,
  como envio de email.
* **`br.com.claudiocarige.mspersistencedb.infra.adapters.google_maps_service`:**  Adaptadores para integração com a API
  do Google Maps.

**Classes Principais:**

* **`CompanyCustomer.java`,  `IndividualCustomer.java` e `Customers.java`:** Implementam o conceito de herança para
  representar clientes, diferenciando pessoas físicas de jurídicas.
* **`Delivery.java`:** Gerencia as entregas, incluindo informações de remetente, destinatário, produtos, status e senha.
* **`Item.java`:**  Representa os itens incluídos em uma entrega.
* **`Product.java`:** Define as características dos produtos a serem transportados.
* **`ConvertClassToDTOService.java`:**  Responsável por converter entidades em DTOs e vice-versa.
* **`CustomerServiceImpl.java`:** Implementa a lógica de negócio para gerenciamento de clientes.
* **`DeliveryServiceImpl.java`:** Implementa a lógica de negócio para gerenciamento de entregas, incluindo cálculo de
  frete, geração de senha e atualização de status.
* **`ProductServiceImpl.java`:**  Implementa a lógica de negócio para gerenciamento de produtos.
* **`DeliveryEmailSendingServiceImpl.java`:**  Responsável pelo envio de emails relacionados às entregas, utilizando o
  Thymeleaf como template engine.
* **`GoogleAPIDistanceMatrixServiceImpl.java`:** Implementa a integração com a API do Google Distance Matrix para
  calcular a distância entre dois pontos.

## Critérios de Avaliação

O projeto foi desenvolvido considerando os seguintes pontos de atenção:

* **Domínio da Linguagem Java:** Clareza, organização e boas práticas de programação.
* **Padrão de Projeto:** Aplicação dos padrões DAO (Data Access Object) e DTO (Data Transfer Object).
* **Organização do Código:** Código limpo, legível e bem documentado.
* **Normalização do Banco de Dados:**  Estruturas de tabelas otimizadas.
* **Criatividade:** Soluções inovadoras e eficientes.

## Instruções de Execução

1. Faça o download ou clone este repositório.
2. Importe o projeto em sua IDE Java (ex: Eclipse, IntelliJ).
3. Configure o banco de dados utilizado no arquivo de propriedades do projeto.
4. Obtenha uma chave de API do Google Maps e configure no arquivo de propriedades.
5. Configure as credenciais do email de envio no arquivo de propriedades.
6. Compile e execute a aplicação no Tomcat.
7. Acesse a aplicação através de um navegador web (a URL será informada na inicialização do servidor).

## Inserir um novo cliente pela API

1. Acesse o Swagger da aplicação: https://gwsistemas-43f1ae9234ad.herokuapp.com/swagger-ui/index.html
2. Clique no endpoint `/api/v1/customers/create/pj` para inserir um novo cliente pessoa jurídica.
3. Preencha os campos obrigatórios e clique em "Try it out".
4. O cliente será inserido no banco de dados e uma resposta será exibida na tela.
5. Verifique se o cliente foi inserido corretamente acessando o endpoint `/api/v1/customers/id`
   ou `/api/v1/customers/list`.
6. Repita o processo para inserir um cliente pessoa física no endpoint `/api/v1/customers/create/pf`.

## Autor

Cláudio Carigé

Documentação da API -  https://gwsistemas-43f1ae9234ad.herokuapp.com/swagger-ui/index.html

ENDPOINTS :

     /api/v1/product
     /api/v1/product/{id}
     /api/v1/product/save
     /api/v1/customers
     /api/v1/customers/{id}
     /api/v1/customers/create/pj
     /api/v1/customers/list
     /api/v1/delivery
     /api/v1/delivery/{id}
     /api/v1/delivery/check-delivery/{deliveryId}
     /api/v1/delivery/request-delivery/save
     /api/v1/delivery/delivery-confirmation
              
