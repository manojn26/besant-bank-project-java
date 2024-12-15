package com.besant.packages.service.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.besant.packages.services.TransactionService;
import com.mysql.cj.protocol.Resultset;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class TransactionServiceImpl implements TransactionService {

	@Override
	public void addMoney(HttpServletRequest req, HttpServletResponse res) {
		// TODO Auto-generated method stub
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/charanbank","root","crazyleo");
			
//			String insertQuery = "insert into charanbank.authentication (email,password,accountNumber) values (?,?)";
			
			PreparedStatement statement = connection.prepareStatement("Select bal from profile where usid = ?");
			HttpSession session = req.getSession();
			if(session.getAttribute("usid") == null) {
				res.sendRedirect("Login.html");
			}else {
				statement.setString(1,(String) session.getAttribute("usid"));
				
				
				
				ResultSet result = statement.executeQuery();
				
				if(result.next()) {
					System.out.println(result.getString("bal"));
					PreparedStatement statement2 = connection
							.prepareStatement("update profile set bal = ? where usid =?");
					
					int newBalance = Integer.parseInt(result.getString("bal"))
							+ Integer.parseInt(req.getParameter("depositAmount"));
					statement2.setInt(1, newBalance);
					statement2.setString(2, (String) session.getAttribute("usid"));
					int result2 = statement2.executeUpdate();
					if (result2 > 0) {
						res.sendRedirect("Dashboard.html");
					} else {
						System.err.println("Something went wrong.... try again later");
					}
					System.out.println();
				}

			}
						
			
			
			
			
		connection.close();
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
	}

	@Override
	public void withDraw(HttpServletRequest req, HttpServletResponse res) {
		// TODO Auto-generated method stub
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/charanbank", "root", "crazyleo");
			PreparedStatement statement = connection
					.prepareStatement("select bal from profile where usid= ?");
			HttpSession session = req.getSession();
			
			if(session.getAttribute("usid") == null) {
				res.sendRedirect("Login.html");
			}else {
				statement.setString(1, (String) session.getAttribute("usid"));
				ResultSet result = statement.executeQuery();
				if (result.next()) {
					PreparedStatement statement2 = connection
							.prepareStatement("update profile set bal = ? where usid =?");
					
					int newBalance = Integer.parseInt(result.getString("bal"))
							- Integer.parseInt(req.getParameter("depositMoney"));
					if( newBalance<0) {
						System.err.println("insufficient balance");
					}else {
					statement2.setInt(1, newBalance);
					statement2.setString(2, (String) session.getAttribute("usid"));
					int result2 = statement2.executeUpdate();
					if (result2 > 0) {
						res.sendRedirect("Dashboard.html");
					} else {
						System.err.println("Something went wrong.... try again later");
					}}
				}
			}
			
			
			connection.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public void viewBalance(HttpServletRequest req, HttpServletResponse res) {
		// TODO Auto-generated method stub
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/charanbank", "root", "crazyleo");
			PreparedStatement statement = connection.prepareStatement("select bal from profile where usid= ?");
			HttpSession session = req.getSession();
			statement.setString(1, (String) session.getAttribute("usid"));
			
			ResultSet result = statement.executeQuery();
			
			if(result.next()) {
				int balance = Integer.parseInt(result.getString("bal"));
				System.out.println(balance);
			}
			

			connection.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
	}

}
