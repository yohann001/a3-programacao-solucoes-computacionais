package Model;

import DAO.ProdutoDAO;
import java.util.;
import java.sql.;
import java.util.ArrayList;

import java.time.LocalDate;

public class Produto {
    private int id_produto;
    private String nome_produto;
    private String descricao_produto;
    private int quantidade_estoque;
    private double preco;
    private LocalDate data_cadastro;

    private final ProdutoDAO dao;

    public Produto() {
        this.dao = new ProdutoDAO();

    }

    public Produto(int id_produto, String nome_produto, String descricao_produto, int quantidade_estoque, double preco,
            LocalDate data_cadastro) {
        this.id_produto = id_produto;
        this.nome_produto = nome_produto;
        this.descricao_produto = descricao_produto;
        this.quantidade_estoque = quantidade_estoque;
        this.preco = preco;
        this.data_cadastro = data_cadastro;
        this.dao = new ProdutoDAO();
    }

    public int getId_produto() {
        return id_produto;
    }

    public void setId_produto(int id_produto) {
        this.id_produto = id_produto;
    }

    public String getNome_produto() {
        return nome_produto;
    }

    public void setNome_produto(String nome_produto) {
        this.nome_produto = nome_produto;
    }

    public String getDescricao_produto() {
        return descricao_produto;
    }

    public void setDescricao_produto(String descricao_produto) {
        this.descricao_produto = descricao_produto;
    }

    public int getQuantidade_estoque() {
        return quantidade_estoque;
    }

    public void setQuantidade_estoque(int quantidade_estoque) {
        this.quantidade_estoque = quantidade_estoque;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public LocalDate getData_cadastro() {
        return data_cadastro;
    }

    public void setData_cadastro(LocalDate data_cadastro) {
        this.data_cadastro = data_cadastro;
    }

    @Override
    public String toString() {
        return "\n ID: " + this.getId_produto() +
               "\n Nome: " + this.getNome_produto() +
               "\n Descrição: " + this.getDescricao_produto() +
               "\n Quantidade em Estoque: " + this.getQuantidade_estoque() +
               "\n Preço: " + this.getPreco() +
               "\n Data de Cadastro: " + this.getData_cadastro() +
                "\n---------------------------";

    public Arraylist<Produtos> getListaProdutos() {
        return dao.getMinhalista();
    }

    public boolean InsertProduto(String nome_produto, String descricao_produto, int quantidade_estoque, double preco,
            LocalDate data_cadastro) {
        int id = this.maiorID() + 1;
        Produto objeto = new Produto(id, nome_produto, descricao_produto, quantidade_estoque, preco, data_cadastro);

        return dao.InsertProduto(objeto);
        return true;
    }

    public boolean DeleteProduto(int id) {
        dao.DeleteProduto(id);
        return true;
    }

    public boolean UpdateProduto(int id, String nome_produto, String descricao_produto, int quantidade_estoque,
            double preco, LocalDate data_cadastro) {
        Produto objeto = new Produto(id, nome_produto, descricao_produto, quantidade_estoque, preco, data_cadastro);
        dao.UpdateProduto(objeto);
        return true;
    }

    public Produto carregaProduto(int id) {
        return dao.carregaProduto(id);
    }

    public int maiorID() throws SQLException {
        return dao.maiorID();

    }

}