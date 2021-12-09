package com.bookstore.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.bookstore.entity.Users;

public class UserDAO extends JpaDAO<Users> implements GenericDAO<Users> {

	public UserDAO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Users create(Users user) {
		return super.create(user);
		// delegate call to its parent class
	}

	@Override
	public Users update(Users user) {
		// TODO Auto-generated method stub
		return super.update(user);
	}

	@Override
	public Users get(Object userId) {
		// TODO Auto-generated method stub
		return super.find(Users.class, userId);
	}

	public Users findByEmail(String email) {
		List<Users> listUsers = super.findWithNamedQuery("Users.findByEmail", "email", email);
		if (listUsers != null && listUsers.size() >= 1) {
			return listUsers.get(0);
		}
		return null;
	}
	
	public boolean checkLogin(String email,String password) {
		Map<String,Object>parameters=new HashMap<>();
		parameters.put("email", email);
		parameters.put("password", password);
		List<Users> listUsers = super.findWithNamedQuery("Users.checkLogin", parameters);
		if(listUsers != null && listUsers.size()==1) {
			return true;
		}
		return false;
	}

	@Override
	public void delete(Object userId) {
		// TODO Auto-generated method stub
		super.delete(Users.class, userId);

	}

	@Override
	public List<Users> listAll() {
		// TODO Auto-generated method stub
		return super.findWithNamedQuery("Users.findAll");
	}

	@Override
	public long count() {
		// TODO Auto-generated method stub
		return super.countWithNamedQuery("Users.countAll");
	}

}
