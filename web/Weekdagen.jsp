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
        <title>CVO Antwerpen - Lessenrooster generator2</title>
    </head>
    <body>
        <div class="content">
            <h2>Op welke dagen kan je les volgen ?</h1>
                <form class="form-horizontal" action=" " role="form">
                    <label class="checkbox"><input type="checkbox" name="chkMaandag" value="1">Maandag</label>
                    <label class="checkbox"><input type="checkbox" name="chkDinsdag" value="1">Dinsdag</label>
                    <label class="checkbox"><input type="checkbox" name="chkWoensdag" value="1">Woensdag</label>
                    <label class="checkbox"><input type="checkbox" name="chkDonderdag" value="1">Donderdag</label>
                    <label class="checkbox"><input type="checkbox" name="chkVrijdag" value="1">Vrijdag</label>
                    <% for (Module module : vm.getModules()) {
                    %>            
                    <input type="hidden" name="Modules" value=<%= module.getId() %>>  
                        <% }%>             
                    <button type="submit" class="btn btn-custom-lighten">Volgende</button>
                </form>
        </div>
    </body>
</html>
