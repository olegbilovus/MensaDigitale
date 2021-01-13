<%@page import="java.util.HashMap"%>
<%@page import="storage.manager.FasciaOrariaDao"%>
<%@page import="business.prenotazioni.FasciaOrariaBean"%>
<%@page import="java.util.Collection"%>
<%@ page import="business.prenotazioni.PrenotazioneBean"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%
PrenotazioneBean<String> prenotazione = (PrenotazioneBean<String>) request.getSession().getAttribute("prenotazione");

FasciaOrariaDao fasceDao = new FasciaOrariaDao();

HashMap<Integer, HashMap<Integer, Boolean>> saleDisponibili = (HashMap<Integer, HashMap<Integer, Boolean>>) request
		.getServletContext().getAttribute("saleDisponibili");
boolean[] saleD = new boolean[5];
%>


<!DOCTYPE html>
<html style="height: auto; width: auto;">

<head>
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
<title>Prenotazioni</title>
<link rel="stylesheet" href="assets/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet"
	href="https://fonts.googleapis.com/css?family=Bitter:400,700">
<link rel="stylesheet"
	href="https://fonts.googleapis.com/css?family=Montserrat">
<link rel="stylesheet" href="assets/fonts/fontawesome-all.min.css">
<link rel="stylesheet" href="assets/css/Footer-Dark.css">
<link rel="stylesheet" href="assets/css/Google-Style-Login.css">
<link rel="stylesheet" href="assets/css/Header-Dark.css">
<link rel="stylesheet"
	href="https://unpkg.com/@bootstrapstudio/bootstrap-better-nav/dist/bootstrap-better-nav.min.css">
<link rel="stylesheet" href="assets/css/Login-Form-Clean.css">
<link rel="stylesheet" href="assets/css/Login-Form-Dark.css">
<link rel="stylesheet" href="assets/css/styles.css">
<script type="text/javascript">
var sale = {};

<% for (int sala4 : saleDisponibili.keySet()){%>
	sale.sala<%= sala4 %> = [];
  	
	  <%for(int fascia: saleDisponibili.get(sala4).keySet()){
	    if(saleDisponibili.get(sala4).get(fascia)){ 
	    FasciaOrariaBean f = fasceDao.doRetrieveByKey(fascia);%>
	    	sale.sala<%= sala4 %>.push({id: <%= f.getId() %>, str: "<%= f.getFascia() %>"});
	       
	    <%
	    }
	  }	
	}%>

</script>
<script type="text/javascript">
		function changeSale(){
			var sala = document.getElementById("salaS").value;
			var fasceopt = document.getElementById("fasceOpt");
			for(let i = 0; i < fasceopt.childNodes.length; i++){
				fasceopt.childNodes[i].remove();
			}
			var fasce = sale["sala" + sala];
			for(let i = 0; i < fasce.length; i++){
				let op = document.createElement("option");
				op.appendChild(document.createTextNode(fasce[i].fascia));
				op.value = fasce[i].id;
				fasceopt.appendChild(op);
			}
			
		}
	</script>
</head>

