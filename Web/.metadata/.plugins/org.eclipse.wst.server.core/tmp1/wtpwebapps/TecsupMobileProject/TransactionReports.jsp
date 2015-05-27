<%@page import="java.util.*,BankServices.modelo.Transaction"%>
<%@page import="java.util.*,BankLogic.TransactionLogic"%>
<!DOCTYPE HTML>
<!--
	Strongly Typed by HTML5 UP
	html5up.net | @n33co
	Free for personal and commercial use under the CCA 3.0 license (html5up.net/license)
-->
<html>
<head>
<title>Transactions Report</title>
<meta charset="utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1" />
<!--[if lte IE 8]><script src="assets/css/ie/html5shiv.js"></script><![endif]-->
<link rel="stylesheet" href="assets/css/main.css" />
<!--[if lte IE 8]><link rel="stylesheet" href="assets/css/ie/v8.css" /><![endif]-->
<!--[if lte IE 8]><script src="assets/css/ie/respond.min.js"></script><![endif]-->

<link rel="icon" type="image/x-icon" href="images/favicon.ico" />



</head>


<body class="header">

	<div id="page-wrapper">


		<!-- Header -->
		<div id="header-wrapper">
			<div id="header" class="container">


				<!-- Logo -->
				<h1 id="logo">
					<a href="TransactionReports.jsp">Transaction Report</a>
				</h1>


			</div>
		</div>
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
										<a href="#">Account Type: </a>
									</h2> <!-- Excerpt -->
									<article class="box excerpt">
										<header>

											<h4>
												<a href="#">Checking</a>
											</h4>
										</header>

									</article>

									<article class="box excerpt">
										<header>

											<h4>
												<a href="#">Savings</a>
											</h4>
										</header>

									</article>

								</li>


							</ul>
						</section>



					</div>

					<div align="right">
						Current Balance: <span class="currencyinput">$<input
							type="text" name="currency" disabled></span>


					</div>

					<br> <br>
					
					<table class="table table-hover">
							<tr>
								<th>AccountNum</th>
								<th>CurrentBal</th>
								<th>Status</th>
								<th>Available</th>
							</tr>
							<%

							%>
						</table>





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