<%@page import="java.util.Calendar"%>
<%@page import="DAL.Module"%>
<%@page import="DAL.Lesmoment"%>
<%@page import="VM.LijstModulesViewModel"%>
<%@page import="VM.LijstLesmomentenViewModel"%>

<%
    LijstModulesViewModel vmLstModulesFinal
            = (LijstModulesViewModel) session.getAttribute("vmLstModulesFinal");

%>

<%    LijstLesmomentenViewModel vmLesmomentenFinal
            = (LijstLesmomentenViewModel) session.getAttribute("vmLesmomentenFinal");

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
            <h2>Volgende modules werden voor u geselecteerd.</h2>
            <% for (Module m : vmLstModulesFinal.getModules()) {%>

            <p><%= m.getCode() + " " + m.getNaam()%></p>

            <% } %>

            <h2>Lessenrooster</h2>

            <table class="table table-striped">
                <tr>
                    <th>Datum</th>
                    <th>Dag</th>                    
                    <th>Beginuur</th>
                    <th>Einduur</th>
                    <th>Lokaal</th>
                    <th>Code</th>
                    <th>Module</th>
                </tr>

                <% for (Lesmoment l : vmLesmomentenFinal.getLesmomenten()) {%>

                <tr>
                    <td><%= l.getDatum()%></td>
                    <td>
                        <%

                                    Calendar c = Calendar.getInstance();
                                    c.setTime(l.getDatum());
                                    int dag = c.get(Calendar.DAY_OF_WEEK);

                                    String strDag = "";

                                    switch (dag) {
                                        case 0:
                                            strDag = "Zondag";
                                            break;
                                        case 1:
                                            strDag = "Maandag";
                                            break;
                                        case 2:
                                            strDag = "Dinsdag";
                                            break;
                                        case 3:
                                            strDag = "Woensdag";
                                            break;
                                        case 4:
                                            strDag = "Donderdag";
                                            break;
                                        case 5:
                                            strDag = "Vrijdag";
                                            break;
                                        case 6:
                                            strDag = "Zaterdag";
                                            break;
                                    }
                                    
                                    pageContext.setAttribute("strDag", strDag);
                        %>
                        <%= pageContext.getAttribute("strDag") %>
                    </td>
                    <td><%= l.getBeginuur()%></td>
                    <td><%= l.getEinduur()%></td>
                    <td><%= l.getLokaal()%></td>
                    <td><%= l.getModule().getCode()%></td>
                    <td><%= l.getModule().getNaam()%></td>
                </tr>

                <% }%>

            </table>

        </div>
    </body>
</html>
