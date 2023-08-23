package controller;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Classe;
import postgres.ConexaoBanco;
import service.ClasseDAO;

@WebServlet("/classe-control")
public class ClasseController extends HttpServlet {
    private ClasseDAO classeDAO;
    
    public void init() throws ServletException {
        try {
            // Inicialize o ClasseDAO com a conexão ao banco de dados
            Connection conexao = ConexaoBanco.getConnection();
            classeDAO = new ClasseDAO(conexao);
        } catch (SQLException ex) {
            Logger.getLogger(ClasseController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet Controller</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Controller at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
            
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
//        processRequest(request, response);
        PrintWriter out = response.getWriter();
        String nome = request.getParameter("nome");
        String valor = request.getParameter("valor");
        String dtDevolucao = request.getParameter("dtDevolucao");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
//        processRequest(request, response);
        String nome = request.getParameter("nome");
        String valor = request.getParameter("valor");
        String dtDevolucao = request.getParameter("dtDevolucao");

        String mensagem = "";
        
        try {
            Date data = new SimpleDateFormat("dd-MM-yyyy").parse(dtDevolucao);
            double valorDouble = Double.parseDouble(valor);
        
            Classe classe = new Classe();
            classe.setNome(nome);
            classe.setValor(valorDouble);
            classe.setDataDevolucao(data);
            try {
                classeDAO.inserir(classe);
                mensagem = "Classe cadastrada com sucesso!";
            } catch (SQLException e) {
                e.printStackTrace();
                mensagem = "Erro! Classe não cadastrada.";
            }
        } catch (ParseException e) {
            System.err.println("ParseException caught!");
            e.printStackTrace();
        } catch (NumberFormatException e) {
            System.err.println("NumberFormatException caught!");
            e.printStackTrace();
        }

        request.setAttribute("mensagem", mensagem);
        request.getRequestDispatcher("/classe/cadastrar.jsp").forward(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}

//drop table classe;
//create table classe(
//    idClasse SERIAL primary key,
//    nome varchar(30) not null,
//    valor double precision not null,
//    dataDevolucao Date not null
//);
