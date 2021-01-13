<%@ page import="java.util.Collection" %>
<%@ page import="business.piatti.PiattoBean" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
        Collection<PiattoBean> piatti = (Collection<PiattoBean>) request.getAttribute("tuttiPiatti");
        String error = (String) request.getAttribute("error");
        if (piatti == null && error == null) {
            /*vuol dire che fin'ora non siamo mai passati per la Servlet*/
            response.sendRedirect("piatto?action=getTuttiPiatti&destination=compilaMenu.jsp");
            return;
        }
%>
<!DOCTYPE html>
<html style="height: auto;width: auto;">

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
    <title>MD_Login</title>
    <link rel="stylesheet" href="assets/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Bitter:400,700">
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Montserrat">
    <link rel="stylesheet" href="assets/css/Footer-Basic.css">
    <link rel="stylesheet" href="https://unpkg.com/@bootstrapstudio/bootstrap-better-nav/dist/bootstrap-better-nav.min.css">
    <link rel="stylesheet" href="assets/css/styles.css">
</head>

<body style="background-image: url(&quot;assets/img/food.jpg&quot;);height: auto;width: auto;max-height: none;">
    <nav class="navbar navbar-light fixed-top text-dark" style="background-color: #FF9900;">
        <div class="container-fluid"><img class="img-fluid" src="assets/img/Logo.png" style="width: 255px;padding: 0px;margin: -29px;"><button data-toggle="collapse" class="navbar-toggler" data-target="#navcol-1"><span class="sr-only">Toggle navigation</span><span class="navbar-toggler-icon"></span></button>
            <div
                class="collapse navbar-collapse text-right float-right" id="navcol-1">
                <ul class="nav navbar-nav mx-auto">
                    <li class="nav-item" role="presentation"><a class="nav-link active" href="#">Login</a></li>
                    <li class="nav-item" role="presentation"><a class="nav-link active" href="#">Consulta Menù</a></li>
                    <li class="nav-item" role="presentation"><a class="nav-link active" href="#">Inserisci Menù</a></li>
                    <li class="nav-item" role="presentation"><a class="nav-link active" href="#">Valuta Servizio</a></li>
                    <li class="nav-item" role="presentation"><a class="nav-link active" href="#">Prenotazione</a></li>
                    <li class="nav-item" role="presentation"><a class="nav-link active" href="#">Servizi Ristorazione</a></li>
                </ul>
                <ul class="nav navbar-nav">
                    <li class="nav-item" role="presentation"></li>
                </ul>
        </div>
        </div>
    </nav>
    <div style="margin-top: 150px;">
        <div class="container">
            <div class="row">
                <div class="col-md-6" style="background-color: rgba(255,255,255,0.92);padding-bottom: 15px;padding-top: 15px;">
                    <h1 style="font-family: Montserrat, sans-serif;">Database</h1>
                    <p style="font-family: Montserrat, sans-serif;">Seleziona i piatti da inserire nel menù</p>
                    <%
				            for (PiattoBean piatto : piatti){
				              String nome = piatto.getNome();
				    %>
                    <div class="form-check" style="font-family: Montserrat, sans-serif;"><input class="form-check-input" type="checkbox" id="<%=nome%>"><label class="form-check-label" for="formCheck-1"><strong><%=nome%></strong></label></div>
                    <%}%>
                    <button class="btn btn-primary" type="button">Nuovo Piatto</button></div>
                <div
                    class="col-md-6" style="background-color: rgba(255,255,255,0.92);padding-bottom: 15px;padding-top: 15px;">
                    <h1 style="font-family: Montserrat, sans-serif;padding: 15px;">Menù Corrente</h1>
                    <p style="font-family: Montserrat, sans-serif;">Piatti Selezionati</p>
                    <form action="<%=response.encodeURL("menu")%>" method="post">
	                    <div><label style="font-family: Montserrat, sans-serif;"><strong>Pasta al Pomodoro</strong></label></div>
	                    <div><label style="font-family: Montserrat, sans-serif;"><strong>Patate</strong></label></div>
	                    <div><label style="font-family: Montserrat, sans-serif;"><strong>Carne al non so cosa</strong></label></div>
	                    <button class="btn btn-primary" type="button" style="background-color: #42a016;">Conferma Menù</button></div>
                    </form>
        </div>
    </div>
    </div>
    <div style="min-height: 300px;"></div>
    <div class="text-center footer-basic" style="margin: 0px;background-color: rgb(20,20,20);margin-top: 100px;">
        <figure class="figure"><img class="img-fluid figure-img" src="assets/img/adisurclogo.png">
            <figcaption class="figure-caption">Agenzia per il Diritto allo Studio della Regione Campania © 2021<br></figcaption>
        </figure>
    </div>
    <script src="assets/js/jquery.min.js"></script>
    <script src="assets/bootstrap/js/bootstrap.min.js"></script>
    <script src="https://unpkg.com/@bootstrapstudio/bootstrap-better-nav/dist/bootstrap-better-nav.min.js"></script>
</body>

</html>