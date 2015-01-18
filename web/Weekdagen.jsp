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
                <form class="form-horizontal" action="GenereerRoosterController" role="form">
                    <label class="checkbox"><input type="checkbox" name="chkWeekdagen" value="Maandag">Maandag</label>
                    <label class="checkbox"><input type="checkbox" name="chkWeekdagen" value="Dinsdag">Dinsdag</label>
                    <label class="checkbox"><input type="checkbox" name="chkWeekdagen" value="Woensdag">Woensdag</label>
                    <label class="checkbox"><input type="checkbox" name="chkWeekdagen" value="Donderdag">Donderdag</label>
                    <label class="checkbox"><input type="checkbox" name="chkWeekdagen" value="Vrijdag">Vrijdag</label>
                    <% for (Module module : vm.getModules()) {
                    %>            
                    <input type="hidden" name="Modules" value=<%= module.getId() %>>  
                        <% }%>       
                    <button Type="button" onClick="history.go(-1);return true;" class="btn btn-custom-lighten">Vorige</button>  
                    <button type="submit" class="btn btn-custom-lighten">Volgende</button>
                </form>
        </div>
    </body>
</html>
