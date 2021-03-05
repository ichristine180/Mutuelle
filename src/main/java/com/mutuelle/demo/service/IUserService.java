package com.mutuelle.demo.service;

import java.util.List;
import java.util.Set;

import com.mutuelle.demo.model.security.Erole;
import com.mutuelle.demo.model.security.User;


public interface IUserService
{
    User findByUsername(String username);

    User findByEmail(String email);

    boolean checkUserExists(String username, String email);

    boolean checkUsernameExists(String username);

    boolean checkEmailExists(String email);

    void save(User user);

    User createUser(User user, Set<Erole> eroles);

    User saveUser(User user);

    List<User> findUserList();

    void enableUser(String username);

    void disableUser(String username);

    void updatePassword(String updatedPassword, String username);

}
