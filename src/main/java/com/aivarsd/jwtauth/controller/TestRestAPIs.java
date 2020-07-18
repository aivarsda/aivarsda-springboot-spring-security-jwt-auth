package com.aivarsd.jwtauth.controller;

import com.aivarsd.jwtauth.dtos.response.ApiErrorResponse;
import com.aivarsd.jwtauth.security.services.UserPrinciple;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class TestRestAPIs extends BaseController {
	@GetMapping("/api/test/user")
	@PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
	public String userAccess(@AuthenticationPrincipal UserPrinciple user) {
		return ">>> " + user.toString() + " -> got Access to : User Contents";
	}

	@GetMapping("/api/test/pm")
	@PreAuthorize("hasRole('ROLE_PM') or hasRole('ROLE_ADMIN')")
	public String projectManagementAccess(@AuthenticationPrincipal UserPrinciple user) {
		return ">>> " + user.toString() + " -> got Access to : Project Management Contents";
	}
	
	@GetMapping("/api/test/admin")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public String adminAccess(@AuthenticationPrincipal UserPrinciple user) {
		return ">>> " + user.toString() + " -> got Access to : Admin Contents";
	}

	@Override
	String getApiType() {
		return ApiErrorResponse.ApiType.API.getValue();
	}
}