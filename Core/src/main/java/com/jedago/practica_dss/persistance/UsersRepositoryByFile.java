package com.jedago.practica_dss.persistance;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import com.jedago.practica_dss.core.User;

public class UsersRepositoryByFile implements UsersRepository {
	
	/**
	 * To check if the persistance file is created
	 * @return true if the persistance file is created
	 */
	public static boolean isFileCreated()
	{
		File UsersFile = new File(Messages.getString("UsersFile"));
		return UsersFile.exists();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<User> findAll() throws Exception {
		List<User> usersList =  new ArrayList<User>();
		if(isFileCreated()) //Si está creado el archivo, leemos su contenido
		{
			try {
				ObjectInputStream readUsers;
				readUsers = new ObjectInputStream(new FileInputStream(Messages.getString("UsersFile")));
				usersList = (List<User>) readUsers.readObject();
				readUsers.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		//Si no, devolvemos la lista vacía
		return usersList;
	}

	@Override
	public Optional<User> findById(int id) throws Exception {
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
		ObjectOutputStream writeUsers;
		try {
			writeUsers = new ObjectOutputStream(new FileOutputStream(Messages.getString("UsersFile")));
			writeUsers.writeObject(usersList);
			writeUsers.flush();
			writeUsers.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void add(User u) throws Exception {
		List<User> usersList = findAll();
		usersList.add(u);
		save(usersList);
	}

	@Override
	public void delete(List<User> usersList) throws Exception {
		List<User> currentUsers = findAll();
		currentUsers.removeAll(usersList);
		save(currentUsers);
	}

	@Override
	public void delete(User u) throws Exception {
		List<User> currentUsers = findAll();
		currentUsers.remove(u);
		save(currentUsers);
	}
	
	@Override
	public void update(int id, User u) throws Exception {
		Optional<User> toUpdate = this.findById(id);
		if(!toUpdate.isEmpty())
		{
			this.delete(toUpdate.get());
			this.add(u);
		}
	}

}
