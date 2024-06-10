/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package mrysi.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import mrysi.beans.Persona;

/**
 *
 * @author Alyta
 */
public class ServletEscuelas extends HttpServlet {
    Connection connect;
    Statement statemnt;
    ResultSet resultset;
    
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ServletEscuelas</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ServletEscuelas at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try  {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String URL= "jdbc:mysql://localhost:3306/universidad?useSSL=false";
            String usuario="root";
            String contraseña ="0230";
            connect = DriverManager.getConnection(URL,usuario,contraseña);
            statemnt = connect.createStatement();
            System.out.print("La conexion se hizo correcta");
            request.setAttribute("ListaEscuelas", getListaPersonas());

        connect.close();
        }catch(java.sql.SQLException ex) {
                System.out.println(ex); 
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ServletEscuelas.class.getName()).log(Level.SEVERE, null, ex);
        }

        response.sendRedirect("BD.jsp");

    }
  

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    private List<Persona> getListaPersonas() throws java.sql.SQLException{
        String query = "SELECT *from persona";
        resultset = statemnt.executeQuery(query);

        List<Persona> personas = new ArrayList();
        Persona persona;
        while (resultset.next()){
        persona = new Persona();
        persona.setId(resultset.getInt(1));
        persona.setNif(resultset.getString(2));
        persona.setNombre(resultset.getString(3));
        persona.setApellido1(resultset.getString(4));
        persona.setApellido2(resultset.getString(5));
        personas.add(persona);
    }
        return personas;
    }
}

