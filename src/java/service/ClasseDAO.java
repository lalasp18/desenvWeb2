package service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Classe;
import postgres.ConexaoBanco;

public class ClasseDAO {
    private Connection conexao;

    public ClasseDAO(Connection conexao) throws SQLException {
        this.conexao = ConexaoBanco.getConnection();
    }
    
    public void inserir(Classe classe) throws SQLException {
        String sql = "INSERT INTO classe (nome, valor, data_devolucao) VALUES (?, ?, ?)";
        PreparedStatement stmt = conexao.prepareStatement(sql);
        stmt.setString(1, classe.getNome());
        stmt.setDouble(2, classe.getValor());
        stmt.setDate(3, new java.sql.Date(classe.getDataDevolucao().getTime()));
        stmt.executeUpdate();
    }

    public void atualizar(Classe classe) throws SQLException {
    }

    public List<Classe> selecionarTodos() throws SQLException {
        return new ArrayList<>();
    }

    public void excluir(int id) throws SQLException {
    }
}