<body
	style="background-image: url(assets/img/food.jpg); height: auto; width: auto; max-height: none;">
	<%
	Boolean error = (Boolean) request.getAttribute("error");
	if (error != null && error) {
	%>
	<script>
		alert("ERRORE, RIPROVA PIU' TARDI")
	</script>
	<%
	}
	%>
	<nav class="navbar navbar-light fixed-top text-dark"
		style="background-color: #FF9900;">
		<div class="container-fluid">
			<img class="img-fluid" src="assets/img/Logo.png"
				style="width: 255px; padding: 0px; margin: -29px;">
			<button data-toggle="collapse" class="navbar-toggler"
				data-target="#navcol-1">
				<span class="sr-only">Toggle navigation</span><span
					class="navbar-toggler-icon"></span>
			</button>
			<div class="collapse navbar-collapse text-right float-right"
				id="navcol-1">
				<ul class="nav navbar-nav mx-auto">
					<li class="nav-item" role="presentation"><a
						class="nav-link active" href="#">Login</a></li>
					<li class="nav-item" role="presentation"><a
						class="nav-link active" href="#">Consulta Menù</a></li>
					<li class="nav-item" role="presentation"><a
						class="nav-link active" href="#">Inserisci Menù</a></li>
					<li class="nav-item" role="presentation"><a
						class="nav-link active" href="#">Valuta Servizio</a></li>
					<li class="nav-item" role="presentation"><a
						class="nav-link active" href="#">Prenotazione</a></li>
					<li class="nav-item" role="presentation"><a
						class="nav-link active" href="#">Servizi Ristorazione</a></li>
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
				<div class="col-md-6"
					style="background-color: rgba(255, 255, 255, 0.92); padding-bottom: 15px; padding-top: 15px;">
					<form action="<%=response.encodeURL("prenotazione")%>"
						method="post">
						<div>
							<%
							if (prenotazione == null) {
							%>
							<h1 style="font-family: Montserrat, sans-serif;">Scegli
								Fascia Oraria</h1>
							<select name="fasciaOraria"
								style="font-family: Montserrat, sans-serif;">
								<optgroup label="Fasce Oraria" id="fasceOpt">
								<%
								for (int sala2 : saleDisponibili.keySet()){
								  saleD[sala2 - 1] = false;
								  for(int fascia: saleDisponibili.get(sala2).keySet()){
								    if(saleDisponibili.get(sala2).get(fascia)){
								      saleD[sala2 - 1] = true; 
								      break;
								    }
								  }	
								}
								int sala;							
								for(sala = 0; sala < saleD.length; sala ++){
								  if(saleD[sala]){
								    break;
								  }
								}
								if(sala < saleD.length){
								  for(int fascia: saleDisponibili.get(sala + 1).keySet()){
								    if(saleDisponibili.get(sala + 1).get(fascia)){
								      FasciaOrariaBean f = fasceDao.doRetrieveByKey(fascia); %>
								      
								      <option value="<%=fascia %>" selected=""><%= f.getFascia() %></option>
								   <% }
								  }
								}
								%>														
								</optgroup>
							</select>
						</div>
						<div>
							<h1 style="font-family: Montserrat, sans-serif;">Scegli Sala</h1>
							<select name="sala" style="font-family: Montserrat, sans-serif;" onchange="changeSala()" id="salaS"><optgroup
									label="Sala">
									<%
									for (int sala3 : saleDisponibili.keySet()){
									  saleD[sala3 - 1] = false;
									  for(int fascia: saleDisponibili.get(sala3).keySet()){
									    if(saleDisponibili.get(sala3).get(fascia)){
									      saleD[sala3 - 1] = true; %>
									      <option value="<%= sala3 %>"><%= sala3 %></option>
									      <%
									      break;
									    }
									  }
									}
									%>
								</optgroup></select>
						</div>
						<button class="btn btn-primary" type="submit"
							style="background-color: rgb(66, 160, 22);">Prenota</button>
						<%
								} else {
						%>
						<h1 style="font-family: Montserrat, sans-serif;">Fascia
							Oraria</h1>
						<select name="fasciaOraria"
							style="font-family: Montserrat, sans-serif;">
							<optgroup label="Fasce Oraria">
								<%
								FasciaOrariaBean fasciaAbc = fasceDao.doRetrieveByKey(prenotazione.getFasciaOraria());
								%>
								<option value="<%=fasciaAbc.getId() %>"><%=fasciaAbc.getFascia()%></option>
							</optgroup>
						</select>
				</div>
				<div>
					<h1 style="font-family: Montserrat, sans-serif;">Sala</h1>
					<select name="sala" style="font-family: Montserrat, sans-serif;"><optgroup
							label="Sala">
							<option value="<%= prenotazione.getSala()%>"><%=prenotazione.getSala()%></option>
						</optgroup></select>
				</div>
				<%
				}
				%>
				</form>
			</div>
			<div class="col-md-6"
				style="background-color: rgba(255, 255, 255, 0.92); padding-bottom: 15px; padding-top: 15px;">
				<div></div>
				<img id="qr"
					src=<%=response.encodeURL("qrcode?height=500&width=500")%>
					onerror="this.onerror=null; this.src='assets/img/Logo.png'">
				<%
				if (prenotazione != null) {
				%>
				<form action="<%=response.encodeURL("prenotazione")%>" method="get">
					<button class="btn btn-primary" type="submit"
						style="background-color: #d2453c;">Annulla Prenotazione</button>
			</div>
			</form>
			<%
			}
			%>
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