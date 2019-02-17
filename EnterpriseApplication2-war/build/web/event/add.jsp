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
        <form method="POST" action="add">
            <br>Nom de l'evenement = <input type="text" name="name">
            <br>Description de l'evenement = <input type="text" name="description">
            <br>Date l'evenement = <input type="date" name="date">
            <br>Lieu de l'evenement = <input type="text" name="localization">
            <br>Prix de l'evenement = <input type=number step=0.01 name="price"> €
            <br><input type="submit" value="Creer">
        </form>
        <hr>
        <a href="eventServlet">Ajouter un Evenement</a>
    </body>
</html>
