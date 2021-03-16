package com.mutuelle.demo.service;

import java.util.List;
import java.util.Set;

import com.mutuelle.demo.model.security.Role;
import com.mutuelle.demo.model.security.User;
import com.mutuelle.demo.model.security.UserRole;



public interface IUserService
{
    User findByUsername(String username);

    User findByEmail(String email);
    User findById(Long id);

    boolean checkUserExists(String username, String email);

    boolean checkUsernameExists(String username);
    boolean checkExitsOnUpdate(String username,long id);
    boolean checkEmailExists(String email);

    void save(User user);

    User saveUser(User user);

    List<User> findUserList();

    void enableUser(String username);

    void disableUser(String username);

    void updatePassword(String updatedPassword, String username);

    User createUser(User user, Set<UserRole> userRoles);
	Role findByName(String rolename);
	Iterable<Role> findAllRole();

	User encryptPass(User password);

	User updateUser(User user);

	void delete(User user);

	boolean checkIfValidOldPassword(User user, String oldPassword);

	void changeUserPassword(User user, String password);

}
