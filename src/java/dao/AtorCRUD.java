package dao;

import dominio.Ator;
import gerTarefas.ConexaoBanco;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

//drop table ator;
//create table ator(
//    idAtor SERIAL primary key,
//    nome varchar(30) not null
//);

//@WebServlet("/ator-control/*")
public class AtorCRUD extends HttpServlet {

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
            request.setAttribute("exibirToast", false);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Ator> listaAtores = new ArrayList<>();
        
        String mensagem = "";
        String color = "";
        boolean exibirToast = false;
        try{
            Statement stmt = ConexaoBanco.getConnection().createStatement();
            String sql = "SELECT * FROM ator";
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                Ator ator = new Ator(rs.getInt("idAtor"), rs.getString("nome"));
                mensagem = mensagem + ator.getIdAtor() +": "+ ator.getNome() + "\n";

                System.out.println(mensagem+"aqui\n");
                listaAtores.add(ator);
            }

//            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(AtorCRUD.class.getName()).log(Level.SEVERE, null, ex);
            mensagem = "Erro! Atores não encontrados.";
            color = "#FC6F6F";
            
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AtorCRUD.class.getName()).log(Level.SEVERE, null, ex);
            mensagem = "Erro! Atores não encontrados.";
            color = "#FC6F6F";
        }
        
        request.setAttribute("mensagem", mensagem);
        request.setAttribute("color", color);
        request.setAttribute("exibirToast", true);

        RequestDispatcher rd = request.getRequestDispatcher("ator/atorR.jsp");
        request.setAttribute("listaAtores", listaAtores);
        rd.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String nome = request.getParameter("inputNome");

        String mensagem = "";
        String color = "";
        boolean exibirToast = false;

        Ator ator = new Ator();
        ator.setNome(nome);
        
        try {
            String sql = "INSERT INTO ator (nome) VALUES (?)";
            PreparedStatement pstmt = ConexaoBanco.getConnection().prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            int ind = 1;
            pstmt.setString(ind++, ator.getNome());
            pstmt.execute();
            
            mensagem = "Ator cadastrado com sucesso!";
            color = "#517B2A";
            
//            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(AtorCRUD.class.getName()).log(Level.SEVERE, null, ex);
            mensagem = "Erro! Ator não cadastrado.";
            color = "#FC6F6F";
            
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AtorCRUD.class.getName()).log(Level.SEVERE, null, ex);
            mensagem = "Erro! Ator não cadastrado.";
            color = "#FC6F6F";
        }
        
        request.setAttribute("mensagem", mensagem);
        request.setAttribute("color", color);
        request.setAttribute("exibirToast", true);

        RequestDispatcher rd = request.getRequestDispatcher("ator/atorC.jsp");
        rd.forward(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
