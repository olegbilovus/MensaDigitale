<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html style="height: auto;width: auto;">

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
    <title>Tracciamento</title>
    <link rel="stylesheet" href="assets/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Bitter:400,700">
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Montserrat">
    <link rel="stylesheet" href="assets/fonts/font-awesome.min.css">
    <link rel="stylesheet" href="assets/css/Footer-Basic.css">
    <link rel="stylesheet" href="assets/css/Google-Style-Login.css">
    <link rel="stylesheet" href="assets/css/Header-Dark.css">
    <link rel="stylesheet" href="https://unpkg.com/@bootstrapstudio/bootstrap-better-nav/dist/bootstrap-better-nav.min.css">
    <link rel="stylesheet" href="assets/css/styles.css">
    
    <script src="assets/js/jquery.min.js"></script>
	
</head>

<body style="background-image: url(&quot;assets/img/food.jpg&quot;);height: auto;width: auto;max-height: none;">
    <jsp:include page="navbar.jsp" />
    <div style="margin-top: 150px;">
        <div class="container">
            <div class="row justify-content-center">
                <div class="col-md-6" style="background-color: rgba(255,255,255,0.92);padding-bottom: 15px;padding-top: 15px;max-width: 95%;width: 357px;">
                    <div>
                        <h1 class="text-center" style="font-family: Montserrat, sans-serif;">Inserisci codice fiscale</h1>
                        <div class="text-center"><input type="text" maxlength="16" name="cf" id="cf" placeholder="Codice Fiscale" style="margin-right: 20px;"><button class="btn btn-warning" onclick="sendCF()">Cerca</button></div>
                    </div>
                           <div>
                    			<div id="date"></div>
								<div id="divPrenotazioni"></div>
							</div>
                	</div>
            </div>
        </div>
    </div>
    <div style="height: 440px;"></div>
   <jsp:include page="footer.jsp" />
    <script src="assets/js/jquery.min.js"></script>
    <script src="assets/bootstrap/js/bootstrap.min.js"></script>
    <script src="https://unpkg.com/@bootstrapstudio/bootstrap-better-nav/dist/bootstrap-better-nav.min.js"></script>
    <script>
		var array = {}
		function sendCF() {
			var inputText = document.getElementById("cf").value;
			if (!(inputText == null || inputText == "")) {
				$
						.ajax({
							type : 'POST',
							url : './TracciamentoContattiServlet',
							data : {
								"cf" : inputText
							},
							success : function(resp) {
								array = {};
								resp = JSON.parse(resp);
								var children = document.getElementById("date").childNodes;
								for(let i = 0; i < children.length; i++) {
									children[i].remove();
								}
								if(document.getElementById("prenotazioni") != undefined) {
									document.getElementById("prenotazioni").remove();
								}
								if(resp.length > 0) {
									var table = document.createElement("table");
									var tr = document.createElement("tr");
									var th = document.createElement("th");
									th.append(document.createTextNode("Nome"));
									tr.append(th);
									th = document.createElement("th")
									th.append(document.createTextNode("Email"));
									tr.append(th);
									th = document.createElement("th")
									th.append(document.createTextNode("Ora"));
									tr.append(th);
									th = document.createElement("th")
									th.append(document.createTextNode("Sala"));
									tr.append(th);
									table.append(tr);
									table.id = "prenotazioni";
									document.getElementById("divPrenotazioni").append(table);
								}
								var tableDate = document.createElement("table");
								var tablePrenotazioni = document
										.getElementById("prenotazioni");

								document.getElementById("date").appendChild(
										tableDate);

								for (let i = 0; i < resp.length; i++) {
									let actual = resp[i];

									let data = actual.dataPrenotazione;
									if (array[data] == undefined) {
										array[data] = [];
										let tr = document.createElement("tr");
										let th = document.createElement("th");
										let text = document
												.createTextNode(data);
										th.addEventListener("click", function() {
											update(data);
										})
										th.append(text);
										tr.appendChild(th);
										tableDate.appendChild(tr);
									}
									//Controlla se e' vuoto
									array[data].push(actual);
									let k = Object.keys(actual);
									if (Object.keys(array)[0] == data) {
										let tr = document.createElement("tr");
										let td = document.createElement("td");
										let text = document
												.createTextNode(actual.nome);
										td.appendChild(text);
										tr.appendChild(td);

										td = document.createElement("td");
										text = document
												.createTextNode(actual.email);
										td.appendChild(text);
										tr.appendChild(td);

										td = document.createElement("td");
										text = document
												.createTextNode(actual.ora);
										td.appendChild(text);
										tr.appendChild(td);

										td = document.createElement("td");
										text = document
												.createTextNode(actual.sala);
										td.appendChild(text);
										tr.appendChild(td);

										tablePrenotazioni.appendChild(tr);
									}
								}
							}
						});
			}
		}
		
		function update(data) {
			if(document.getElementById("prenotazioni") != undefined) {
				document.getElementById("prenotazioni").remove();
			}
			var table = document.createElement("table");
			var tr = document.createElement("tr");
			var th = document.createElement("th");
			th.append(document.createTextNode("Nome"));
			tr.append(th);
			th = document.createElement("th")
			th.append(document.createTextNode("Email"));
			tr.append(th);
			th = document.createElement("th")
			th.append(document.createTextNode("Ora"));
			tr.append(th);
			th = document.createElement("th")
			th.append(document.createTextNode("Sala"));
			tr.append(th);
			table.append(tr);
			table.id = "prenotazioni";
			document.getElementById("divPrenotazioni").append(table);
			listPrenotazioni = array[data];
			for(let i = 0; i < listPrenotazioni.length; i++) {
				var actual = listPrenotazioni[i];
				let tr = document.createElement("tr");
				let td = document.createElement("td");
				let text = document
						.createTextNode(actual.nome);
				td.appendChild(text);
				tr.appendChild(td);

				td = document.createElement("td");
				text = document
						.createTextNode(actual.email);
				td.appendChild(text);
				tr.appendChild(td);

				td = document.createElement("td");
				text = document
						.createTextNode(actual.ora);
				td.appendChild(text);
				tr.appendChild(td);

				td = document.createElement("td");
				text = document
						.createTextNode(actual.sala);
				td.appendChild(text);
				tr.appendChild(td);

				table.appendChild(tr);
			}
		}
	
	</script>
</body>

</html>