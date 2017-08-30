package it.biagio.repository;

import org.springframework.data.repository.CrudRepository;

import it.biagio.entities.User;

public interface UserInterface extends CrudRepository<User, Integer> {
	public User findByUsernameAndPassword(String username, String password);
	public User findByUsername(String username);
}
