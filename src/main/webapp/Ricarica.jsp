<%@ page import="business.consumatore.ConsumatoreBean" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%
    ConsumatoreBean consumatore = (ConsumatoreBean) request.getSession().getAttribute("consumatore"); //TODO replace with Utente when ready
    if (consumatore != null){
      response.sendRedirect(request.getContextPath() + "/index.jsp");
    }
%>
<!DOCTYPE html>
<html>
<title>Ricarica del Saldo</title>
  <head>
    <meta name="viewport" content="width=device-width, initial-scale=1"> <!-- Ensures optimal rendering on mobile devices. -->
    <meta http-equiv="X-UA-Compatible" content="IE=edge" /> <!-- Optimal Internet Explorer compatibility -->
  </head>

  <body>
  
  
  
  <form id="importo" method="POST" name="importo" action="RicaricaSaldo">
  <input type="radio" id="importo" name="importo" value="5.00">
  <label for="male">5</label><br>
  <input type="radio" id="importo" name="importo" value="10.00">
  <label for="female">10</label><br>
  <input type="radio" id="importo" name="importo" value="20.00">
  <label for="other">20</label><br>
  <input type="radio" id="importo" name="importo" value="50.00">
  <label for="other">50</label>
  <input type="submit" value="Seleziona" onclick="validateform()" /> 
</form>
 <script>
 function validateform(){  
var importo=document.myform.id.importo;  

  
if (importo==null || name==""){  
  alert("seleziona un importo");  
  return false;  
}else {  
  return true;  
  }  
}  
 </script>
 
 
 
 
 <script> var importo = importo; 
 function float importo (importo){
	 return importo;
 }
 </script> 
 


    
    <!-- Add the checkout buttons, set up the order and approve the order -->

    <div id="paypal-button-container">
<script src="https://www.paypal.com/sdk/js?client-id=ASmzurgdl5tJiqNKlv73MgpFOg3XRbu4Dau8g_wDUXbrZwaEBW4LfZgUIkmAYz34NGgTF2zSsSxlbmMs&currency=EUR&disable-funding=sofort,mybank"></script>
<script>paypal.Buttons().render('#paypal-payment-button'); </script>
 
    <script>
    
    paypal.Buttons({
        createOrder: function(data, actions) {
        	var importo=document.getElementsByName("importo");  
        	  
        	
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
            alert('Transazione Completata ' + details.payer.name.given_name);
          });
        }
      }).render('#paypal-button-container'); // Display payment options on your web page
    </script> </div>
  </body>
</html>