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
            <h2>Kies de reeds gevolgde modules</h1>
                <form class="form-horizontal" action="TeVolgenModulesController" role="form">
                    <% for (Module module : vmModuleLijst.getModules()) {
                    %>            
                    <label class="checkbox"><input type="checkbox" name="chkModule" value="<%= module.getClassificatie().getId() %>"><%= module.getNaam()%></label>
                        <% }%>             
                    <button type="submit" class="btn btn-custom-lighten">Volgende</button>
                </form>
        </div>
    </body>
</html>
