<%@ page import="business.utente.Utente" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    Utente utente = (Utente) request.getSession().getAttribute("utente");
    if (utente != null){
      response.sendRedirect(request.getContextPath() + "/index.jsp");
    }
%>
<!DOCTYPE html>
<html style="height: auto;width: auto;">

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
    <title>Login</title>
    <link rel="stylesheet" href="assets/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Bitter:400,700">
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Montserrat">
    <link rel="stylesheet" href="assets/fonts/font-awesome.min.css">
    <link rel="stylesheet" href="assets/css/Footer-Basic.css">
    <link rel="stylesheet" href="assets/css/Google-Style-Login.css">
    <link rel="stylesheet" href="assets/css/Header-Dark.css">
    <link rel="stylesheet" href="https://unpkg.com/@bootstrapstudio/bootstrap-better-nav/dist/bootstrap-better-nav.min.css">
    <link rel="stylesheet" href="assets/css/styles.css">
    
     <script
            src="https://code.jquery.com/jquery-3.5.1.js"
            integrity="sha256-QWo7LDvxbWT2tbbQ97B53yJnYU3WhH/C8ycbRAkjPDc="
            crossorigin="anonymous"></script>
    <script src="https://apis.google.com/js/platform.js" async defer></script>
    <meta name="google-signin-client_id" content="112892589115-l441sfpsdcid969k4b23jva30fs8igod.apps.googleusercontent.com">
        <script>
        function onSignIn(googleUser) {
            // 1. manda richiesta a Google
            // 2. ottiene email da Google
            // 3. imposta il value dell'input name 'email' di 'formGoogle' uguale all'email restituita da Google
            // 4. esegue il submit della form
            let profile = googleUser.getBasicProfile();
            let email = profile.getEmail();
            googleUser.disconnect();
            $("#emailGoogle").val(email);
            $("#formGoogle").submit();
        }
    </script>
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
            <div class="row justify-content-center">
                <div class="col-md-6" style="background-color: rgba(255,255,255,0.92);padding-bottom: 15px;padding-top: 15px;max-width: 95%;width: 357px;">
                    <div>
                        <h1 class="text-center" style="font-family: Montserrat, sans-serif;">Accedi con Google</h1>
                        <div class="text-center"></div>
                    </div>
                    <div class="row justify-content-center">
                    	 <%-- Login con Google --%>
							  <form id="formGoogle" action="<%=response.encodeURL("login")%>" method="post">
							      <input id="emailGoogle" name="email" value="" style="visibility: hidden">
							      <input name="action" value="loginGoogle" style="visibility: hidden">
							      <div class="g-signin2 row justify-content-center" data-long-title="true" data-width="300" data-onsuccess="onSignIn"></div>
							  </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div style="height: 390px;"></div>
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