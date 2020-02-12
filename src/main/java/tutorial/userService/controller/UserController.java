package tutorial.userService.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import tutorial.userService.model.User;
import tutorial.userService.repostiory.UserRepository;

@RestController
public class UserController {

	@Autowired
	private UserRepository userRepository;
	
	@PostMapping("/user")
	public ResponseEntity<Void> addUser (@RequestBody User user) {
		try {
		User createUser = userRepository.save(user);
		HttpHeaders headers = new HttpHeaders();
		headers.add("id", createUser.getUserId().toString());
		return new ResponseEntity<>(headers, HttpStatus.CREATED);
		
		} catch (Exception e) {
			return new ResponseEntity<Void>(HttpStatus.EXPECTATION_FAILED);
		}
	}
	
	@GetMapping("/user/{id}")
	public ResponseEntity<User> getUser(@PathVariable ("id") String id) {
		try {
		Optional<User> user = userRepository.findById(Long.valueOf(id));
		return new ResponseEntity<User>(user.get(), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
		}
	}
	
	@PutMapping("/user")
	public ResponseEntity<User> update (@RequestBody User user){
		try {
		User updateUser = userRepository.save(user);
		return new ResponseEntity<User>(updateUser, HttpStatus.OK);
		
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
		}
	}
	
	@DeleteMapping("user/{id}")
	public ResponseEntity<User> deleteUser(@PathVariable("id") String id) {
		try {
		userRepository.deleteById(Long.valueOf(id));
		return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
		}
		
	}
}
