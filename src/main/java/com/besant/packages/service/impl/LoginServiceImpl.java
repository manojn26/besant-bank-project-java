package com.besant.packages.service.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;



import com.besant.packages.services.LoginService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class LoginServiceImpl implements LoginService {

	@Override
	public void login(HttpServletRequest req, HttpServletResponse res) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/charanbank","root","crazyleo");
			
		PreparedStatement statement = connection.prepareStatement("SELECT * FROM charanbank.authentication where email= ? and password =?");
		statement.setString(1, req.getParameter("email"));
		statement.setString(2, req.getParameter("password"));
		
		ResultSet result = statement.executeQuery();
		
		if(result.next()) {
			HttpSession session = req.getSession();
			session.setAttribute("usid", result.getString("id"));
			
			res.sendRedirect("Dashboard.html");
			System.out.println("logged  successfully");
		}else {
			System.err.println("password or email incorrect");

		}
			
		connection.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
	}

}
