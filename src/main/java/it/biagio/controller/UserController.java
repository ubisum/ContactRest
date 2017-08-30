package it.biagio.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import it.biagio.entities.User;
import it.biagio.repository.UserInterface;

@RestController
@RequestMapping("/User")
public class UserController {
	@Autowired
	private UserInterface repository;
	
	@RequestMapping(value = "/getModel", method = RequestMethod.GET)
	public User getModel() {
		return new User();
	}
	
	@RequestMapping(value = "/saveUser", method = RequestMethod.POST)
	public ResponseEntity<User> saveUser(@RequestBody User user) {

		try {
			repository.save(user);
			User fromDB = repository.findByUsername(user.getUsername());
			return new ResponseEntity<>(fromDB, HttpStatus.CREATED);
		}

		catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}
	
	@RequestMapping(value = "/editUser", method = RequestMethod.POST)
	public ResponseEntity<User> editUser(@RequestBody User user) {

		try 
		{
			repository.save(user);
			return new ResponseEntity<>(user, HttpStatus.OK);
		} 
		
		catch (Exception e) 
		{
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}
	
	@RequestMapping(value = "/getListUser", method = RequestMethod.GET)
	public ResponseEntity<Iterable<User>> getListUser() {

		try 
		{
			
			return new ResponseEntity<>(repository.findAll(), HttpStatus.OK);
		} 
		
		catch (Exception e) 
		{
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}
	
	@RequestMapping(value = "/deleteUser{id}", method = RequestMethod.DELETE)
	public ResponseEntity<User> deleteUser(@PathVariable("id") int id) {

		try 
		{
			repository.delete(id);
			return new ResponseEntity<>(HttpStatus.OK);
		} 
		
		catch (Exception e) 
		{
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}
	
	@RequestMapping(value = "/getAccess{username}/{password}", method = RequestMethod.GET)
	public ResponseEntity<User> getAccess(@PathVariable("username") String username, 
			                              @PathVariable("password") String password) 
	{

		try 
		{
			return new ResponseEntity<>(repository.findByUsernameAndPassword(username, password), HttpStatus.OK);
		} 
		
		catch (Exception e) 
		{
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}
	
	
}
