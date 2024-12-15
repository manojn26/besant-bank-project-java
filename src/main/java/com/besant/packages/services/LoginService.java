package com.besant.packages.services;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public interface LoginService {
	public void login(HttpServletRequest req, HttpServletResponse res);
}
