package com.besant.packages.controller;

import com.besant.packages.service.impl.LoginServiceImpl;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/login")
public class LoginController extends HttpServlet {
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse res) {
		LoginServiceImpl loginService = new LoginServiceImpl();
		loginService.login(req, res);
//		System.out.println("Working on Login Servie");
	}
}
