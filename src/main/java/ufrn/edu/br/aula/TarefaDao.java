package ufrn.edu.br.aula;

import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TarefaDao {

    public List<Tarefa> listarTodasTarefas(){

        Connection connection = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Tarefa> listaDeTarefas = new ArrayList<>();

        try {
            connection = Conexao.getConnection();

            stmt = connection.prepareStatement("select * from tarefa_tbl");
            rs = stmt.executeQuery();

            while (rs.next()) {
                Tarefa t = new Tarefa(new Date(rs.getLong("data_cadastro")), rs.getInt("id"));
                t.setTexto(rs.getString("texto"));
                t.setPrioridade(rs.getInt("prioridade"));
                listaDeTarefas.add(t);
            }
            connection.close();

        } catch (SQLException | URISyntaxException ex) {
            // response.getWriter().append("Connection Failed! Check output console");
        }

        return listaDeTarefas;
    }

    public void cadastrarTarefa(Tarefa t){

        Connection connection = null;
        PreparedStatement stmt = null;

        try {
            connection = Conexao.getConnection();

            stmt = connection.prepareStatement(
                    "insert into tarefa_tbl (texto, prioridade, data_cadastro) values (?,?,?)");

            stmt.setString(1, t.getTexto());
            stmt.setInt(2, t.getPrioridade());
            stmt.setFloat(3, t.getDataCadastro().getTime());

            stmt.executeUpdate();
            connection.close();

        } catch (SQLException | URISyntaxException ex) {
            // response.getWriter().append("Connection Failed! Check output console");
        }
    }
}
