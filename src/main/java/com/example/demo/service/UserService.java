package com.example.demo.service;


import java.util.List;

import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.Model.security.Erole;
import com.example.demo.Model.security.Users;
import com.example.demo.Repo.RoleRepository;
import com.example.demo.Repo.UsersRepository;

@Service
@Transactional
public class UserService implements IUserService {

	private static final Logger LOG = LoggerFactory.getLogger(IUserService.class);

	@Autowired
	private UsersRepository usersRepository;

	@Autowired
	private RoleRepository roleRepository;


	@Override
	public void save(Users user) {
		usersRepository.save(user);
	}

	@Override
	public Users findByUsername(String username) {
		return usersRepository.findByuserName(username);
	}

	@Override
	public Users findByEmail(String email) {
		return usersRepository.findByEmail(email);
	}

	@Override
	public Users createUser(Users user, Set<Erole> eroles) {
		Users localUser = usersRepository.findByuserName(user.getUserName());

		try {
			localUser = usersRepository.save(user);
		} catch (Exception e) {
			System.out.println(e.getMessage() + " JSJS");
		}
		return localUser;
	}

	@Override
	public boolean checkUserExists(String username, String email) {
		if (checkUsernameExists(username) || checkEmailExists(username)) {
			return true;
		}
		return false;
	}

	@Override
	public boolean checkUsernameExists(String username) {
		if (null != findByUsername(username)) {
			return true;
		}
		return false;
	}

	@Override
	public boolean checkEmailExists(String email) {
		if (null != findByEmail(email)) {
			return true;
		}

		return false;
	}

	@Override
	public Users saveUser(Users user) {
		return usersRepository.save(user);
	}

	@Override
	public List<Users> findUserList() {
		return usersRepository.findAll();
	}

	@Override
	public void enableUser(String username) {
		Users user = findByUsername(username);

		usersRepository.save(user);
	}

	@Override
	public void disableUser(String username) {
		Users user = findByUsername(username);
		usersRepository.save(user);
	}

	@Override
	public void updatePassword(String updatedPassword, String userName) {
		usersRepository.updatePassword(updatedPassword, userName);
	}


	

}
