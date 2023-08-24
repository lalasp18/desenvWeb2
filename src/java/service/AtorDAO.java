package service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Ator;
import postgres.ConexaoBanco;

public class AtorDAO {
    private Connection conexao;

    public AtorDAO(Connection conexao) throws SQLException {
        this.conexao = ConexaoBanco.getConnection();
    }
    
    public void inserir(Ator ator) throws SQLException {
        String sql = "INSERT INTO ator (nome) VALUES (?)";
        PreparedStatement stmt = conexao.prepareStatement(sql);
        stmt.setString(1, ator.getNome());
        stmt.executeUpdate();
    }

    public void atualizar(Ator ator) throws SQLException {
    }

    public List<Ator> selecionarTodos() throws SQLException {
        return new ArrayList<>();
    }

    public void excluir(int id) throws SQLException {
    }
}
