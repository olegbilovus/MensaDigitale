<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html style="height: auto;width: auto;color: rgb(255,255,255);">

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
    <div style="margin-top: 150px;background-color: rgba(255,255,255,0.92);">
        <div>
            <h1 class="text-center" style="font-family: Montserrat, sans-serif;margin-top: 0px;padding-top: 10px;">Richiesta per l'attivazione dei servizi di ristorazione</h1>
        </div>
        <h5 class="text-center" style="padding-left: 10px;">Dati Anagrafici</h5>
        <div class="container">
            <div class="row">
                <div class="col-md-6 col_main" style="background-color: rgba(255,255,255,0.92);padding-bottom: 15px;padding-top: 15px;">
                <form  method="post"
		action="<%=response.encodeURL("./SubmitRichiestaServlet")%>">
                    <div><label style="padding-left: 10px;padding-right: 10px;font-family: Montserrat, sans-serif;">Nome</label><input type="text" style="font-family: Montserrat, sans-serif;"></div>
                    <div><label style="padding-left: 10px;padding-right: 10px;font-family: Montserrat, sans-serif;">Cognome</label><input type="text" style="font-family: Montserrat, sans-serif;"></div>
                    <div><label style="padding-left: 10px;padding-right: 10px;font-family: Montserrat, sans-serif;">Data di Nascita</label><input type="text" style="font-family: Montserrat, sans-serif;"></div>
                    <div><label style="padding-left: 10px;padding-right: 10px;font-family: Montserrat, sans-serif;">Comune di Nascita</label><input type="text" style="font-family: Montserrat, sans-serif;"></div>
                </div>
                <div class="col-md-6 col_main" style="background-color: rgba(255,255,255,0.92);padding: 0px;padding-bottom: 15px;padding-top: 15px;padding-right: 15px;padding-left: 15px;">
                    <div><label style="padding-left: 10px;padding-right: 10px;font-family: Montserrat, sans-serif;">Provincia di Nascita</label><input type="text" style="font-family: Montserrat, sans-serif;"></div>
                    <div><label style="padding-left: 10px;padding-right: 10px;font-family: Montserrat, sans-serif;">Cittadinanza</label><input type="text" style="font-family: Montserrat, sans-serif;"></div>
                    <div><label style="padding-left: 10px;padding-right: 10px;font-family: Montserrat, sans-serif;">Codice Fiscale</label><input type="text" style="font-family: Montserrat, sans-serif;"></div>
                    <div><label style="padding-left: 10px;padding-right: 10px;font-family: Montserrat, sans-serif;">Studente Apolide o Rifugiato</label>
                        <div class="form-check"><input class="form-check-input" type="checkbox" id="formCheck-1"><label class="form-check-label" for="formCheck-1">Si</label></div>
                        <div class="form-check"><input class="form-check-input" type="checkbox" id="formCheck-2"><label class="form-check-label" for="formCheck-2">No</label></div>
                    </div>
                    <div><label style="padding-left: 10px;padding-right: 10px;font-family: Montserrat, sans-serif;">Residenza dell'Intero Nucleo Familiare</label>
                        <div class="form-check"><input class="form-check-input" type="checkbox" id="formCheck-1"><label class="form-check-label" for="formCheck-1">Si</label></div>
                        <div class="form-check"><input class="form-check-input" type="checkbox" id="formCheck-2"><label class="form-check-label" for="formCheck-2">No</label></div>
                    </div>
                </div>
            </div>
        </div>
        <h5 class="text-center" style="padding-left: 10px;">Residenza</h5>
        <div class="container">
            <div class="row">
                <div class="col-md-6 col_main" style="background-color: rgba(255,255,255,0.92);padding-bottom: 15px;padding-top: 15px;">
                    <div><label style="padding-left: 10px;padding-right: 10px;font-family: Montserrat, sans-serif;">Indirizzo</label><input type="text" style="font-family: Montserrat, sans-serif;"></div>
                    <div><label style="padding-left: 10px;padding-right: 10px;font-family: Montserrat, sans-serif;">Comune</label><input type="text" style="font-family: Montserrat, sans-serif;"></div>
                    <div><label style="padding-left: 10px;padding-right: 10px;font-family: Montserrat, sans-serif;">Provincia</label><input type="text" style="font-family: Montserrat, sans-serif;"></div>
                    <div><label style="padding-left: 10px;padding-right: 10px;font-family: Montserrat, sans-serif;">CAP</label><input type="text" style="font-family: Montserrat, sans-serif;"></div>
                </div>
                <div class="col-md-6 col_main" style="background-color: rgba(255,255,255,0.92);padding: 0px;padding-bottom: 15px;padding-top: 15px;padding-right: 15px;padding-left: 15px;">
                    <div><label style="padding-left: 10px;padding-right: 10px;font-family: Montserrat, sans-serif;">Telefono</label><input type="text" style="font-family: Montserrat, sans-serif;"></div>
                    <div><label style="padding-left: 10px;padding-right: 10px;font-family: Montserrat, sans-serif;">Cellulare</label><input type="text" style="font-family: Montserrat, sans-serif;"></div>
                    <div><label style="padding-left: 10px;padding-right: 10px;font-family: Montserrat, sans-serif;">Indirizzo Email</label><input type="text" style="font-family: Montserrat, sans-serif;"></div>
                    <div><label style="padding-left: 10px;padding-right: 10px;font-family: Montserrat, sans-serif;">Conferma Indirizzo Email</label><input type="text" style="font-family: Montserrat, sans-serif;"></div>
                </div>
            </div>
        </div>
        <h5 class="text-center" style="padding-left: 10px;">Presentazione dati relativi alla condizione economica e al merito</h5>
        <div class="container">
            <div class="row">
                <div class="col-md-6 col_main" style="background-color: rgba(255,255,255,0.92);padding-bottom: 15px;padding-top: 15px;">
                    <p class="text-break" id="datiRelativi">AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA</p>
                    <div
                        class="form-check"><input class="form-check-input" type="checkbox" id="formCheck-3"><label class="form-check-label" for="formCheck-3">Acconsento</label></div>
            </div>
        </div>
    </div>
    </div>
    <div style="background-color: rgba(255,255,255,0.92);">
        <div class="container">
            <div class="row justify-content-center" style="margin-top: -15px;">
                <div class="col">
                    <h5 class="text-center" style="padding-left: 10px;">Dichiarazione</h5>
                </div>
                <div class="col-md-12" style="background-color: rgba(255,255,255,0.92);">
                    <p class="text-break" id="datiRelativi">AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA</p>
                    <div
                        class="form-check"><input class="form-check-input" type="checkbox" id="formCheck-3"><label class="form-check-label" for="formCheck-3">Acconsento</label></div>
            </div>
            <div class="col text-center"><input type="submit" class="btn btn-success text-center">Invia Domanda</button></div>
            </form>
        </div>
    </div>
    </div>
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
