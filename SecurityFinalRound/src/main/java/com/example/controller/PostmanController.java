package com.example.controller;

import java.util.Arrays;
import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.dto.ReqDto;
import com.example.dto.RespDto;
import com.example.entity.Employee;
import com.example.repo.EmpRepo;
import com.example.repo.RoleRepo;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@RestController
public class PostmanController {
	
	@Value("${jwt.security.key}")
	private String key;

	@Autowired
	private EmpRepo empRepo;
	@Autowired
	private RoleRepo roleRepo;
	@Autowired
	private PasswordEncoder encoder; 
	@Autowired
	private AuthenticationManager authManager;
	
	
	@GetMapping("/emphome")         // anyone can have access
	public ResponseEntity<String> home(){
		return new ResponseEntity<String>("welcome home...!", HttpStatus.OK);
	}
	
	@PostMapping("/addnewemployee")     // anyone can have access
	public ResponseEntity<String> saveEmp(@RequestBody Employee emp){
		emp.setPassword(encoder.encode(emp.getPassword()));
		emp.setRoles(Arrays.asList(roleRepo.findByName("normal")));
		empRepo.save(emp);
		return new ResponseEntity<String>("new employee created", HttpStatus.CREATED);
	}
	
	@PostMapping("/emplogin")
	public ResponseEntity<?> handleLogin(@RequestBody ReqDto dto){
//		String key = "championtheonetheonlypoundforpoundnumberonejonbonesjones";
		SecretKey shaKey = Keys.hmacShaKeyFor(key.getBytes());
		
		try {
			
			Authentication authObj = authManager.authenticate(new UsernamePasswordAuthenticationToken(dto.getEmail(), dto.getPassword()));
			// if this lines fails, will directly jump to exception
			SecurityContextHolder.getContext().setAuthentication(authObj);
			
			
			// lets create now Jwt token with 8 parameters and send it to frontend as a response
			
			String JwtToken = Jwts.builder()
				.subject("token for myApp").issuer("brainworks")
				.issuedAt(new Date()).expiration(new Date(System.currentTimeMillis()+3600000))
				.claim("username", authObj.getName())
				.claim("authorities", authObj.getAuthorities().stream().map(GrantedAuthority::getAuthority).toList())
				// here are the authorities from DB into spring boot
				.signWith(shaKey).compact();
			
			RespDto respDto = new RespDto("login successful", JwtToken);
			return new ResponseEntity<>(respDto, HttpStatus.OK);
			
		} catch (BadCredentialsException e) {
			return new ResponseEntity<>("Invalid credentials...!", HttpStatus.OK);
		}
	}
	
	
	
	
	@GetMapping("/findemployee/{id}")      // anyone can have access
	public ResponseEntity<Employee> getEmp(@PathVariable Integer id){
		Employee foundEmp = empRepo.findById(id).orElseThrow(()-> new RuntimeException("Invalid id...!"));
		return new ResponseEntity<Employee>(foundEmp, HttpStatus.OK);
	}
	
	
	@PostMapping("/changename")   // only admin (vijay) should have this priviledge
	public ResponseEntity<String> nameChange(@RequestBody Employee emp){
		Employee found = empRepo.findByEmail(emp.getEmail());
		found.setName(emp.getName());
		empRepo.save(found);
		return new ResponseEntity<String>("Name has been changed successfully...!", HttpStatus.OK);
	}
	
	
	@GetMapping("/deleteemployee/{id}")    // only super-admin (chetan) can have access to this
	public ResponseEntity<String> deleteEmp(@PathVariable Integer id){
	    System.out.println("DELETE EMPLOYEE API CALLED");
		Employee foundEmp = empRepo.findById(id).orElseThrow(()-> new RuntimeException("Invalid id...!"));
		empRepo.deleteById(foundEmp.getId());
		return new ResponseEntity<String>("deleted employee successfully", HttpStatus.OK);
	}
	
	
	
	
	
}
