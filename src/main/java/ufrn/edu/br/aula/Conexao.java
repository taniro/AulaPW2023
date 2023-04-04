package ufrn.edu.br.aula;

import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {

    /*
    Default env:
    DATABASE_HOST=localhost;DATABASE_PORT=5432;DATABASE_NAME=tarefadb;DATABASE_USERNAME=postgres;DATABASE_PASSWORD=postgres
    */

    /*
    * SQL PARA CRIAÇÃO DO BANCO
    * CREATE TABLE IF NOT EXISTS tarefa_tbl (id SERIAL PRIMARY KEY, texto VARCHAR(55), prioridade INTEGER, data_cadastro bigint);
    * */
    public static Connection getConnection() throws SQLException, URISyntaxException {
        String dbUri = System.getenv("DATABASE_HOST");
        String dbPort = System.getenv("DATABASE_PORT");
        String dbName = System.getenv("DATABASE_NAME");

        String username = System.getenv("DATABASE_USERNAME");
        String password = System.getenv("DATABASE_PASSWORD");
        String dbUrl = "jdbc:postgresql://" + dbUri + ':' + dbPort + "/" + dbName + "?serverTimezone=UTC";

        return DriverManager.getConnection(dbUrl, username, password);
    }
}
