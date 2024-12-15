package com.besant.packages.controller;

import com.besant.packages.service.impl.TransactionServiceImpl;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/withdrawMoney")
public class WithdrawController extends HttpServlet {
	
	public void doPost(HttpServletRequest req, HttpServletResponse res) {
		
		TransactionServiceImpl service = new TransactionServiceImpl();
		service.withDraw(req, res);
		
	}

}
