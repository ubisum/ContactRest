package it.biagio.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import it.biagio.entities.Contact;
import it.biagio.repository.ContactInterface;

@RestController
@RequestMapping("/Contact")
public class ContactController {
	@Autowired
	private ContactInterface repository;
	
	@RequestMapping(value = "/getModel", method = RequestMethod.GET)
	public Contact getModel() {
		return new Contact();
	}
	
	@RequestMapping(value = "/saveContact", method = RequestMethod.POST)
	public ResponseEntity<Contact> saveContact(@RequestBody Contact contact) {

		try {
			repository.save(contact);
			return new ResponseEntity<>(contact, HttpStatus.CREATED);
		}

		catch (Exception e) {
			System.out.println(e.getMessage());
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}
	
	@RequestMapping(value = "/deletePerson{id}", method = RequestMethod.DELETE)
	public ResponseEntity<String> deleteContact(@PathVariable("id") int id) {

		try 
		{
			repository.delete(id);
			return new ResponseEntity<>("Contact deleted",HttpStatus.OK);
		} 
		
		catch (Exception e) 
		{
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}
	
	@RequestMapping(value = "/editContact", method = RequestMethod.POST)
	public ResponseEntity<Contact> editContact(@RequestBody Contact contact)
	{
		try
		{
			Contact fromDB = repository.findOne(contact.getId());
			
			// copy new information
			fromDB.setEmail(contact.getEmail());
			fromDB.setName(contact.getName());
			fromDB.setSurname(contact.getSurname());
			fromDB.setTelephone(contact.getTelephone());			
			
			repository.save(fromDB);
			return new ResponseEntity<>(fromDB, HttpStatus.OK);
		}
		
		catch(Exception e)
		{
			System.out.println(e.getMessage());
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@RequestMapping(value = "/getContacsByUser{id}", method = RequestMethod.GET)
	public ResponseEntity<Iterable<Contact>> getContactsByUser(@PathVariable("id") int id)
	{
		try
		{
			return new ResponseEntity<>(repository.findByUserId(id), HttpStatus.OK);
		}
		
		catch(Exception e)
		{
			System.out.println(e.getMessage());
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		
		}
	}
	
	@RequestMapping(value = "/getContactById{id}", method = RequestMethod.GET)
	public ResponseEntity<Contact> getContactById(@PathVariable("id") int id)
	{
		try
		{
			return new ResponseEntity<>(repository.findById(id), HttpStatus.OK);
		}
		
		catch(Exception e)
		{
			System.out.println(e.getMessage());
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	
}
