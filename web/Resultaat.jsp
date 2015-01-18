<%@page import="DAL.Module"%>
<%@page import="VM.LijstModulesViewModel"%>

<%
    LijstModulesViewModel vmLstModulesDieWelKunnenWordenGevolgd
            = (LijstModulesViewModel) session.getAttribute("vmLstModulesDieWelKunnenWordenGevolgd");

%>

<%    LijstModulesViewModel vmLstModulesDieNietKunnenWordenGevolgd
            = (LijstModulesViewModel) session.getAttribute("vmLstModulesDieNietKunnenWordenGevolgd");

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
            <% if (!vmLstModulesDieNietKunnenWordenGevolgd.getModules().isEmpty()) { %>
            <h2>Opgelet, onderstaande module(s) werden gekozen, maar bevatten lesmomenten op dagen die je niet hebt geselecteerd. 
                Ze worden dus niet gebruikt om een lessenrooster te genereren!</h2>
            <table class="table">
                <% for (Module module : vmLstModulesDieNietKunnenWordenGevolgd.getModules()) {
                %>
                <tr>            
                    <td width="20%"><%= module.getCode()%></td>
                    <td><%= module.getNaam()%></td>    
                </tr>
                <%  }%>
            </table>
            <% }%>

            <h2>Onderstaande modules worden gebruikt om een lessenrooster te genereren.</h2>
            <table class="table">
                <% for (Module module : vmLstModulesDieWelKunnenWordenGevolgd.getModules()) {
                %>
                <tr>            
                    <td width="20%"><%= module.getCode()%></td>
                    <td><%= module.getNaam()%></td>    
                </tr>
                <%  }%>
            </table>
            
            
            
        </div>
    </body>
</html>
