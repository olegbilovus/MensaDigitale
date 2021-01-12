<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<input type="text" maxlength="16" name="cf" id="cf"
		placeholder="Codice Fiscale">
	<button onclick="sendCF()">Conferma</button>
	<hr>
	<div>
		<div id="date"></div>
		<div id="divPrenotazioni"></div>
	</div>

	<script src="assets/js/jquery.min.js"></script>
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
									//Controlla se è vuoto
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