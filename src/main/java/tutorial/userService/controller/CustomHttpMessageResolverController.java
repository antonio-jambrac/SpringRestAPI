package tutorial.userService.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tutorial.userService.exception.UserNotFoundException;
import tutorial.userService.model.User;
import tutorial.userService.repostiory.UserRepository;

@RestController
@RequestMapping("userconverter")
public class CustomHttpMessageResolverController {
	
	@Autowired
	private UserRepository userRepository;
	
	@PostMapping(value = "/user", consumes = "text/user")
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
	
	@GetMapping(value = "/user/{id}", produces = "text/user")
	public ResponseEntity<User> getUser(@PathVariable ("id") String id) throws UserNotFoundException {
		try {
		Optional<User> user = userRepository.findById(Long.valueOf(id));
		return new ResponseEntity<User>(user.get(), HttpStatus.OK);
		} catch (Exception e) {
			throw new UserNotFoundException("User Not Found For id: "+ id);
		}
	}
}
