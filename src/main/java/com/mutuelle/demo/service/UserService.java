package com.mutuelle.demo.service;





import java.util.List;

import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mutuelle.demo.Model.User;
import com.mutuelle.demo.Model.security.Erole;
import com.mutuelle.demo.Repo.RoleRepository;
import com.mutuelle.demo.Repo.UsersRepository;



@Service
@Transactional
public class UserService implements IUserService {

	private static final Logger LOG = LoggerFactory.getLogger(IUserService.class);

	@Autowired
	private UsersRepository usersRepository;

	@Autowired
	private RoleRepository roleRepository;


	@Override
	public void save(User user) {
		usersRepository.save(user);
	}

	@Override
	public User findByUsername(String username) {
		return usersRepository.findByusername(username);
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
	public List<User> findUserList() {
		return usersRepository.findAll();
	}

	@Override
	public void enableUser(String username) {
		User user = findByUsername(username);

		usersRepository.save(user);
	}

	@Override
	public void disableUser(String username) {
		User user = findByUsername(username);
		usersRepository.save(user);
	}

	@Override
	public void updatePassword(String updatedPassword, String userName) {
		usersRepository.updatePassword(updatedPassword, userName);
	}

	@Override
	public User createUser(User user, Set<Erole> eroles) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User saveUser(User user) {
		// TODO Auto-generated method stub
		return null;
	}

	
	@Override
	public User findByEmail(String email) {
		// TODO Auto-generated method stub
		return null;
	}

	
	

}



