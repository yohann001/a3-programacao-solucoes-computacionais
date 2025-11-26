package DAO;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConexaoDB {

    public static Connection getConexao() {
        try {

            Properties props = new Properties();

            try (FileInputStream fis = new FileInputStream("db.properties")) {
                props.load(fis);
            }

            String driver = "com.mysql.cj.jdbc.Driver";
            Class.forName(driver);

            String url = props.getProperty("DB_URL");
            String user = props.getProperty("DB_USER");
            String password = props.getProperty("DB_PASSWORD");

            Connection connection = DriverManager.getConnection(url, user, password);

            if (connection != null) {

            }
            return connection;

        } catch (IOException e) {
            System.out.println("ERRO: Arquivo db.properties não encontrado na raiz do projeto.");
            return null;
        } catch (ClassNotFoundException e) {
            System.out.println("ERRO: Driver JDBC não encontrado.");
            return null;
        } catch (SQLException e) {
            System.out.println("ERRO: Não foi possível conectar ao banco. Verifique url/usuario/senha.");
            return null;
        }
    }
}
