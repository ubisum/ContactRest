package it.biagio.repository;

import org.springframework.data.repository.CrudRepository;

import it.biagio.entities.Contact;

public interface ContactInterface extends CrudRepository<Contact, Integer> {
	public Iterable<Contact> findByUserId(int id);
	public Contact findById(int id);
	
}
