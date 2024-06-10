<%-- 
    Document   : BD
    Created on : Jun 9, 2024, 8:53:30 PM
    Author     : Alyta
--%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="mrysi.beans.Persona"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<!DOCTYPE html>
<html>
    <% getServletContext().getRequestDispatcher("/ServletEscuelas").include(request, response); %>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <table>
            <thead>
                <tr>
                    <th>id</th>
                    <th>nif</th>
                    <th>nombre</th>
                    <th>Apellido1</th>
                    <th>Apellido2</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items ="${requestScope.ListaEscuelas}" var="e">
                <tr>
                    <td><c:out value="${e.id}"/></td>
                    <td><c:out value="${e.nif}"/></td>
                    <td><c:out value="${e.nombre}"/></td>
                    <td><c:out value="${e.apellido1}"/></td>
                    <td><c:out value="${e.apellido2}"/></td>
                </tr>
                </c:forEach>
            </tbody>
        </table>

    </body>
</html>