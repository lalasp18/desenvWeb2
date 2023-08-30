package gerTarefas;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoBanco {
    private static Connection conexao;
    private static final String URL = "jdbc:postgresql://localhost:5433/acervo";
    private static final String USER = "postgres";
    private static final String PASSWORD = "postgres";
    
    public static Connection getConnection() throws SQLException, ClassNotFoundException {
        Class.forName("org.postgresql.Driver", true, ConexaoBanco.class.getClassLoader());
        conexao = DriverManager.getConnection(URL, USER, PASSWORD);
        return conexao;
    }
}
