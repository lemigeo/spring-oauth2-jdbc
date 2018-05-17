package com.oauth2.resource.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController(value="/login")
@CrossOrigin
public class LoginController {

	@RequestMapping(value="/login", method=RequestMethod.GET)
	@ResponseBody
	public String loginByauthcode(@RequestParam("code") String code){
		//get access token
		//http://localhost:8082/oauth/token?grant_type=authorization_code&code=%s
		return code;
	}
}