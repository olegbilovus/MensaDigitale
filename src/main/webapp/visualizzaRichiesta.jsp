<%@page import="java.util.Date"%>
<%@page import="business.richieste.RichiestaBean"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html style="height: auto;width: auto;color: rgb(255,255,255);">

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
    <title>Richiesta</title>
    <link rel="stylesheet" href="assets/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Bitter:400,700">
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Montserrat">
    <link rel="stylesheet" href="assets/fonts/font-awesome.min.css">
    <link rel="stylesheet" href="assets/css/Footer-Basic.css">
    <link rel="stylesheet" href="assets/css/Google-Style-Login.css">
    <link rel="stylesheet" href="assets/css/Header-Dark.css">
    <link rel="stylesheet" href="https://unpkg.com/@bootstrapstudio/bootstrap-better-nav/dist/bootstrap-better-nav.min.css">
    <link rel="stylesheet" href="assets/css/styles.css">
</head>

<body style="background-image: url(&quot;assets/img/food.jpg&quot;);height: auto;width: auto;">
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
    <%
		RichiestaBean r = (RichiestaBean) request.getAttribute("richiesta");
		String cognome = (String) request.getAttribute("rCognome");
		String nome = (String) request.getAttribute("rNome");
		Date data = (Date) request.getAttribute("rData");
		String provincia = (String) request.getAttribute("rProvincia");
		String comune = (String) request.getAttribute("rComune");
		String cf = (String) request.getAttribute("rCodice");
		String cittadinanza = (String) request.getAttribute("rCittadinanza");
		boolean rifugiato = (boolean) request.getAttribute("rRifugiato");
		boolean residenza = (boolean) request.getAttribute("rResidenza");
		String indirizzo = (String) request.getAttribute("rIndirizzo");
		String telefono = (String) request.getAttribute("rTelefono");
		String cellulare = (String) request.getAttribute("rCellulare");
		String email = (String) request.getAttribute("rEmail");
	
		if (r == null) {
	%>
	<h1>Errore: Accesso illegale alla pagina</h1>
	<%
	} else {
	%>
    <div style="margin-top: 150px;background-color: rgba(255,255,255,0.92);">
        <div>
            <h1 class="text-center" style="font-family: Montserrat, sans-serif;margin-top: 0px;padding-top: 10px;">Richiesta per l'attivazione dei servizi di ristorazione</h1>
        </div>
        <h5 class="text-center" style="padding-left: 10px;">Dati Anagrafici</h5>
        <div class="container">
            <div class="row">
                <div class="col-md-6 col_main" style="padding-bottom: 15px;padding-top: 15px;">
                    <div><label style="padding-left: 10px;padding-right: 10px;font-family: Montserrat, sans-serif;">Nome</label><input type="text" value=" <%=nome%>" disabled style="font-family: Montserrat, sans-serif;"></div>
                    <div><label style="padding-left: 10px;padding-right: 10px;font-family: Montserrat, sans-serif;">Cognome</label><input type="text" value="<%=cognome%>" disabled style="font-family: Montserrat, sans-serif;"></div>
                    <div><label style="padding-left: 10px;padding-right: 10px;font-family: Montserrat, sans-serif;">Data di Nascita</label><input type="text" value="<%=data%>" disabled style="font-family: Montserrat, sans-serif;"></div>
                    <div><label style="padding-left: 10px;padding-right: 10px;font-family: Montserrat, sans-serif;">Comune di Nascita</label><input type="text" value="<%=comune%>" disabled style="font-family: Montserrat, sans-serif;"></div>
                </div>
                <div class="col-md-6 col_main" style="padding: 0px;padding-bottom: 15px;padding-top: 15px;padding-right: 15px;padding-left: 15px;">
                    <div><label style="padding-left: 10px;padding-right: 10px;font-family: Montserrat, sans-serif;">Provincia di Nascita</label><input type="text" value="<%=provincia%>" disabled style="font-family: Montserrat, sans-serif;"></div>
                    <div><label style="padding-left: 10px;padding-right: 10px;font-family: Montserrat, sans-serif;">Cittadinanza</label><input type="text" value="<%=cittadinanza%>" disabled style="font-family: Montserrat, sans-serif;"></div>
                    <div><label style="padding-left: 10px;padding-right: 10px;font-family: Montserrat, sans-serif;">Codice Fiscale</label><input type="text" value="<%=cf%>" disabled style="font-family: Montserrat, sans-serif;"></div>
                    <div><label style="padding-left: 10px;padding-right: 10px;font-family: Montserrat, sans-serif;">Studente Apolide o Rifugiato</label><input type="checkbox" disabled <%if (residenza) {%> checked <%}%>></div>
                    <div><label style="padding-left: 10px;padding-right: 10px;font-family: Montserrat, sans-serif;">Residenza dell'Intero Nucleo Familiare</label><input type="checkbox" disabled <%if (residenza) {%> checked <%}%>></div>
                </div>
            </div>
        </div>
        <h5 class="text-center" style="padding-left: 10px;">Residenza</h5>
        <div class="container">
            <div class="row">
                <div class="col-md-6 col_main" style="padding-bottom: 15px;padding-top: 15px;">
                    <div><label style="padding-left: 10px;padding-right: 10px;font-family: Montserrat, sans-serif;">Indirizzo</label><input type="text" value="<%=indirizzo%>" disabled style="font-family: Montserrat, sans-serif;"></div>
                    <div><label style="padding-left: 10px;padding-right: 10px;font-family: Montserrat, sans-serif;">Telefono</label><input type="text" value="<%=telefono%>" disabled style="font-family: Montserrat, sans-serif;"></div>
                </div>
                <div class="col-md-6 col_main" style="padding: 0px;padding-bottom: 15px;padding-top: 15px;padding-right: 15px;padding-left: 15px;">
                    <div></div>
                    <div><label style="padding-left: 10px;padding-right: 10px;font-family: Montserrat, sans-serif;">Cellulare</label><input type="text" value="<%=cellulare%>" disabled style="font-family: Montserrat, sans-serif;"></div>
                    <div><label style="padding-left: 10px;padding-right: 10px;font-family: Montserrat, sans-serif;">Indirizzo Email</label><input type="text" value="<%=email%>" disabled style="font-family: Montserrat, sans-serif;"></div>
                </div>
            </div>
        </div>
    </div>
    <div style="background-color: rgba(255,255,255,0.92);margin-top: 15px;">
        <div class="container">
            <div class="row justify-content-center" style="margin-top: -15px;">
                <div class="col text-center">
                    <div class="text-center" style="margin-top: 20px;">
	                    <form action="<%=response.encodeURL("./ValutaRichiestaServlet")%>" method="post">
	                    	<input type="hidden" name="esito" value="1">
	                    	<input type="hidden" name="idRichiesta" value="<%=r.getId()%>">
                    		<button class="btn btn-success text-center" type="submit" value="Approva" style="margin-right: 15px;">Accetta Domanda</button>
                    	</form>
                	</div>
                </div>
                <div class="col text-center">
                    <div class="text-center" style="margin-top: 20px;">
                    	<form action="<%=response.encodeURL("./ValutaRichiestaServlet")%>" method="post">
                    		<input type="hidden" name="esito" value="2">
                    		<input type="hidden" name="idRichiesta" value="<%=r.getId()%>">
                    		<button class="btn btn-danger text-center" type="submit" value="Rifiuta" style="margin-left: 15px;">Rifiuta Domanda</button>
                		</form>
                	</div>
                </div>
            </div>
        </div>
    </div>
    <div style="height: 150px;"></div>
    <div class="text-center footer-basic" style="margin: 0px;background-color: rgb(20,20,20);">
        <figure class="figure"><img class="img-fluid figure-img" src="assets/img/adisurclogo.png">
            <figcaption class="figure-caption">Agenzia per il Diritto allo Studio della Regione Campania © 2021<br></figcaption>
        </figure>
    </div>
    <script src="assets/js/jquery.min.js"></script>
    <script src="assets/bootstrap/js/bootstrap.min.js"></script>
    <script src="https://unpkg.com/@bootstrapstudio/bootstrap-better-nav/dist/bootstrap-better-nav.min.js"></script>
    <%
	}
	%>
</body>

</html>