package DAO;

import Model.Fornecedor;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FornecedorDAO {

    public Connection getConexao() {

        Connection connection = null;

        try {
            String driver = "com.mysql.cj.jdbc.Driver";
            Class.forName(driver);

            String server = "estoque-ps6711534-f574.d.aivencloud.com:19913/defaultdb?ssl-mode=REQUIRED";
            String database = "defaultdb";
            String url = "jdbc:mysql://" + server + ":19913/" + database;
            String user = "avnadmin";
            String password = "AVNS_qUgty15qAyx3rTYNp5F";

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
    
    public List<Fornecedor> listarFornecedores() {
        List<Fornecedor> lista = new ArrayList<>();
        try {
            Connection conn = this.getConexao();
            if (conn != null) {
                String sql = "SELECT id_fornecedor, nome FROM tb_fornecedores";
                Statement stmt = conn.createStatement();
                ResultSet res = stmt.executeQuery(sql);
                
                while (res.next()) {
                    Fornecedor f = new Fornecedor(res.getInt("id_fornecedor"), res.getString("nome"));
                    lista.add(f);
                }
                conn.close();
            }
        } catch (SQLException e) {
            System.out.println("Erro ao listar fornecedores: " + e.getMessage());
        }
        return lista;
    }
}

