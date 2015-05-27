<%@page import="java.util.*,BankServices.modelo.CheckingAccount"%>
<%@page import="java.util.*,BankServices.dao.ReportDAO"%>
<%@page import="java.util.*,BankServices.dao.AccountDAO"%>
<%@page import="java.util.*,BankServices.modelo.Transaction"%>
<%@page import="java.util.*,BankLogic.TransactionLogic"%>
<%@page import="java.util.*,BankServices.modelo.Report"%>
<%@page import="java.util.*,BankServices.modelo.Customer"%>
<%@page import="java.util.*,BankServices.modelo.Account"%>
<!DOCTYPE HTML>
<!--
	Strongly Typed by HTML5 UP
	html5up.net | @n33co
	Free for personal and commercial use under the CCA 3.0 license (html5up.net/license)
-->
<html>
<head>
<title>Transaction Report</title>
<meta charset="utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1" />
<!--[if lte IE 8]><script src="assets/css/ie/html5shiv.js"></script><![endif]-->
<link rel="stylesheet" href="assets/css/main.css" />
<!--[if lte IE 8]><link rel="stylesheet" href="assets/css/ie/v8.css" /><![endif]-->
<!--[if lte IE 8]><script src="assets/css/ie/respond.min.js"></script><![endif]-->

<link rel="icon" type="image/x-icon" href="images/favicon.ico" />

					<%
					ReportDAO dao = new ReportDAO();
					Customer customer = (Customer)session.getAttribute("user");
					AccountDAO Adao = new AccountDAO();
					Account account = Adao.getAccountByCustomer(customer, "Checking");
					AccountDAO Bdao = new AccountDAO();
					Account accountB = Bdao.getAccountByCustomer(customer, "Savings");
					%>
					



</head>


<body class="header">


		<!-- Header -->
		<header>
		<div id="header-wrapper">
			<div id="header" class="container">


				<!-- Logo -->
				<h1 id="logo">
					<a href="TransactionReports.jsp">Transaction Report</a>
				</h1>

				
			</div>
		</div>
		</header>
		
		<!-- Main -->
		<div id="main-wrapper">
			<div id="main" class="container">
				<div class="row">

					<!-- Sidebar -->
					<div id="sidebar" class="4u 12u(mobile)">

						<!-- Excerpts -->
						<section>
							<ul class="divided">
								<li>
									<h2>
										<a href="#">User: <%out.println(customer.getName()); %></a>
									</h2> <!-- Excerpt -->
									<article class="box excerpt">
										<header>

											<h4>
												<a href="#">Checking</a>
											</h4>
										</header>

									</article>



									

								</li>


							</ul>
						</section>



					</div>
					
					<section>
					<!doctype html>
					<html lang="en">
					<head>
						<meta charset="utf-8">
						  <title>jQuery UI Datepicker - Select a Date Range</title>
						  <link rel="stylesheet" href="//code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">
						  <script src="//code.jquery.com/jquery-1.10.2.js"></script>
						  <script src="//code.jquery.com/ui/1.11.4/jquery-ui.js"></script>
						  <link rel="stylesheet" href="/resources/demos/style.css">
						  <script>
						  $(function() {
						    $( "#from" ).datepicker({
						      defaultDate: "+1w",
						      changeMonth: true,
						      numberOfMonths: 3,
						      onClose: function( selectedDate ) {
						        $( "#to" ).datepicker( "option", "minDate", selectedDate );
						      }
						    });
						    $( "#to" ).datepicker({
						      defaultDate: "+1w",
						      changeMonth: true,
						      numberOfMonths: 3,
						      onClose: function( selectedDate ) {
						        $( "#from" ).datepicker( "option", "maxDate", selectedDate );
						      }
						    });
						  });
						  </script>
						</head>
						<body>
						 
						<label for="from">From</label>
						<input type="text" id="from" name="from">
						<label for="to">to</label>
						<input type="text" id="to" name="to">
						
						</body>
						</html>
					
					</section>
				
<section>
					<div align="right">
						Current Checking Balance: <span class="currencyinput">$<input
							type="text" name="currency" disabled value=<% out.println(account.getCurrentBal());%>></span>
</section>

					</div>
					
					<br> <br>
					
					<table class="table table-hover">
					<div align ="left">
							<tr>
								<th>Date</th>
								<th>Description</th>
								<th>Amount</th>
								<th>Status</th>
							</tr>
							
							<%
					
	
							List<Report> list = dao.getTransactions(account.getAccountNumber().toString());
 
							if (list != null) {
								for (Iterator i = list.iterator(); i.hasNext();) {
									Report re = (Report) i.next();
									
									out.println("<tr><td>" + re.getDate() + "</td>");
									out.println("<td>" + re.getDescription() + "</td>");										
									out.println("<td>" + re.getAmount() + "</td>");										
									out.println("<td>" + re.getStatus() + "</td></tr>");

								}
							}
							%>
						</table>


									<br>
									<br>
									<br>
									<article class="box excerpt">
										<header>

											<h4>
												<a href="#">Savings</a>
											</h4>
										</header>
									</article>

									<div>
									<section>
									
									<div align="center">
						Current Savings Balance: <span class="currencyinput">$<input
							type="text" name="currency" disabled value=<% out.println(accountB.getCurrentBal());%>></span>
</section>

					</div>
					<br>
					<tr align ="left">
									<table class="table table-hover">
									
							<tr>
							
								<th>Date</th>
								<th>Description</th>
								<th>Amount</th>
								<th>Status</th>
							</tr>
											<%
											

							List<Report> listA = dao.getTransactions(accountB.getAccountNumber().toString());
 
							if (list != null) {
								for (Iterator i = listA.iterator(); i.hasNext();) {
									Report reA = (Report) i.next();
									
									out.println("<tr><td>" + reA.getDate() + "</td>");
									out.println("<td>" + reA.getDescription() + "</td>");										
									out.println("<td>" + reA.getAmount() + "</td>");										
									out.println("<td>" + reA.getStatus() + "</td></tr>");

								}
							}
							%>
					</table>
</div>
				</div>
			</div>

		</div>

		<!-- Scripts -->
		<script src="assets/js/jquery.min.js"></script>
		<script src="assets/js/jquery.dropotron.min.js"></script>
		<script src="assets/js/skel.min.js"></script>
		<script src="assets/js/skel-viewport.min.js"></script>
		<script src="assets/js/util.js"></script>
		<script src="assets/js/main.js"></script>
</body>
</html>