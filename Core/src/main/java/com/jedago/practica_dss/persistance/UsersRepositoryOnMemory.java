package com.jedago.practica_dss.persistance;

import java.util.List;

import com.jedago.practica_dss.core.User;

public class UsersRepositoryOnMemory implements UsersRepository {
	private List<User> users;

	@Override
	public List<User> readUsers() throws Exception {
		return users;
	}

	@Override
	public void writeUsers(List<User> usersList) throws Exception {
		this.users = usersList;
	}

	@Override
	public void saveUser(User u) throws Exception {
		users.add(u);
	}

	@Override
	public User findUserById(int id) throws Exception {
		
		for(User i: users) {
			if(i.getIdUser()==id) {
				User seekUser = i;
				return seekUser;
			}
		}
		return null;
		
		
	}
	
	
	
	
	
	
}
