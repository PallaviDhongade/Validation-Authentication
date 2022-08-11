package com.springboot.in;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {

	@Autowired
	UsersRepository usersRepository;
	
	@Autowired
	JwtTokenUtil jwtUtil;
	
	@Autowired 
	AuthenticationManager authManager;
	
	@GetMapping(value="/register")
	public List<Users> getUsers(){
		return (List<Users>) usersRepository.findAll();
	}
	
	@PostMapping(value="/adduser")
	public String  saveUser(@RequestBody Users users) {
		
		if(usersRepository.existsByPhoneNo(users.getPhoneNo())) {
			return "PhoneNo already Exist";
		}
		
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    	String password = passwordEncoder.encode(users.getPassword());
    	
    	Users newUser = new Users();
    	
    	newUser.setFirstName(users.getFirstName());
		newUser.setLastName(users.getLastName());
		newUser.setOccupation(users.getOccupation());
		newUser.setComname(users.getComname());
		newUser.setAge(users.getAge());
		newUser.setLocation(users.getLocation());
		newUser.setPhoneNo(users.getPhoneNo());
		newUser.setPassword(password);
		newUser.setEmail(users.getEmail());
		
		usersRepository.save(newUser);
		return "Data saved in Database";
	}
	public ResponseEntity<Users> create(@RequestBody @Valid Users users) {
        Users savedUsers = usersRepository.save(users);
        URI usersURI = URI.create("/users/" + savedUsers.getId());
        return ResponseEntity.created(usersURI).body(savedUsers);
    }
     
    @GetMapping("/home")
    public String home() {
    	return "This is home page";
    }
    
    @PutMapping(value = "update/{id}")
	public String updateUsers(@PathVariable long id, @RequestBody Users users){
		
		Users updatedUser = usersRepository.findById(id).get();
		updatedUser.setFirstName(users.getFirstName());
		updatedUser.setLastName(users.getLastName());
		updatedUser.setOccupation(users.getOccupation());
		updatedUser.setComname(users.getComname());
		updatedUser.setAge(users.getAge());
		updatedUser.setLocation(users.getLocation());
		updatedUser.setPhoneNo(users.getPhoneNo());
		updatedUser.setPassword(users.getPassword());
		updatedUser.setEmail(users.getEmail());
		usersRepository.save(updatedUser);
		return "Updated User info successfully...";
		
	}
	
	
}
