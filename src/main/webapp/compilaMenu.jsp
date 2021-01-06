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
    <link rel="stylesheet" href="assets/fonts/fontawesome-all.min.css">
    <link rel="stylesheet" href="assets/css/Footer-Dark.css">
    <link rel="stylesheet" href="assets/css/Google-Style-Login.css">
    <link rel="stylesheet" href="assets/css/Header-Dark.css">
    <link rel="stylesheet" href="https://unpkg.com/@bootstrapstudio/bootstrap-better-nav/dist/bootstrap-better-nav.min.css">
    <link rel="stylesheet" href="assets/css/Login-Form-Clean.css">
    <link rel="stylesheet" href="assets/css/Login-Form-Dark.css">
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
                    <form action="<%=response.encodeURL("menu")%>" method="post">
        			<%
				            for (PiattoBean piatto : piatti){
				              String nome = piatto.getNome();
				    %>
				        <div class="form-check" style="font-family: Montserrat, sans-serif;"><input class="form-check-input" type="checkbox" id="<%=nome%>"><label class="form-check-label" for="formCheck-1"><strong><%=nome%></strong></label></div>
				        <%}%>
				        <button class="btn btn-primary" type="button">Nuovo Piatto</button>
				        <input name="action" value="aggiungiMenu" style="visibility: hidden">
				        <button class="btn btn-primary" type="button">Nuovo Piatto</button>
				        <input type="submit" value="Invia">
				    </form>
                </div>
                <div class="col-md-6" style="background-color: rgba(255,255,255,0.92);padding-bottom: 15px;padding-top: 15px;">
                    <h1 style="font-family: Montserrat, sans-serif;padding: 15px;">Menù Corrente</h1>
                    <p style="font-family: Montserrat, sans-serif;">Piatti Selezionati</p>
                    <div><label style="font-family: Montserrat, sans-serif;"><strong>Pasta al Pomodoro</strong></label></div>
                    <div><label style="font-family: Montserrat, sans-serif;"><strong>Patate</strong></label></div>
                    <div><label style="font-family: Montserrat, sans-serif;"><strong>Carne al non so cosa</strong></label></div><button class="btn btn-primary" type="button" style="background-color: #42a016;">Conferma Menù</button></div>
        </div>
    </div>
    </div>
    <div class="footer-dark" style="margin-right: 0px;margin-bottom: 0px;margin-left: 0px;padding-bottom: 0px;">
          <footer>
            <div class="container">
                <div class="row">
                    <div class="col-sm-6 col-md-3 item footerNull" id="aziendaFooter">
                        <h3>L'Azienda</h3>
                        <ul>
                            <li><a href="#">Home Page</a></li>
                            <li><a href="#">Servizi On Line</a></li>
                            <li><a href="#">Albo Pretorio On Line</a></li>
                        </ul>
                    </div>
                    <div class="col-sm-6 col-md-3 item footerNull" id="recapitiFooter">
                        <h3>Recapiti e Contatti</h3>
                        <ul>
                            <li><a href="#">Via Alcide de Gasperi n°45</a></li>
                            <li><a href="#">80133 Napoli (NA)</a></li>
                            <li><a href="#">PEC:&nbsp; adisurc@pec.it</a></li>
                            <li><a href="#">Centralino: 081 7603111</a></li>
                            <li><a href="#">P.IVA: 08699411214</a></li>
                        </ul>
                    </div>
                    <div class="col-md-6 item text footerNull" id="logoFooter" style="width: 300px;max-width: 35%;"><img src="assets/img/adisurclogo.png"></div>
                </div>
                <p class="copyright">Azienda al Diritto dello Studio Universitario della Regione Campania © 2021</p>
            </div>
        </footer>
    </div>
    <script src="assets/js/jquery.min.js"></script>
    <script src="assets/bootstrap/js/bootstrap.min.js"></script>
    <script src="https://unpkg.com/@bootstrapstudio/bootstrap-better-nav/dist/bootstrap-better-nav.min.js"></script>
</body>

</html>