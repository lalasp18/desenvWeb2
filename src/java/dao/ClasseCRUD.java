package dao;

import dominio.Classe;
import gerTarefas.ConexaoBanco;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

//drop table classe;
//create table classe(
//    idClasse SERIAL primary key,
//    nome varchar(30) not null,
//    valor double precision not null,
//    dataDevolucao Date not null
//);

public class ClasseCRUD extends HttpServlet {
    private Connection conexao;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ClasseCRUD</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ClasseCRUD at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
        this.conexao = ConexaoBanco.getConnection();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String nome = request.getParameter("inputNome");
            String valor = request.getParameter("inputValor");
            String data = request.getParameter("inputDt");
            
            String mensagem = "";
            
            Classe classe = new Classe();
            classe.setNome(nome);
            classe.setValor(Double.valueOf(valor));
//            classe.setDataDevolucao(dataDevolucao);
            
            try {
                String sql = "INSERT INTO classe (nome, valor, dtdevolucao) VALUES (?)";
                PreparedStatement stmt = conexao.prepareStatement(sql);
                stmt.setString(1, classe.getNome());
                stmt.setString(1, classe.getNome());
                stmt.setString(1, classe.getNome());
                stmt.executeUpdate();
                
                mensagem = "Ator cadastrado com sucesso!";
            } catch (SQLException e) {
                e.printStackTrace();
                mensagem = "Erro! Ator não cadastrado.";
            }
            
            
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(ClasseCRUD.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(ClasseCRUD.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
