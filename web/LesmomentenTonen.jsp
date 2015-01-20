<%@page import="DAL.Lesmoment"%>
<%@page import="VM.LijstLesmomentenViewModel"%>

<%
    LijstLesmomentenViewModel vmLesmomenten
            = (LijstLesmomentenViewModel) session.getAttribute("vmLesmomenten");

%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<jsp:include page="TopMenu.html" />
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>CVO Antwerpen - Lessenrooster generator</title>
    </head>
    <body>
        <div class="content">
            <h2>Lesmomenten</h2>    
            <table class="table table-striped">
                <tr>
                    <th>Datum</th>
                    <th>Beginuur</th>
                    <th>Einduur</th>
                    <th>Lokaal</th>
                    <th>Aktie</th>
                </tr>
                <% for (Lesmoment lesmoment : vmLesmomenten.getLesmomenten()) {
                %>
                <tr>        
                    <td><%= lesmoment.getDatum()%></td>
                    <td><%= lesmoment.getBeginuur()%></td>
                    <td><%= lesmoment.getEinduur()%></td>
                    <td><%= lesmoment.getLokaal()%></td>    
                    <td><a href="LesmomentVerwijderenController?id=<%= lesmoment.getId() %>" class="glyphicon glyphicon-trash"></a></td>  
                </tr>
                <%  }%>
            </table>

            <a href="LesmomentToevoegen.jsp" class="btn btn-custom-lighten">Nieuw lesmoment toevoegen</a>
        </div>
    </body>
</html>
