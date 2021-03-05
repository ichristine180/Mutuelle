package com.mutuelle.demo.service;

import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mutuelle.demo.model.security.Erole;
import com.mutuelle.demo.model.security.User;
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
    public void save(final User user)
    {
        usersRepository.save(user);
    }

    @Override
    public User findByUsername(final String username)
    {
        return usersRepository.findByuserName(username);
    }

    @Override
    public User findByEmail(final String email)
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
    public User saveUser(final User user)
    {
        return usersRepository.save(user);
    }

    @Override
    public List<User> findUserList()
    {
        return usersRepository.findAll();
    }

    @Override
    public void enableUser(final String username)
    {
        final User user = findByUsername(username);

        usersRepository.save(user);
    }

    @Override
    public void disableUser(final String username)
    {
        final User user = findByUsername(username);
        usersRepository.save(user);
    }

    @Override
    public void updatePassword(final String updatedPassword, final String userName)
    {
        usersRepository.updatePassword(updatedPassword, userName);
    }

    @Override
    public User createUser(final User user, final Set<Erole> eroles)
    {
        // TODO Auto-generated method stub
        return null;
    }

}
