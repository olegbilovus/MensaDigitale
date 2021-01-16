<%@ page import="business.utente.Utente" %>
<%@ page import="business.consumatore.ConsumatoreBean" %>
<%@ page import="business.addetto.AddettoBean" %>
<%@ page import="business.admin.AdministratorBean" %>
<%
    Utente consumatore = (Utente) request.getSession().getAttribute("utente");
%>
<nav class="navbar navbar-light fixed-top text-dark" style="background-color: #FF9900;">
        <div class="container-fluid"><img class="img-fluid" src="assets/img/Logo.png" style="width: 255px;padding: 0px;margin: -29px;"><button data-toggle="collapse" class="navbar-toggler" data-target="#navcol-1"><span class="sr-only">Toggle navigation</span><span class="navbar-toggler-icon"></span></button>
            <div
                class="collapse navbar-collapse text-right float-right" id="navcol-1">
                <ul class="nav navbar-nav mx-auto">
                	<%
   						if(consumatore==null) { %>
  									<li class="nav-item" role="presentation"><a class="nav-link active" href="./login.jsp">Login</a></li>
  									<li class="nav-item" role="presentation"><a class="nav-link active" href="./visualizzaMenu.jsp">Consulta Menù</a></li>
		                <%} else 
		                {%>
		                			<form id="logout" action="<%=response.encodeURL("login")%>" method="post">
		                    			<input name="action" value="logOut" style="visibility:hidden;">
		                    					<li class="nav-item" role="presentation"><a onclick="document.getElementById('logout').submit()" class="nav-link active">Logout</a></li>
		                			</form>
		                			<% if(consumatore.getClass()==AdministratorBean.class)
		                				{ %>
				                       		<li class="nav-item" role="presentation"><a class="nav-link active" href="./tracciamentoContatti.jsp">Tracciamento</a></li>	
										<%}
				                		else if(consumatore.getClass()==AddettoBean.class){%>
				                				<li class="nav-item" role="presentation"><a class="nav-link active" href="./userarea.jsp">Area Utente</a></li>
		                						<li class="nav-item" role="presentation"><a class="nav-link active" href="./visualizzaMenu.jsp">Consulta Menù</a></li>
				                				<li class="nav-item" role="presentation"><a class="nav-link active" href="./compilaMenu.jsp">Inserisci Menù</a></li>
				                				<li class="nav-item" role="presentation"><a class="nav-link active" href="./inserisciFasciaOraria.jsp">Fasce Orarie</a></li>
				                				<li class="nav-item" role="presentation"><a class="nav-link active" href="./visualizzaRichiesteInSospeso.jsp">Richieste</a></li>
				              			 <%} 
				                		else if(consumatore.getClass()==ConsumatoreBean.class){%>
				                				<li class="nav-item" role="presentation"><a class="nav-link active" href="./userarea.jsp">Area Utente</a></li>
		                						<li class="nav-item" role="presentation"><a class="nav-link active" href="./visualizzaMenu.jsp">Consulta Menù</a></li>
				                				<li class="nav-item" role="presentation"><a class="nav-link active" href="./inserisciValutazione.jsp">Valuta Servizio</a></li>
                    							<li class="nav-item" role="presentation"><a class="nav-link active" href="./prenotazione.jsp">Prenotazione</a></li>
				                		<%}
				                }%>
                </ul>
                <ul class="nav navbar-nav">
                    <li class="nav-item" role="presentation"></li>
                </ul>
        </div>
        </div>
    </nav>