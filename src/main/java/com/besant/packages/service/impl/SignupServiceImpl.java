package com.besant.packages.service.impl;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import com.besant.packages.services.SignupService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class SignupServiceImpl implements SignupService {
	
	public static int generateRandomNumber() {
		int ran = 0;
	      for(int i=0;i < 5;i++){
	        int randomNumber = (int) (Math.random()*100);
	        ran = ran * 10 + randomNumber;
	      }
	    return ran;
	}

	@Override
	public void signup(HttpServletRequest req, HttpServletResponse res) {
		System.out.println("Business login for signup");
		String userEmail = req.getParameter("email");
		String password = req.getParameter("password");
		System.out.println(userEmail+" "+password);
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/charanbank","root","crazyleo");
			
//			String insertQuery = "insert into charanbank.authentication (email,password,accountNumber) values (?,?)";
			
			PreparedStatement statement = connection.prepareStatement("insert into charanbank.authentication (email,password,accountNumber) values (?,?,?)");
			statement.setString(1, userEmail);
			statement.setString(2, password);
			statement.setInt(3, generateRandomNumber());
			
			int response = statement.executeUpdate();
			System.out.println(response);
			if(response > 0) {
				System.out.println("signed up successfully");
				res.sendRedirect("Login.html");
			}else {
				System.err.println("something went wrong");
			}
			
			connection.close();
			
			
			
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		
	}

	
}
