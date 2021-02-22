package com.mutuelle.demo.service;

import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.mutuelle.demo.Model.Employee;
import com.mutuelle.demo.Model.User;
import com.mutuelle.demo.Model.security.Role;
import com.mutuelle.demo.Model.security.UserRole;
import com.mutuelle.demo.Repo.IEmployeeRepository;
import com.mutuelle.demo.Repo.RoleRepository;
import com.mutuelle.demo.Repo.UsersRepository;




@Service(IEmployeeService.NAME)
@Transactional
public class EmployeeService implements IEmployeeService{
	
	private static final Logger LOG = LoggerFactory.getLogger(IEmployeeService.class);
	
	@Autowired
	private IEmployeeRepository employeeRepository;
	
	@Autowired
	private RoleRepository roleRepo;
	
	@Autowired
	private UsersRepository userDao;
	
	
	@Autowired
	private IEmployeeRepository workerrepo;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	
	public List<Employee> listAll() {
		return employeeRepository.findAll();
	}

	public void save(Employee emp) {
		employeeRepository.save(emp);
	}
	
	public long countWorkers() {
		try {
			return workerrepo.count();
		} catch (Exception e) {
			throw e;
		}

	}
	
	@Override
	public Employee findEmployeeByEmployeeNumber(String employeeNumber) {
		try {
			return employeeRepository.findByEmployeeNumber(employeeNumber);
		} catch (Exception ex) {
			throw ex;
		}
	}


	public Iterable<Role> findAll() {
		try {
			return roleRepo.findAll();
		} catch (Exception e) {
			throw e;
		}

	}

	@Override
	public void save(User user) {
		userDao.save(user);
	}

	@Override
	public boolean checkUserExists(String username) {
		if (checkUsernameExists(username)) {
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
	public User createUser(User user) {
		User localUser = userDao.findByUsername(user.getUsername());
		if (localUser != null) {
			LOG.info("Account with {} username, exists", user.getUsername());
		} else {
			String encryptedPassword = passwordEncoder.encode(user.getPassword());
			user.setPassword(encryptedPassword);
			
			try {
				localUser = userDao.save(user);
			} catch (Exception e) {
				System.out.println(e.getMessage() + " JSJS");
			}
		}
		return localUser;
	}
	@Override
	public User encryptPass(User password) {
		String encryptedPassword = passwordEncoder.encode(password.getPassword());
		password.setPassword(encryptedPassword);

		return password;
	}

	
	@Override
	public User createUser(User user, Set<UserRole> userRoles) {
		for (UserRole ur : userRoles) {
			roleRepo.save(ur.getRole());
		}
		user.getUserRoles().addAll(userRoles);
		try {

			return userDao.save(user);
		} catch (Exception e) {
			System.out.println(e.getMessage() + " JSJS");
			throw e;
		}
	}
	@Override
	public User saveUser(User user) {
		return userDao.save(user);
	}
	
	@Override
	public List<User> findUserList() {
		return userDao.findAll();
	}
	
	
	@Override
	public User findByUsername(String username) {
		return userDao.findByUsername(username);
	}
	
	@Override
	public Role findByName(String rolename) {
		try {
			return roleRepo.findByName(rolename);
		} catch (Exception ex) {
			throw ex;
		}
		
		
	}

	@Override
	public List<Employee> findAllEmployees() {
		return employeeRepository.findAll();
	}
	
	@Override
	public Employee createEmployee(Employee employee) {
		return employeeRepository.save(employee);
	}

	

	
	@Override
	public Employee findByEmployeeId(Long id) {
		try {
			return employeeRepository.findByEmployeeId(id);
		} catch (Exception e) {
			throw e;
		}

	}

	
	
	public Employee get(long id) {
		return employeeRepository.findByEmployeeId(id);

	}

}

	
	

	

	
	
	

	
		



	

	

	
	
	
	



