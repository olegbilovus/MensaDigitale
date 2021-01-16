<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="business.consumatore.ConsumatoreBean" %>
<%
	ConsumatoreBean consumer = (ConsumatoreBean) request.getSession().getAttribute("utente");
	String nome = consumer.getNome();
	String cognome = consumer.getCognome();
	String data = consumer.getDataDiNascita().toString();
	float saldo = consumer.getSaldo();
	int fascia = consumer.getFasciaPagamento();	
%>
<!DOCTYPE html>
<html style="height: auto;width: auto;">

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
    <title>Area Utente</title>
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

<body style="background-image: url(&quot;assets/img/food.jpg&quot;);height: auto;width: auto;max-height: none;">
    <jsp:include page="navbar.jsp" />
    <div style="margin-top: 150px;background-color: rgba(255,255,255,0.92);margin-bottom: 0px;">
        <div class="container">
            <div class="row">
                <div class="col-md-12" style="background-color: rgba(255,255,255,0);">
                    <h1 class="text-center" style="font-family: Montserrat, sans-serif;margin-top: 15px;">Bentornato <%=nome %></h1>
                </div>
            </div>
        </div>
    </div>
    <div style="background-color: rgba(255,255,255,0.92);margin-bottom: 0px;margin-top: -10px;">
        <div class="container">
            <div class="row">
                <div class="col-md-6 text-center align-self-center">
                    <div style="width: 100%;"><img src="assets/img/avatar_2x.png"></div>
                </div>
                <div class="col-md-6 align-self-center">
                    <div style="width: 50%;">
                        <p style="width: 100%%;font-family: Montserrat, sans-serif; font-size:30;font-weight:bold;"><%=nome %> <%=cognome %><br><%=data %><br><%=fascia %></p>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div style="background-color: rgba(255,255,255,0.92);margin-top: -10px;">
        <div class="container">
            <div class="row">
                <div class="col-md-12">
                    <h1 class="text-center" style="font-family: Montserrat, sans-serif;margin-top: 15px;">Saldo Corrente: <%=saldo %></h1>
                    <div class="text-center" style="margin-top: 10px;"><a href="./Ricarica.jsp"><button class="btn btn-success text-center"  type="button" style="margin-bottom: 10px;">Ricarica Saldo</button></a></div>
                </div>
            </div>
        </div>
    </div>
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