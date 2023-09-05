package controller;

import application.AtorApplication;
import application.ConexaoHibernate;
import domain.Ator;
import application.GenericDAO;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import dto.AtorDTO;
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

//drop table ator;
//create table ator(
//    idAtor SERIAL primary key,
//    nome varchar(30) not null
//);

@WebServlet(urlPatterns = {"/ator-control/listar", "/ator-control/postar", "/ator-control/editar", "/ator-control/excluir"})
public class AtorController extends HttpServlet {
    GenericDAO genDAO = null;
    AtorApplication atorAPP = null;

    public AtorController() throws HibernateException{
        ConexaoHibernate.getSessionFactory();
        
        genDAO = new GenericDAO();
        atorAPP = new AtorApplication();
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
            List<Ator> listaAtores = new ArrayList<>();
            listaAtores = atorAPP.listar(Ator.class);
            
            Gson gson = new Gson();
            String jsonAtores = gson.toJson(listaAtores);

            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            
            response.getWriter().write(jsonAtores);
        } catch (HibernateException ex) {
            Logger.getLogger(AtorController.class.getName()).log(Level.SEVERE, null, ex);
        }
            
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String nome = request.getParameter("inputNome");
        
        try {
            Ator ator = new Ator(nome);
            atorAPP.inserir(ator);

        } catch (HibernateException ex) {
            Logger.getLogger(AtorController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(AtorController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AtorController.class.getName()).log(Level.SEVERE, null, ex);
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
        AtorDTO atorDTO = objectMapper.readValue(jsonRequest.toString(), AtorDTO.class);

        try {
            Ator ator = new Ator(atorDTO.getId(), atorDTO.getNome());
            atorAPP.alterar(ator);
        } catch (HibernateException ex) {
            Logger.getLogger(AtorController.class.getName()).log(Level.SEVERE, null, ex);
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
            Ator ator = (Ator) atorAPP.get(Ator.class, id);
            atorAPP.excluir(ator);
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
