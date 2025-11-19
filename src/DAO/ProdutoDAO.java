package DAO;

import Model.Produto;
import java.util.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Date;
import java.time.LocalDate;

public class ProdutoDAO {

    public static ArrayList<Produto> MinhaLista = new ArrayList<Produto>();

    public ProdutoDAO() {
    }

    public int maiorID() throws SQLException {

        int maiorID = 0;
        try {
            Statement stmt = this.getConexao().createStatement();
            ResultSet res = stmt.executeQuery("SELECT MAX(id_produto) id FROM tb_produtos");
            res.next();
            maiorID = res.getInt("id");

            stmt.close();

        } catch (SQLException ex) {
        }

        return maiorID;
    }

    public Connection getConexao() {

        Connection connection = null;

        try {
            String driver = "com.mysql.cj.jdbc.Driver";
            Class.forName(driver);

            String server = "bwgyactsuuve5cowvycs-mysql.services.clever-cloud.com";
            String database = "bwgyactsuuve5cowvycs";
            String url = "jdbc:mysql://" + server + ":3306/" + database;
            String user = "ublbzt0cknm4phvd";
            String password = "GJdQI3oichlWhHepNLhT";

            connection = DriverManager.getConnection(url, user, password);

            if (connection != null) {
                System.out.println("Status: Conectado!");
            } else {
                System.out.println("Status: NÃO CONECTADO!");
            }

            return connection;

        } catch (ClassNotFoundException e) {
            System.out.println("O driver nao foi encontrado. " + e.getMessage());
            return null;

        } catch (SQLException e) {
            System.out.println("Nao foi possivel conectar...");
            return null;
        }
    }

    public ArrayList<Produto> getMinhaLista() {

        MinhaLista.clear();

        try {
            Statement stmt = this.getConexao().createStatement();
            ResultSet res = stmt.executeQuery("SELECT * FROM tb_produtos");
            while (res.next()) {

                int id = res.getInt("id_produto");
                String nome = res.getString("nome_produto");
                String descricao = res.getString("descricao_produto");
                int qtd = res.getInt("quantidade_estoque");
                double preco = res.getDouble("preco");
                LocalDate data = res.getDate("data_cadastro").toLocalDate();
//                LocalDate dataAtualizacao = res.getDate("data_atualizacao").toLocalDate();
                //tirei isso pq ele buscava uma coluna q nao existia e por isso nao atualizava a tabela, qnd tiver pode add dnv
                Produto objeto = new Produto(id, nome, descricao, qtd, preco, data);

                MinhaLista.add(objeto);
            }

            stmt.close();

        } catch (SQLException ex) {
        }

        return MinhaLista;
    }

    public boolean InsertProdutoBD(Produto objeto) {
        String sql = "INSERT INTO tb_produtos(id_produto, nome_produto, descricao_produto, quantidade_estoque, preco, data_cadastro) VALUES(?,?,?,?,?,?)";

        try {
            PreparedStatement stmt = this.getConexao().prepareStatement(sql);

            stmt.setInt(1, objeto.getId_produto());
            stmt.setString(2, objeto.getNome_produto());
            stmt.setString(3, objeto.getDescricao_produto());
            stmt.setInt(4, objeto.getQuantidade_estoque());
            stmt.setDouble(5, objeto.getPreco());
            stmt.setDate(6, java.sql.Date.valueOf(objeto.getData_cadastro()));

            stmt.execute();
            stmt.close();

            return true;

        } catch (SQLException erro) {
            throw new RuntimeException(erro);
        }

    }

    public boolean DeleteProdutoBD(int id) {
        String sql = "DELETE FROM tb_produtos WHERE id_produto = ?";
        try {
            PreparedStatement stmt = this.getConexao().prepareStatement(sql);
            stmt.setInt(1, id);
            stmt.execute();
            stmt.close();
            return true;
        } catch (SQLException erro) {
            throw new RuntimeException(erro);
        }
    }

    public boolean UpdateProdutoBD(Produto objeto) {
        String sql = "UPDATE tb_produtos set nome_produto = ? ,descricao_produto = ? ,quantidade_estoque = ? ,preco = ?, data_cadastro = ? WHERE id_produto = ?";

        try {
            PreparedStatement stmt = this.getConexao().prepareStatement(sql);

            stmt.setString(1, objeto.getNome_produto());
            stmt.setString(2, objeto.getDescricao_produto());
            stmt.setInt(3, objeto.getQuantidade_estoque());
            stmt.setDouble(4, objeto.getPreco());
            stmt.setDate(5, java.sql.Date.valueOf(objeto.getData_cadastro()));
            stmt.setInt(6, objeto.getId_produto());

            stmt.execute();
            stmt.close();

            return true;

        } catch (SQLException erro) {
            throw new RuntimeException(erro);
        }

    }

    public Produto carregaProduto(int id) {
        Produto objeto = new Produto();
        objeto.setId_produto(id);

        try {
            Statement stmt = this.getConexao().createStatement();
            ResultSet res = stmt.executeQuery("SELECT * FROM tb_produtos WHERE id_produto = " + id);

            if (res.next()) {
                objeto.setNome_produto(res.getString("nome_produto"));
                objeto.setDescricao_produto(res.getString("descricao_produto"));
                objeto.setQuantidade_estoque(res.getInt("quantidade_estoque"));
                objeto.setPreco(res.getDouble("preco"));
                objeto.setData_cadastro(res.getDate("data_cadastro").toLocalDate());
            }

            stmt.close();

        } catch (SQLException erro) {
            System.out.println("Erro ao carregar produto: " + erro.getMessage());
        }
        return objeto;
    }

    //ADICIONAIS
    public ArrayList<Produto> getMinhaListaOrdenadaPorPreco() {
        String sql = "SELECT * FROM tb_alunos ORDER BY preco ASC";
        MinhaLista.clear();
        try {
            PreparedStatement stmt = this.getConexao().prepareStatement(sql);
            ResultSet res = stmt.executeQuery();
            
            while (res.next()) {
                int id = res.getInt("id_produto");
                String nome = res.getString("nome_produto");
                String descricao = res.getString("descricao_produto");
                int qtd = res.getInt("quantidade_estoque");
                double preco = res.getDouble("preco");
                java.time.LocalDate data = res.getDate("data_cadastro").toLocalDate();

                Produto objeto = new Produto(id, nome, descricao, qtd, preco, data);
                MinhaLista.add(objeto);
            }
            stmt.close();
        } catch (SQLException ex) {
            System.out.println("Erro ao ordenar: " + ex.getMessage());
        }
        return MinhaLista;
    }


}
