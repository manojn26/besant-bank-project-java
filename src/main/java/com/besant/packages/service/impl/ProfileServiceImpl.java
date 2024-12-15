package com.besant.packages.service.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.besant.packages.services.ProfileService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class ProfileServiceImpl implements ProfileService {

	@Override
	public void createProfile(HttpServletRequest req, HttpServletResponse res) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/charanbank","root","crazyleo");
			
			PreparedStatement statement = connection.prepareStatement("insert into charanbank.profile (name , bal, pan,address,usid) values (?,0,?,?,?)");
			statement.setString(1, req.getParameter("name"));
			statement.setString(2, req.getParameter("pan"));
			statement.setString(3, req.getParameter("address"));
			
			HttpSession session = req.getSession();
			
			if(session.getAttribute("usid") == null) {
				res.sendRedirect("Login.html");
			}else {
				statement.setString(4, (String) session.getAttribute("usid"));
				System.out.println(session.getAttribute("usid"));
			
				int response = statement.executeUpdate();
				if(response > 0) {
					System.out.println("signed up successfully");
					res.sendRedirect("Dashboard.html");
				}else {
					System.err.println("something went wrong");
				}
			}
			
		
			
		connection.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
	}

}
