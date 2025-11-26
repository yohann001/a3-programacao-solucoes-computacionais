# 👟 Sneaker Flow - Sistema de Gestão de Estoque
Este projeto é uma aplicação desktop desenvolvida em Java (Swing) para a gestão de inventário da loja de ténis Sneaker Flow. O sistema permite o controlo completo de produtos, fornecedores e geração de relatórios, utilizando uma arquitetura MVC (Model-View-Controller) e persistência de dados em um servidor MySQL rodando na nuvem.

Este trabalho foi desenvolvido no âmbito da Unidade Curricular de A3 - Programação de Soluções Computacionais da faculdade Unisul.


## 📖 Sobre o Projeto
A Sneaker Flow é uma empresa especializada na venda de ténis desportivos e casuais. Este software foi criado para organizar o sistema de estoque, registando e controlando cada produto por código, modelo, tamanho, quantidade e fornecedor, garantindo a precisão das informações e agilidade nos processos internos.

## 🚀 Funcionalidades
- Gestão de Produtos:

  - Registo de novos produtos com nome, descrição, quantidade, preço e fornecedor.

  - Edição e atualização de dados de produtos existentes.

  - Remoção de produtos do sistema.

- Consulta e Listagem:

  - Visualização de todos os produtos em formato de tabela.

  - Pesquisa dinâmica por nome ou descrição.

  - Ordenação de produtos por preço (crescente/decrescente).

- Relatórios:

  - Geração de relatório visual na aplicação.

  - Exportação para Word (.doc): Gera um ficheiro formatado com a lista de produtos.

  - Exportação para Excel (.csv): Gera um ficheiro separado por ponto e vírgula para análise de dados.

- Integração com Fornecedores: Seleção de fornecedores registados na base de dados durante o cadastro de produtos.

## 🛠 Tecnologias Utilizadas

- Linguagem: Java (JDK 24 configurado no projeto).

- Interface Gráfica (GUI): Java Swing (JFrame, JTable).

- Base de Dados: MySQL.

- Driver de Conexão: MySQL Connector/J 9.5.0.

- IDE Recomendado: NetBeans.

- Arquitetura: MVC (Model-View-Controller) e DAO (Data Access Object).

## 📂 Estrutura do Projeto
O código está organizado nos seguintes pacotes:

  - src/Model: Classes que representam as entidades (ex: Produto.java, Fornecedor.java).

  - src/View: Interfaces gráficas (ex: TelaPrincipal.java, GerenciaProduto.java, CadastroProduto.java).
  
  - src/Controller: Lógica de controlo e validação de dados (ex: ProdutoControllers.java).

  - src/DAO: Acesso à base de dados e execução de queries SQL (ex: ProdutoDAO.java, FornecedorDAO.java).

  - src/Imagens: Recursos visuais como logótipos e imagens de fundo.

## 👥 Autores
  - Pedro Manoel de Souza
  - Yohan Nascimento Costa
  - Gabriel Luz da Silva
  - Beatriz Ventura Marques
  - Emily Serafim
  - Ana Carolina Medina
