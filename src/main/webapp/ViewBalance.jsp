<%@page import="java.sql.ResultSet"%>
<%@page import="jakarta.servlet.http.HttpSession"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<style>
/* Resetting margins and padding */
* {
	margin: 0;
	padding: 0;
	box-sizing: border-box;
}

/* Body and general layout */
body {
	font-family: 'Arial', sans-serif;
	background-color: #f4f8fb; /* Light blue background */
	color: #333;
	padding: 0 20px;
}

/* Navigation Bar Styling */
nav {
	background-color: #007BFF; /* Blue background */
	padding: 15px 20px;
	text-align: center;
}

nav a {
	color: #fff; /* White text */
	text-decoration: none;
	margin: 0 20px;
	font-size: 16px;
	text-transform: uppercase;
	font-weight: bold;
}

nav a:hover {
	color: #FFD700; /* Gold color on hover */
}

/* Heading */
h1 {
	color: #007BFF;
	text-align: center;
	margin-top: 40px;
	font-size: 36px;
}

/* Balance display */
.balance {
	background-color: #fff;
	padding: 20px;
	border-radius: 8px;
	margin-top: 30px;
	text-align: center;
	box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
}

.balance h1 {
	font-size: 48px;
	color: #28a745; /* Green color for balance */
}

.balance h4 {
	font-size: 20px;
	color: #555;
	margin-top: 10px;
}

/* Footer Section */
footer {
	background-color: #007BFF; /* Blue background */
	color: #fff;
	text-align: center;
	padding: 5px; margin-bottom : 0px;
	position: absolute;
	bottom: 0;
	width: 100%;
	font-size: 14px;
	margin-bottom: 0px;
}
</style>

</head>
<body>

	<nav>
		<a href="Dashboard.html">Dashboard</a> <a href="Login.html">Logout</a>

	</nav>


	<%
	try {
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/charanbank", "root", "crazyleo");
		PreparedStatement statement = connection.prepareStatement("select bal from profile where usid= ?");
		HttpSession session1 = request.getSession();
		String userId = (String) session1.getAttribute("usid");
		if (userId != null) {
			statement.setString(1, userId);
			ResultSet result = statement.executeQuery();

			if (result.next()) {
		int balance = result.getInt("bal");
	%>
	<div class="balance">
		<h1>Your balance is:</h1>
		<h1><%=balance%></h1>
		<h4>Thank you for banking with us!</h4>
	</div>
	<%
	} else {
	%>
	<p>No balance information found for your account.</p>
	<%
	}
	result.close();
	} else {
	%>
	<p>User session not found. Please log in.</p>
	<%
	}
	statement.close();
	connection.close();
	} catch (Exception e) {
	out.println("<p>Error retrieving balance: " + e.getMessage() + "</p>");
	e.printStackTrace();
	}
	%>



	<footer>
		<p>&copy; 2024 CHARAN Bank. All rights reserved.</p>
	</footer>


</body>
</html>