/**
 * 
 */
package com.jedago.practica_dss.persistance;

import java.util.List;

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
	public List<User> readUsers() throws Exception;
	
	/**
	 * To save the current list of users
	 * @param  usersList the list of users that we want to save
	 * @throws Exception
	 */
	public void writeUsers(List<User> usersList) throws Exception;
	
	/**
	 * To save a single user in the repository
	 * @param u The user that we want to save in the system
	 * @throws Exception
	 */
	public void saveUser(User u) throws Exception;
}
