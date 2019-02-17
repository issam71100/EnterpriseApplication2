<%-- 
    Document   : event
    Created on : 17 févr. 2019, 08:48:55
    Author     : issam
--%>

<%@page import="java.util.Enumeration"%>
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
        <% if (session.getAttribute("name") != null) {%>
        <h2>Bienvenue <%= (String) session.getAttribute("name")%></h2>
        <%}%>
        <hr>
        <table>
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Name</th>
                    <th>Description</th>
                    <th>Date</th>
                    <th>Localization</th>
                    <th>Price</th>
                    <th>Participant</th>
                    <th>Actions</th>
                </tr>
            </thead>
            <tbody>
                <% @SuppressWarnings(  "unchecked")
                    List<Event> events = (List<Event>) request.getAttribute("eventList");
                    for (Event event : events) {
                %>
                <tr>
                    <td> <%=event.getId()%> </td>
                    <td> <%=event.getName()%> </td>
                    <td> <%=event.getDescription()%> </td>
                    <td> <%=event.getDate()%> </td>
                    <td> <%=event.getLocalization()%> </td>
                    <td> <%=event.getPrice()%> </td>
                    <td> nada </td>
                    <td> 
                        <ul>
                            <li>
                                <a href="eventServlet?id=<%=event.getId()%>">show</a>
                            </li>
                        </ul>
                    </td>
                </tr>

                <% }%>
            </tbody>
        </table>
        <hr>
        <a href="eventServlet/add">Ajouter un Evenement</a>
    </body>
</html>
