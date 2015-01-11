<%@page import="VM.LijstVoorkennisViewModel"%>
<%@page import="VM.LijstClassificatieViewModel"%>
<%@page import="DAL.Modulevoorkennis"%>
<%@page import="DAL.Classificatie"%>

<%
    LijstVoorkennisViewModel vmVoorkennis
            = (LijstVoorkennisViewModel) session.getAttribute("vmVoorkennis");

%>

<%    LijstClassificatieViewModel vmClassificatie
            = (LijstClassificatieViewModel) session.getAttribute("vmClassificatie");

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
            <h2>Vereiste voorkennis</h2>    
            <table class="table table-striped">
                <tr>
                    <th>Classificatie</th>
                    <th>Aktie</th>
                </tr>
                <% for (Modulevoorkennis modulevoorkennis : vmVoorkennis.getLstModulevoorkennis()) {
                %>
                <tr>        
                    <td><%= modulevoorkennis.getClassificatie().getCode() %></td>  
                    <td><a href="VoorkennisVerwijderenController?id=<%= modulevoorkennis.getId()%>" class="glyphicon glyphicon-trash"></a></td>  
                </tr>
                <%  }%>
            </table>
            <form class="form-horizontal" action="VoorkennisToevoegenController" role="form">
                <div>
                    <select name="selectlist" class="selectpicker">
                        <% for (Classificatie classificatie : vmClassificatie.getClassificatie() ) {%>
                        <option value="<%= classificatie.getId() %>"> <%= classificatie.getCode() %> </option>
                        <%}%>
                    </select>
                    <input type="hidden" name="ModuleId" value=<%= request.getSession().getAttribute("ModuleId") %>>                    
                </div>
                <div>
                    <button type="submit" class="btn btn-custom-lighten">Toevoegen</button>
                </div>
            </form>
        </div>
    </body>
</html>
