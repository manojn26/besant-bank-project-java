package com.besant.packages.services;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public interface SignupService {
	public void signup(HttpServletRequest req, HttpServletResponse res);
}
