package com.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.model.LoginDTO;
import com.app.model.User;
import com.app.service.userService;

@RestController
@RequestMapping("/user")
public class UserController {

	
	@Autowired
	private userService userService;
@PostMapping("/login")
public ResponseEntity<User> login( @RequestBody LoginDTO loginDTO) throws Exception{
	
	 User checkLogin = this.userService.checkLogin(loginDTO.getUserName(), loginDTO.getPassword());
	
	 return new ResponseEntity<User>(checkLogin, HttpStatus.OK);
}
}

