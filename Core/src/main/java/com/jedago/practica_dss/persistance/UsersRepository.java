/**
 * 
 */
package com.jedago.practica_dss.persistance;

import java.util.List;
import java.util.Optional;

import com.jedago.practica_dss.core.User;

/**
 * @author Jesus Serrano Gallan
 *
 */
public interface UsersRepository {
	/**
	 * To get the list of registered users
	 * @return A list with all the registered users
	 * @throws Exception
	 */
	public List<User> findAll() throws Exception;
	
	/**
	 * To get an user from its id
	 * @param id
	 * @return The user with that id
	 * @throws Exception
	 */
	public Optional<User> findById(int id) throws Exception;
	
	/**
	 * To save the current list of users overwriting it
	 * @param  usersList the list of users that we want to save
	 * @throws Exception
	 */
	public void save(List<User> usersList) throws Exception;
	
	/**
	 * To save a single user in the repository
	 * @param u The user that we want to save in the system
	 * @throws Exception
	 */
	public void add(User u) throws Exception;
	
	/**
	 * To delete several users from the repository
	 * @param usersList users to delete
	 * @throws Exception
	 */
	public void delete(List<User> usersList) throws Exception; 
	
	/**
	 * To delete a user from the repository
	 * @param u user to delete
	 * @throws Exception
	 */
	public void delete(User u) throws Exception; 
	
	/**
	 * To update a user from the repository
	 * @param id Id of the existing user to update
	 * @param u user info to update
	 * @throws Exception
	 */
	public void update(int id, User u) throws Exception; 
}
