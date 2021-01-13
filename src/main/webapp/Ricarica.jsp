<%@ page import="business.consumatore.ConsumatoreBean"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%
    ConsumatoreBean consumatore = (ConsumatoreBean) request.getSession().getAttribute("consumatore"); //TODO replace with Utente when ready
    if (consumatore == null){
      
      response.sendRedirect(request.getContextPath() + "/index.jsp");
      
      return;
    }
%>
<!DOCTYPE html>
<html>
<title>Ricarica del Saldo</title>
<head>
<script
  src="https://code.jquery.com/jquery-3.5.1.min.js"
  integrity="sha256-9/aliU8dGd2tb6OSsuzixeV4y/faTqgFtohetphbbj0="
  crossorigin="anonymous"></script>
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- Ensures optimal rendering on mobile devices. -->
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<!-- Optimal Internet Explorer compatibility -->
</head>

<body>

	<div id="importo">
		<input type="radio" name="importo" value="5.00"> <label
			for="male">5</label><br> <input type="radio" name="importo"
			value="10.00"> <label for="female">10</label><br> <input
			type="radio" name="importo" value="20.00"> <label for="other">20</label><br>
		<input type="radio" name="importo" value="50.00"> <label
			for="other">50</label>
	</div>


	<!-- Add the checkout buttons, set up the order and approve the order -->

	<div id="paypal-button-container"></div>
	<script src="https://www.paypal.com/sdk/js?client-id=ASmzurgdl5tJiqNKlv73MgpFOg3XRbu4Dau8g_wDUXbrZwaEBW4LfZgUIkmAYz34NGgTF2zSsSxlbmMs&currency=EUR&disable-funding=sofort,mybank"></script>


	<script>
	var importo = null;
    paypal.Buttons({   
        createOrder: function(data, actions) {
        	importo =document.querySelector("#importo input[type=radio]:checked").value;
        	
          return actions.order.create({
            purchase_units: [{
              amount: {
                value:importo
              }
            }]
          });
        },
        onApprove: function(data, actions) {
          return actions.order.capture().then(function(details) {
            console.log(details);
            $.ajax({
            	type:"POST", url:"./RicaricaSaldo", data:{transaction_id:details.id,amount:importo}, 
            	success: function (data) {
            		alert("Transazione Eseguita");
            	}
            })
        	  
          });
        }
      }).render('#paypal-button-container'); // Display payment options on your web page
    </script>
</body>
</html>