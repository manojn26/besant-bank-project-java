package com.besant.packages.services;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public interface ProfileService {
	public void createProfile(HttpServletRequest req, HttpServletResponse res);

}
