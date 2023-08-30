package dao;

import dominio.Ator;
import gerTarefas.ConexaoBanco;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

//drop table ator;
//create table ator(
//    idAtor SERIAL primary key,
//    nome varchar(30) not null
//);

public class AtorCRUD extends HttpServlet {
    private Connection conexao;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
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
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Ator> listaAtores = new ArrayList<>();
        
        String mensagem = "";

        try {
            Class.forName("org.postgresql.Driver");
            this.conexao = ConexaoBanco.getConnection();
            
            String sql = "SELECT * FROM ator";
            PreparedStatement stmt = conexao.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Ator ator = new Ator();
                ator.setIdAtor(rs.getInt("idAtor"));
                ator.setNome(rs.getString("nome"));

                listaAtores.add(ator);
            }

            rs.close();
            stmt.close();
            conexao.close();


            RequestDispatcher rd = request.getRequestDispatcher("ator/atorR.jsp");
            request.setAttribute("listaAtores", listaAtores);
//            request.setAttribute("mensagem", mensagem);
            rd.forward(request, response);
            
//            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(AtorCRUD.class.getName()).log(Level.SEVERE, null, ex);
            mensagem = "Erro! Atores não encontrados.";
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AtorCRUD.class.getName()).log(Level.SEVERE, null, ex);
            mensagem = "Erro! Atores não encontrados.";
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String nome = request.getParameter("inputNome");

        String mensagem = "";

        Ator ator = new Ator();
        ator.setNome(nome);
        
        try {
            Class.forName("org.postgresql.Driver");
            this.conexao = ConexaoBanco.getConnection();
            
            System.out.println("Connected");
            
            String sql = "INSERT INTO ator (nome) VALUES (?)";
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setString(1, ator.getNome());
            stmt.executeUpdate();

            mensagem = "Ator cadastrado com sucesso!";
            
            RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
            request.setAttribute("mensagem", mensagem);
            request.setAttribute("exibirToast", true);
            rd.forward(request, response);
            
//            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(AtorCRUD.class.getName()).log(Level.SEVERE, null, ex);
            mensagem = "Erro! Ator não cadastrado.";
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AtorCRUD.class.getName()).log(Level.SEVERE, null, ex);
            mensagem = "Erro! Ator não cadastrado.";
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
