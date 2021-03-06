package com.mutuelle.demo.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.mutuelle.demo.model.security.User;
import com.mutuelle.demo.repository.UsersRepository;


@Service
public class UserSecurityService implements UserDetailsService
{

    /** The application logger */
    private static final Logger LOG = LoggerFactory.getLogger(UserSecurityService.class);

    @Autowired
    private UsersRepository userDao;

    @Override
    public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException
    {
        final User user = userDao.findByUsername(username);
        if (null == user)
        {
            LOG.warn("Username {} not found", username);
            throw new UsernameNotFoundException("Username " + username + " not found");
        }
        return user;
    }
}
