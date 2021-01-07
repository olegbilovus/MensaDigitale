<%@ page import="business.prenotazioni.PrenotazioneBean"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%
PrenotazioneBean<String> prenotazione =
    (PrenotazioneBean<String>) request.getSession().getAttribute("prenotazione");
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
    <link rel="stylesheet" href="assets/css/Google-Style-Login.css">
    <link rel="stylesheet" href="assets/css/Header-Dark.css">
    <link rel="stylesheet" href="https://unpkg.com/@bootstrapstudio/bootstrap-better-nav/dist/bootstrap-better-nav.min.css">
    <link rel="stylesheet" href="assets/css/styles.css">
</head>

<body style="background-image: url(&quot;assets/img/food.jpg&quot;);height: auto;width: auto;max-height: none;">
<%
	Boolean error = (Boolean) request.getAttribute("error");
	if (error != null && error) {
	%>
	<h1>error</h1><%
	}
	%>
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
                <form action="<%=response.encodeURL("prenotazione")%>" method="post">
                    <div>
                        <h1 style="font-family: Montserrat, sans-serif;">Scegli Fascia Oraria</h1><select name="fasciaOraria" style="font-family: Montserrat, sans-serif;"><optgroup label="Fasce Oraria"><option value="1" selected="">10:00 - 10:40</option><option value="2">10:40 - 11:20</option><option value="3">11:20 - 12:00</option><option value="4">12:00 - 12:40</option><option value="5">12:40 - 13:20</option></optgroup></select></div>
                    <div>
                        <h1 style="font-family: Montserrat, sans-serif;">Scegli Sala</h1><select name="sala" style="font-family: Montserrat, sans-serif;"><optgroup label="Sala"><option value="1">1</option><option value="2">2</option><option value="3">3</option><option value="4">4</option><option value="5">5</option></optgroup></select></div>
                    <button
                        class="btn btn-primary" type="submit" style="background-color: rgb(66,160,22);">Prenota</button>
                </form>
                </div>
                <div class="col-md-6" style="background-color: rgba(255,255,255,0.92);padding-bottom: 15px;padding-top: 15px;">
                    <div></div><img id="qr" src=<%=response.encodeURL("qrcode?height=500&width=500")%> onerror="this.onerror=null; this.src='assets/img/defaultqrcode.png'">
                    <form action="<%=response.encodeURL("prenotazione")%>" method="get">
							<button class="btn btn-primary" type="submit" style="background-color: #d2453c;">Annulla Prenotazione</button></div>
					</form>
           </div>
        </div>
    </div>
    <div style="min-height: 100px;"></div>
    <div class="text-center footer-basic" style="margin: 0px;background-color: rgb(20,20,20);">
        <figure class="figure"><img class="img-fluid figure-img" src="assets/img/adisurclogo.png">
            <figcaption class="figure-caption">Agenzia per il Diritto allo Studio della Regione Campania © 2021<br></figcaption>
        </figure>
    </div>
    <script src="assets/js/jquery.min.js"></script>
    <script src="assets/bootstrap/js/bootstrap.min.js"></script>
    <script src="https://unpkg.com/@bootstrapstudio/bootstrap-better-nav/dist/bootstrap-better-nav.min.js"></script>
</body>

</html>