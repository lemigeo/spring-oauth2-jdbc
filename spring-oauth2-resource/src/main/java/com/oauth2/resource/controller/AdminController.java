package com.oauth2.resource.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController(value="/admin")
@CrossOrigin
public class AdminController {
	
	@PreAuthorize("hasRole('ADMIN') and #oauth2.hasScope('read')")
	@RequestMapping(value="/admin/{tokenId}", method=RequestMethod.GET)
	@ResponseBody
	public String findById(@PathVariable("tokenId") String tokenId){
		return tokenId;
	}
}