package com.oauth2.resource.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController(value="/user")
@CrossOrigin
public class UserController {
	
	@PreAuthorize("hasRole('USER') or hasRole('GUEST') and #oauth2.hasScope('read')")
	@RequestMapping(value="/user/find/{id}", method=RequestMethod.GET)
	@ResponseBody
	public String findById(@PathVariable("id") String id){
		return id;
	}
	
	@PreAuthorize("hasRole('USER') and #oauth2.hasScope('write')")
	@RequestMapping(value="/user/create/{id}", method=RequestMethod.GET)
	@ResponseBody
	public String create(@PathVariable("id") String id){
		return id;
	}
}