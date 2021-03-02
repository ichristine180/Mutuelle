package com.mutuelle.demo.service;

import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mutuelle.demo.model.security.Erole;
import com.mutuelle.demo.model.security.Users;
import com.mutuelle.demo.repository.RoleRepository;
import com.mutuelle.demo.repository.UsersRepository;


@Service
@Transactional
public class UserService implements IUserService
{

    private static final Logger LOG = LoggerFactory.getLogger(IUserService.class);

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public void save(final Users user)
    {
        usersRepository.save(user);
    }

    @Override
    public Users findByUsername(final String username)
    {
        return usersRepository.findByuserName(username);
    }

    @Override
    public Users findByEmail(final String email)
    {
        return usersRepository.findByEmail(email);
    }

    @Override
    public boolean checkUserExists(final String username, final String email)
    {
        if (checkUsernameExists(username) || checkEmailExists(username))
        {
            return true;
        }
        return false;
    }

    @Override
    public boolean checkUsernameExists(final String username)
    {
        if (null != findByUsername(username))
        {
            return true;
        }
        return false;
    }

    @Override
    public boolean checkEmailExists(final String email)
    {
        if (null != findByEmail(email))
        {
            return true;
        }

        return false;
    }

    @Override
    public Users saveUser(final Users user)
    {
        return usersRepository.save(user);
    }

    @Override
    public List<Users> findUserList()
    {
        return usersRepository.findAll();
    }

    @Override
    public void enableUser(final String username)
    {
        final Users user = findByUsername(username);

        usersRepository.save(user);
    }

    @Override
    public void disableUser(final String username)
    {
        final Users user = findByUsername(username);
        usersRepository.save(user);
    }

    @Override
    public void updatePassword(final String updatedPassword, final String userName)
    {
        usersRepository.updatePassword(updatedPassword, userName);
    }

    @Override
    public Users createUser(final Users user, final Set<Erole> eroles)
    {
        // TODO Auto-generated method stub
        return null;
    }

}
