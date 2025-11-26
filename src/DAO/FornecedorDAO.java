package DAO;

import Model.Fornecedor;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FornecedorDAO {

    public Connection getConexao() {
        return ConexaoDB.getConexao();
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
