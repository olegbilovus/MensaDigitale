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
		<div>
			<table id="prenotazioni">
				<tr>
					<th> Nome </th>
					<th> Email </th>
					<th> Ora </th>
					<th> Sala </th>
				</tr>
			</table>
		</div>
	</div>

	<script src="assets/js/jquery.min.js"></script>
	<script>
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
								console.log(resp);
								var array = {};
								var tableDate = document.createElement("table");
								var tablePrenotazioni = document.getElementById("prenotazioni");
								
								document.getElementById("date").appendChild(
										tableDate);
								
								for(let i=0; i < resp.length; i++) {
									let actual = resp[i];
									
									let data = actual.dataPrenotazione;
									if(array[data] == undefined){
										array[data] = [];
										console.log(array);
										let tr = document.createElement("tr");
										let th = document.createElement("th");
										let text = document.createTextNode(data);
										
										th.append(text);
										tr.appendChild(th);
										tableDate.appendChild(tr);
									}
									//Controlla se è vuoto
									array.data.push(actual);
									if (Object.keys(array)[0] == data) {
										let tr = document.createElement("tr");
										for(let j = 0; j < 4; j++) {
											let td = document.createElement("td");
											let text = document.createTextNode(actual[j]);
											td.appendChild(text);
											tr.appendChild(td);
										}
										tablePrenotazioni.appendChild(tr);
									}
								}
							}
						});
			}
		}
	</script>
</body>
</html>