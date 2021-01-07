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
<html style="height: 100%;width: 100%;">

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

<body style="height: auto;width: auto;background-image: url(&quot;assets/img/food.jpg&quot;);">
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
    <div id="wrap" style="margin-top: 150px;">
        <div class="container">
            <div class="row">
                <div class="col-md-6 col_main" style="background-color: rgba(255,255,255,0.92);padding-bottom: 15px;padding-top: 15px;">
                 <form method="post" action="<%=response.encodeURL("valutazione")%>">
                    <div>
                        <h1 style="font-family: Montserrat, sans-serif;">Piatti del Giorno</h1><select name="piatto" style="font-family: Montserrat, sans-serif;"><optgroup label="Primi del Giorno"><option value="pasta al pomodoro">pasta al pomodoro</option><option value="pasta in bianco">pasta in bianco</option><option value="carbonara">carbonara</option></optgroup><optgroup label="Secondi del Giorno"><option value="mozzarella">mozzarella</option><option value="carne arrosto">carne arrosto</option><option value="cordon bleu">cordon bleu</option></optgroup><optgroup label="Contorni del Giorno"><option value="patate al forno">patate al forno</option><option value="patate fritte">patate fritte</option><option value="insalata">insalata</option></optgroup></select></div>
                    <div>
                        <h2 style="font-family: Montserrat, sans-serif;">Valutazione</h2>
                        <div class="form-check"><input class="form-check-input" type="checkbox" id="formCheck-1" name="valutazione" value="1"><label class="form-check-label" for="formCheck-1">★☆☆☆☆</label></div>
                        <div class="form-check"><input class="form-check-input" type="checkbox" id="formCheck-1" name="valutazione" value="2"><label class="form-check-label" for="formCheck-1">★★☆☆☆<br></label></div>
                        <div class="form-check"><input class="form-check-input" type="checkbox" id="formCheck-1" name="valutazione" value="3"><label class="form-check-label" for="formCheck-1">★★★☆☆<br></label></div>
                        <div class="form-check"><input class="form-check-input" type="checkbox" id="formCheck-1" name="valutazione" value="4"><label class="form-check-label" for="formCheck-1">★★★★☆<br></label></div>
                        <div class="form-check"><input class="form-check-input" type="checkbox" id="formCheck-1" name="valutazione" value="5"><label class="form-check-label" for="formCheck-1">★★★★★<br></label></div>
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
    <div style="min-height: 310px;"></div>
    <div class="text-center footer-basic" style="margin: 0px;background-color: rgb(20,20,20);margin-top: 0px;">
        <figure class="figure"><img class="img-fluid figure-img" src="assets/img/adisurclogo.png">
            <figcaption class="figure-caption">Agenzia per il Diritto allo Studio della Regione Campania © 2021<br></figcaption>
        </figure>
    </div>
    <script src="assets/js/jquery.min.js"></script>
    <script src="assets/bootstrap/js/bootstrap.min.js"></script>
    <script src="https://unpkg.com/@bootstrapstudio/bootstrap-better-nav/dist/bootstrap-better-nav.min.js"></script>
</body>

</html>