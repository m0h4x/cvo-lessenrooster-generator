<%-- 
    Document   : LesmomentToevoegen
    Created on : 22-Dec-2014, 19:55:05
    Author     : Wim
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<jsp:include page="TopMenu.html" />
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

        <link rel="stylesheet" href="//code.jquery.com/ui/1.11.2/themes/smoothness/jquery-ui.css">
        <script src="//code.jquery.com/jquery-1.10.2.js"></script>
        <script src="//code.jquery.com/ui/1.11.2/jquery-ui.js"></script>
        <link rel="stylesheet" href="/resources/demos/style.css">
        <script>
            $(function () {
                $("#datum").datepicker({dateFormat: "dd-mm-yy"}).val();
            });
        </script>

        <title>CVO Antwerpen - Lessenrooster generator</title>
    </head>
    <body>
        <div class="content">
            <form class="form-horizontal" action="LesmomentToevoegenController" role="form">
                <div class="form-group">
                    <label for="Datum" class="col-sm-2 control-label">Datum</label>
                    <div class="col-sm-10">
                        <input type="text" name="Datum" class="form-control" id="datum" placeholder="dd-mm-yyyy">
                    </div>
                </div>
                <div class="form-group">
                    <label for="Beginuur" class="col-sm-2 control-label">Beginuur</label>
                    <div class="col-sm-10">
                        <input type="text" name="Beginuur" class="form-control" id="Beginuur" placeholder="hh:mm">
                    </div>
                </div>
                <div class="form-group">
                    <label for="Einduur" class="col-sm-2 control-label">Einduur</label>
                    <div class="col-sm-10">
                        <input type="text" name="Einduur" class="form-control" id="Einduur" placeholder="hh:mm">
                    </div>
                </div>
                <div class="form-group">
                    <label for="Lokaal" class="col-sm-2 control-label">Lokaal</label>
                    <div class="col-sm-10">
                        <input type="text" name="Lokaal" class="form-control" id="Lokaal" placeholder="Lokaal">
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-sm-offset-2 col-sm-10">
                        <button type="submit" class="btn btn-default">Opslaan</button>
                    </div>
                </div>
                <input type="hidden" name="ModuleId" value=<%= request.getSession().getAttribute("ModuleId")%>>
            </form>
        </div>
    </body>
</html>
