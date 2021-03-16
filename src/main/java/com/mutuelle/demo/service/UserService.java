package com.mutuelle.demo.service;

import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mutuelle.demo.model.security.Role;
import com.mutuelle.demo.model.security.User;
import com.mutuelle.demo.model.security.UserRole;
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

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public void save(final User user)
    {
        usersRepository.save(user);
    }

    @Override
    public User findByUsername(final String username)
    {
        return usersRepository.findByUsername(username);
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
    public User createUser(final User user, final Set<UserRole> userRoles)
    {
        for (final UserRole ur : userRoles)
        {
            roleRepository.save(ur.getRole());
        }
        user.getUserRoles().addAll(userRoles);
        final User localUser = usersRepository.findByUsername(user.getUsername());
        if (localUser != null)
        {
            LOG.info("Account with {} username, exists", user.getUsername());
        }
        else
        {
            final String encryptedPassword = passwordEncoder.encode(user.getPassword());
            user.setPassword(encryptedPassword);
            try
            {

                return usersRepository.save(user);
            }
            catch (final Exception e)
            {
                System.out.println(e.getMessage() + " JSJS");
                throw e;
            }
        }
        return localUser;
    }

    @Override
    public User updateUser(final User user)
    {
        try
        {

            return usersRepository.save(user);
        }
        catch (final Exception e)
        {
            System.out.println(e.getMessage() + " JSJS");
            throw e;
        }
    }

    @Override
    public Role findByName(final String rolename)
    {
        // TODO Auto-generated method stub
        try
        {
            return roleRepository.findByName(rolename);
        }
        catch (final Exception e)
        {
            System.out.println(e.getMessage() + " JSJS");
            throw e;
        }
    }

    @Override
    public Iterable<Role> findAllRole()
    {
        // TODO Auto-generated method stub
        try
        {
            return roleRepository.findAll();
        }
        catch (final Exception e)
        {
            System.out.println(e.getMessage() + " JSJS");
            throw e;
        }
    }

    @Override
    public User encryptPass(final User password)
    {
        final String encryptedPassword = passwordEncoder.encode(password.getPassword());
        password.setPassword(encryptedPassword);

        return password;
    }

    @Override
    public User findById(final Long id)
    {
        try
        {
            return usersRepository.findByUserId(id);
        }
        catch (final Exception e)
        {
            System.out.println(e.getMessage() + " JSJS");
            throw e;
        }
    }

    @Override
    public boolean checkExitsOnUpdate(final String username, final long id)
    {
        System.out.println(usersRepository.findByUsernameAndUserId(username, id));
        if (null != usersRepository.findByUsernameAndUserId(username, id))
        {
            return true;
        }
        return false;
    }

    @Override
    public void delete(final User user)
    {
        try
        {
            usersRepository.delete(user);
        }
        catch (final Exception e)
        {
            System.out.println(e.getMessage() + " JSJS");
            throw e;
        }
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
        user.setEnabled(false);
        usersRepository.save(user);
    }

    @Override
    public void updatePassword(final String updatedPassword, final String userName)
    {
        usersRepository.updatePassword(updatedPassword, userName);
    }


	  @Override
	    public boolean checkIfValidOldPassword(final User user, final String oldPassword) {
	        return passwordEncoder.matches(oldPassword, user.getPassword());
	  }
	  @Override
	    public void changeUserPassword(final User user, final String password) {
	        user.setPassword(passwordEncoder.encode(password));
	        usersRepository.save(user);
	    }

}

