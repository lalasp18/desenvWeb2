package controller;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Ator;
import postgres.ConexaoBanco;
import service.AtorDAO;

//drop table ator;
//create table ator(
//    idAtor SERIAL primary key,
//    nome varchar(30) not null
//);

public class AtorController extends HttpServlet {
    private AtorDAO atorDAO;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            // Inicialize o ClasseDAO com a conexão ao banco de dados
            Connection conexao = ConexaoBanco.getConnection();
            atorDAO = new AtorDAO(conexao);
        } catch (SQLException ex) {
            Logger.getLogger(ClasseController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String nome = request.getParameter("inputNome");

        String mensagem = "";
        
        Ator ator = new Ator();
        ator.setNome(nome);
        try {
            atorDAO.inserir(ator);
            mensagem = "Ator cadastrado com sucesso!";
        } catch (SQLException e) {
            e.printStackTrace();
            mensagem = "Erro! Ator não cadastrado.";
        }

//        request.setAttribute("mensagem", mensagem);
//        request.getRequestDispatcher("/ator/atorC.jsp").forward(request, response);
        
        processRequest(request, response);

    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
