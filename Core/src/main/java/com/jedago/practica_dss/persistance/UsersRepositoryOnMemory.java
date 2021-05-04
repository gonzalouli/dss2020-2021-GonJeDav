package com.jedago.practica_dss.persistance;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import com.jedago.practica_dss.core.User;

public class UsersRepositoryOnMemory implements UsersRepository {
	private List<User> users;

	@Override
	public List<User> findAll() throws Exception {
		return users;
	}

	@Override
	public Optional<User> findById(String id) throws Exception {
		boolean found = false;
		User seekUser = null, u;
		
		Iterator<User> i = findAll().iterator();
		
		while(i.hasNext() && !found)
		{
			u = i.next();
			if(u.getIdUser()==id) 
			{
				seekUser = u;
				found = true;
			}
		}
		if(found)
			return Optional.of(seekUser);
		else
			return Optional.empty();
	}

	@Override
	public void save(List<User> usersList) throws Exception {
		this.users = usersList;
	}

	@Override
	public void add(User u) throws Exception {
		assert(u.getAge()>=18);
		this.users.add(u);
	}

	@Override
	public void delete(List<User> usersList) throws Exception {
		users.removeAll(usersList);
	}

	@Override
	public void delete(User u) throws Exception {
		users.remove(u);
	}

	@Override
	public void update(String id, User u) throws Exception {
		Optional<User> toUpdate = this.findById(id);
		if(!toUpdate.isPresent())
		{
			this.delete(toUpdate.get());
			this.add(u);
		}
	}
	
}
