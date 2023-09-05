package controller;

import application.ClasseApplication;
import application.ConexaoHibernate;
import application.GenericDAO;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import domain.Classe;
import dto.ClasseDTO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.hibernate.HibernateException;

//drop table classe;
//create table classe(
//    idClasse SERIAL primary key,
//    nome varchar(30) not null,
//    valor double precision not null,
//    dataDevolucao Date not null
//);

@WebServlet(urlPatterns = {"/classe-control/listar", "/classe-control/postar", "/classe-control/editar", "/classe-control/excluir"})
public class ClasseController extends HttpServlet {
    GenericDAO genDAO = null;
    ClasseApplication classeAPP = null;

    public ClasseController() throws HibernateException{
        ConexaoHibernate.getSessionFactory();
        
        genDAO = new GenericDAO();
        classeAPP = new ClasseApplication();
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException, ClassNotFoundException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
//            out.println("<!DOCTYPE html>");
//            out.println("<html>");
//            out.println("<head>");
//            out.println("<title>Servlet ClasseCRUD</title>");            
//            out.println("</head>");
//            out.println("<body>");
//            out.println("<h1>Servlet ClasseCRUD at " + request.getContextPath() + "</h1>");
//            out.println("</body>");
//            out.println("</html>");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            List<Classe> listaClasses = new ArrayList<>();
            listaClasses = classeAPP.listar(Classe.class);
            
            Gson gson = new Gson();
            String jsonClasses = gson.toJson(listaClasses);

            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");

            response.getWriter().write(jsonClasses);
        } catch (HibernateException ex) {
            Logger.getLogger(ClasseController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String nome = request.getParameter("inputNome");
        
        String valor = request.getParameter("inputValor");
        double numeroDouble = 0.0;
        try {
            numeroDouble = Double.parseDouble(valor);
        } catch (NumberFormatException e) {
            System.err.println("Erro ao converter a String para Double: " + e.getMessage());
        }
        
        String data = request.getParameter("inputDt");
        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
        Date dataDate = new Date();
        try {
            dataDate = formato.parse(data);
        } catch (ParseException e) {
            System.err.println("Erro ao converter a String para Date: " + e.getMessage());
        }
        
        System.out.println(nome+" - "+valor+": "+dataDate);
        
        try {
            Classe classe = new Classe(nome, numeroDouble, dataDate);
            classeAPP.inserir(classe);
        } catch (HibernateException ex) {
            Logger.getLogger(ClasseController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ClasseController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ClasseController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        BufferedReader reader = request.getReader();
        StringBuilder jsonRequest = new StringBuilder();
        String line;
        
        while ((line = reader.readLine()) != null) {
            jsonRequest.append(line);
        }

        ObjectMapper objectMapper = new ObjectMapper();
        ClasseDTO classeDTO = objectMapper.readValue(jsonRequest.toString(), ClasseDTO.class);

        try {
            Classe classe = new Classe(classeDTO.getId(), classeDTO.getNome(), classeDTO.getValor(), classeDTO.getData());
            classeAPP.alterar(classe);
        } catch (HibernateException ex) {
            Logger.getLogger(ClasseController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        BufferedReader reader = request.getReader();
        StringBuilder jsonRequest = new StringBuilder();
        String line;
        
        while ((line = reader.readLine()) != null) {
            jsonRequest.append(line);
        }

        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(jsonRequest.toString());
        int id = jsonNode.get("id").asInt();
    
        try {
            Classe classe = (Classe) classeAPP.get(Classe.class, id);
            classeAPP.excluir(classe);
        } catch (NumberFormatException ex) {
            Logger.getLogger(AtorController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (HibernateException ex) {
            Logger.getLogger(AtorController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
