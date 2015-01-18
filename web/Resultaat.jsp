<%@page import="DAL.Module"%>
<%@page import="VM.LijstModulesViewModel"%>

<%
    LijstModulesViewModel vmLstModulesDieWelKunnenWordenGevolgd
            = (LijstModulesViewModel) session.getAttribute("vmLstModulesDieWelKunnenWordenGevolgd");

%>

<%    LijstModulesViewModel vmLstModulesDieNietKunnenWordenGevolgd
            = (LijstModulesViewModel) session.getAttribute("vmLstModulesDieNietKunnenWordenGevolgd");

%>

<%    LijstModulesViewModel lstModulesFinal
            = (LijstModulesViewModel) session.getAttribute("lstModulesFinal");

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
            <h2>Onderstaande modules werden weggefilterd omdat ze niet op de juiste dagen worden gegeven. 
                Ze worden dus niet gebruikt om een lessenrooster te genereren!</h2>
            <table class="table">
                <% for (Module module : vmLstModulesDieNietKunnenWordenGevolgd.getModules()) {
                %>
                <tr>            
                    <td width="10%"><%= module.getCode()%></td>
                    <td><%= module.getNaam()%></td>    
                </tr>
                <%  }%>
            </table>
            <% }%>

            <h2>Onderstaande modules werden gebruikt om een lessenrooster te genereren.</h2>
            <table class="table">
                <% for (Module module : vmLstModulesDieWelKunnenWordenGevolgd.getModules()) {
                %>
                <tr>        
                    <td width="10%"><%= module.getCode()%></td>
                    <td><%= module.getNaam()%></td>    
                </tr>
                <%  }%>
            </table>  
            <h2>Resultaat</h2>


            <h2>Final</h2>
            <table class="table">
                <% for (Module module : lstModulesFinal.getModules()) {
                %>
                <tr>        
                    <td width="10%"><%= module.getCode()%></td>
                    <td><%= module.getNaam()%></td>    
                </tr>
                <%  }%>
            </table>  
            <h2>Resultaat</h2>
        </div>
    </body>
</html>
