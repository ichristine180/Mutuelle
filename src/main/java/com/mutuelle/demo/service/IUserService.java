package com.mutuelle.demo.service;

import java.util.List;


import java.util.Set;

import com.mutuelle.demo.model.security.Role;
import com.mutuelle.demo.model.security.UserRole;
import com.mutuelle.demo.model.security.Users;



public interface IUserService
{
    Users findByUsername(String username);

    Users findByEmail(String email);

    boolean checkUserExists(String username, String email);

    boolean checkUsernameExists(String username);

    boolean checkEmailExists(String email);

    void save(Users user);

  //  Users createUser(Users user, Set<Erole> eroles);

    Users saveUser(Users user);

    List<Users> findUserList();

    void enableUser(String username);

    void disableUser(String username);

    void updatePassword(String updatedPassword, String username);

	Users createUser(Users user, Set<UserRole> userRoles);
	Role findByName(String rolename);
	Iterable<Role> findAllRole();

	Users encryptPass(Users password);

}
