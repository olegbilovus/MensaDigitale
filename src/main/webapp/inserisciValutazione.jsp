<%@ page import="business.utente.Utente" %>
<%@ page import="business.consumatore.ConsumatoreBean" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    //Utente utente = (Utente) request.getSession().getAttribute("utente");
    //if (utente == null || !Utente.class.isInstance(utente)){
        // qua non ci puo' stare. TODO cambiare modo di fare il check (non riguarda i ragazzi del front-end)
        //response.sendRedirect(request.getContextPath() + "/index.jsp");
        //return;
    //}
%>
<!DOCTYPE html>
<html style="height: auto;width: auto;">

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
    <title>Inserisci la tua valutazione</title>
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
    <div style="margin-top: 150px;">
        <div class="container">
            <div class="row">
                <div class="col-md-6 col_main" style="background-color: rgba(255,255,255,0.92);padding-bottom: 15px;padding-top: 15px;">
                    <div>
                        <h1 style="font-family: Montserrat, sans-serif;">Piatti del Giorno</h1><select style="font-family: Montserrat, sans-serif;"><optgroup label="Primi del Giorno"><option value="pasta al pomodoro">pasta al pomodoro</option><option value="pasta in bianco">pasta in bianco</option><option value="carbonara">carbonara</option></optgroup><optgroup label="Secondi del Giorno"><option value="mozzarella">mozzarella</option><option value="carne arrosto">carne arrosto</option><option value="cordon bleu">cordon bleu</option></optgroup><optgroup label="Contorni del Giorno"><option value="patate al forno">patate al forno</option><option value="patate fritte">patate fritte</option><option value="insalata">insalata</option></optgroup></select></div>
                    <div>
                        <h2 style="font-family: Montserrat, sans-serif;">Valutazione</h2>
                        <form method="post" action="<%=response.encodeURL("valutazione")%>">
                        <div class="form-check"><input class="form-check-input" type="checkbox" id="formCheck-1" value="1" name="1stella"><label class="form-check-label" for="formCheck-1">★☆☆☆☆</label></div>
                        <div class="form-check"><input class="form-check-input" type="checkbox" id="formCheck-1" name="2stelle" value="2"><label class="form-check-label" for="formCheck-1">★★☆☆☆<br></label></div>
                        <div class="form-check"><input class="form-check-input" type="checkbox" id="formCheck-1" name="3stelle" value="3"><label class="form-check-label" for="formCheck-1">★★★☆☆<br></label></div>
                        <div class="form-check"><input class="form-check-input" type="checkbox" id="formCheck-1" name="4stelle" value="4"><label class="form-check-label" for="formCheck-1">★★★★☆<br></label></div>
                        <div class="form-check"><input class="form-check-input" type="checkbox" id="formCheck-1" name="5stelle" value="5"><label class="form-check-label" for="formCheck-1">★★★★★<br></label></div>
                    </div><button class="btn btn-primary" type="submit" style="background-color: rgb(66,160,22);">Invia Valutazione</button></div>
                    </form>
                <div class="col-md-6 col_main" style="background-color: rgba(255,255,255,0.92);padding: 0px;padding-bottom: 15px;padding-top: 15px;">
                    <div>
                        <h1 style="font-family: Montserrat, sans-serif;">I Tuoi Voti</h1>
                    </div>
                    <div class="table-responsive">
                        <table class="table">
                            <thead>
                                <tr>
                                    <th>Piatto</th>
                                    <th>Valutazione</th>
                                    <th class="data-tabella">Data</th>
                                    <th></th>
                                </tr>
                            </thead>
                            <tbody>
                                <tr>
                                    <td>carbonara</td>
                                    <td>★☆☆☆☆<br></td>
                                    <td class="data-tabella">23/02/2021</td>
                                    <td><button class="btn btn-primary" type="button" style="background-color: #d2453c;width: 30px;height: 30px;font-family: Montserrat, sans-serif;font-size: 15px;padding: 0px;">🗑</button></td>
                                    <td><button class="btn btn-primary text-center" type="button" style="background-color: #e87f33;width: 30px;height: 30px;font-family: Montserrat, sans-serif;font-size: 17px;padding: 0px;">🔧</button></td>
                                </tr>
                                <tr>
                                    <td>patate fritte</td>
                                    <td>★★★☆☆<br></td>
                                    <td class="data-tabella">23/02/2021</td>
                                    <td><button class="btn btn-primary" type="button" style="background-color: #d2453c;width: 30px;height: 30px;font-family: Montserrat, sans-serif;font-size: 15px;padding: 0px;">🗑</button></td>
                                    <td><button class="btn btn-primary text-center" type="button" style="background-color: #e87f33;width: 30px;height: 30px;font-family: Montserrat, sans-serif;font-size: 17px;padding: 0px;">🔧</button></td>
                                </tr>
                            </tbody>
                        </table>
                    </div>
                </div>
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
