<%@page import="DAL.Module"%>
<%@page import="VM.LijstModulesViewModel"%>

<%
    LijstModulesViewModel vmModuleLijst
            = (LijstModulesViewModel) session.getAttribute("vmModuleLijst");

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
            <h2>Modules</h2>    
            <table class="table table-striped">
                <tr>
                    <th>Code</th>
                    <th>Naam</th>
                    <th>Begindatum</th>
                    <th>Einddatum</th>
                    <th>Classificatie</th>
                    <th>Voorkennis</th> 
                    <th>Lesmomenten</th>                     
                    <th>Aktie</th>
                </tr>
                <% for (Module module : vmModuleLijst.getModules()) {
                %>
                <tr>            
                    <td><%= module.getCode()%></td>
                    <td><%= module.getNaam()%></td>
                    <td><%= module.getBegindatum()%></td>
                    <td><%= module.getEinddatum()%></td>
                    <td><%= module.getClassificatie().getCode() %></td>      
                    <td><a href="VoorkennisLijstController?id=<%= module.getId() %>" class="glyphicon glyphicon-pencil"></a></td> 
                    <td><a href="LesmomentLijstController?id=<%= module.getId() %>" class="glyphicon glyphicon-pencil"></a></td> 
                    <td><a href="ModuleVerwijderen?id=<%= module.getId() %>" class="glyphicon glyphicon-trash"></a></td>       
                </tr>
                <%  }%>
            </table>
            <a href="ModuleToevoegen.jsp" class="btn btn-custom-lighten">Nieuwe module toevoegen</a>
        </div>            
    </body>
</html>
