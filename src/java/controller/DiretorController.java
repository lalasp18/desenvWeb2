package controller;

import application.DiretorApplication;
import application.ConexaoHibernate;
import domain.Diretor;
import application.GenericDAO;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import dto.DiretorDTO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.hibernate.HibernateException;

//drop table diretor;
//create table diretor(
//    idDiretor SERIAL primary key,
//    nome varchar(30) not null
//);

@WebServlet(urlPatterns = {"/diretor-control/listar", "/diretor-control/postar", "/diretor-control/editar", "/diretor-control/excluir"})
public class DiretorController extends HttpServlet {
    GenericDAO genDAO = null;
    DiretorApplication diretorAPP = null;

    public DiretorController() throws HibernateException{
        ConexaoHibernate.getSessionFactory();
        
        genDAO = new GenericDAO();
        diretorAPP = new DiretorApplication();
    }
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException, HibernateException {
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
            List<Diretor> listaDiretores = new ArrayList<>();
            listaDiretores = diretorAPP.listar(Diretor.class);
            
            Gson gson = new Gson();
            String jsonDiretores = gson.toJson(listaDiretores);

            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            
            response.getWriter().write(jsonDiretores);
        } catch (HibernateException ex) {
            Logger.getLogger(DiretorController.class.getName()).log(Level.SEVERE, null, ex);
        }
            
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String nome = request.getParameter("inputNome");
        
        try {
            Diretor diretor = new Diretor(nome);
            diretorAPP.inserir(diretor);

        } catch (HibernateException ex) {
            Logger.getLogger(DiretorController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(DiretorController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DiretorController.class.getName()).log(Level.SEVERE, null, ex);
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
        DiretorDTO diretorDTO = objectMapper.readValue(jsonRequest.toString(), DiretorDTO.class);

        try {
            Diretor diretor = new Diretor(diretorDTO.getId(), diretorDTO.getNome());
            diretorAPP.alterar(diretor);
        } catch (HibernateException ex) {
            Logger.getLogger(DiretorController.class.getName()).log(Level.SEVERE, null, ex);
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
            Diretor diretor = (Diretor) diretorAPP.get(Diretor.class, id);
            diretorAPP.excluir(diretor);
        } catch (NumberFormatException ex) {
            Logger.getLogger(DiretorController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (HibernateException ex) {
            Logger.getLogger(DiretorController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
