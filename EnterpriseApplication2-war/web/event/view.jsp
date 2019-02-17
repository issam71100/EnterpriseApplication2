<%-- 
    Document   : event
    Created on : 17 févr. 2019, 08:48:55
    Author     : issam
--%>

<%@page import="java.util.List"%>
<%@page import="com.model.Event"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Hello World!</h1>
        <hr>
        <table>
            <tbody>
                <% @SuppressWarnings("unchecked")
                    Event event = (Event) request.getAttribute("eventbyid");
                %>
                <tr>
                    <th>ID</th>
                    <td><%=event.getId()%></td>
                </tr>
                <tr>
                    <th>Name</th>
                    <td><%=event.getName()%></td>

                </tr>
                <tr>
                    <th>Description</th>
                    <td><%=event.getDescription()%></td>
                </tr>
                <tr>
                    <th>Date</th>
                    <td><%=event.getDate()%></td>
                </tr>
                <tr>
                    <th>Localization</th>
                    <td><%=event.getId()%></td>
                </tr>
                <tr>
                    <th>Price</th>
                    <td><%=event.getPrice()%></td>
                </tr>
                <tr>
                    <th>Participant</th>
                    <td>nothing</td>
                </tr>
            </tbody>
        </table>
            <hr>
            <a href="eventServlet/add">Ajouter un Evenement</a>
            <a href="eventServlet">Index des Evenement</a>
    </body>
</html>
