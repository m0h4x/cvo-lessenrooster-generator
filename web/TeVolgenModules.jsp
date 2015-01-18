<%@page import="DAL.Module"%>
<%@page import="VM.LijstModulesViewModel"%>

<%
    LijstModulesViewModel vm
            = (LijstModulesViewModel) session.getAttribute("ViewModel");

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
            <h2>Kies de modules die je graag zou willen volgen</h1>
                <form class="form-horizontal" action="WeekdagenController" role="form">
                    <% for (Module module : vm.getModules()) {
                    %>            
                    <label class="checkbox"><input type="checkbox" name="chkModule" value="<%= module.getId() %>"><%= module.getNaam()%></label>
                        <% }%>             
                    <button Type="button" onClick="history.go(-1);return true;" class="btn btn-custom-lighten">Vorige</button>  
                    <button type="submit" class="btn btn-custom-lighten">Volgende</button>
                </form>
        </div>
    </body>
</html>
