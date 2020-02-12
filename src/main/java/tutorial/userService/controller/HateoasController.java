package tutorial.userService.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tutorial.userService.model.User;
import tutorial.userService.repostiory.UserRepository;

@RestController
@RequestMapping("v2")
public class HateoasController {

	@Autowired
	private UserRepository userRepository; 
	
	@GetMapping("/user/{id}")
	public EntityModel<User> getUser(@PathVariable ("id") String id) {
		Optional<User> user = userRepository.findById(Long.valueOf(id));
		Link selfLink = WebMvcLinkBuilder.linkTo(HateoasController.class).slash(user.get().getUserId()).withSelfRel();
		Link deleteLink = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(UserController.class).deleteUser(user.get().getUserId()+"")).withRel("delete");
		return new EntityModel<User>(user.get(), selfLink, deleteLink);
	}
}
