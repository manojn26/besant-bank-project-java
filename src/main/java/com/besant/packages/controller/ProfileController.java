package com.besant.packages.controller;

import com.besant.packages.service.impl.ProfileServiceImpl;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/createProfile")
public class ProfileController extends HttpServlet {
	protected void doPost(HttpServletRequest req, HttpServletResponse res) {
		ProfileServiceImpl profileServiceImpl = new ProfileServiceImpl();
		profileServiceImpl.createProfile(req, res);
	}

}
