package com.jedago.practica_dss.test;

import static org.junit.Assert.assertEquals;



import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import com.jedago.practica_dss.core.User;


public class TestUser {
	
	User user;
	
	@Before //Antes de cada test
	public void setUp() throws Exception {
		user= new User("Gonzalo","Ulibarri", LocalDate.of(1995,3,17), "12345678A");
	}

	@After
	public void tearDown() throws Exception {
		user = null;
	}
	
	
	@Test
	public void TestConstructor() 
	{
		assertEquals(user.getFirstName(), "Gonzalo" );
		assertEquals(user.getLastName(), "Ulibarri" );
		assertEquals(user.getBirthDate(), LocalDate.of(1995, 3, 17) );
		assertEquals(user.getDni(), "12345678A" );

	}
	
	@Test
	public void TestGetFirstName() 
	{	
		assertEquals(user.getFirstName(),"Gonzalo");
	}
	
	@Test
	public void TestSetFirstName() 
	{
		user.setFirstName("Mario");
		assertEquals(user.getFirstName(), "Mario");
	}
	
	@Test
	public void TestGetLastName() 
	{	
		assertEquals(user.getLastName(),"Ulibarri");
	}
	
	@Test
	public void TestSetLastName() 
	{
		user.setLastName("Garcia");
		assertEquals(user.getLastName(), "Garcia");
	}
	
	@Test
	public void TestGetDni() 
	{
		assertEquals(user.getDni(), "12345678A");
	}
	
	@Test
	public void TestSetDni() 
	{
		user.setDni("87654321B");
		assertEquals(user.getDni(), "87654321B");
	}
	
	@Test
	public void TestGetAge() 
	{
		assertEquals(user.getAge(), 26);
	}
	
}

