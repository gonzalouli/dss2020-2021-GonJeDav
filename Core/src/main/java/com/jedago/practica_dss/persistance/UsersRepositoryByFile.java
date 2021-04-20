package com.jedago.practica_dss.persistance;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import com.jedago.practica_dss.core.User;

public class UsersRepositoryByFile implements UsersRepository {
	
	/**
	 * To check if the persistance file is created
	 * @return true if the persistance file is created
	 */
	public static boolean isFileCreated()
	{
		File ProductsFile = new File(Messages.getString("UsersFile"));
		return ProductsFile.exists();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<User> readUsers() throws Exception {
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
	public void writeUsers(List<User> usersList) throws Exception {
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
	public void saveUser(User u) throws Exception {
		List<User> usersList = readUsers();
		usersList.add(u);
		writeUsers(usersList);
	}


}
