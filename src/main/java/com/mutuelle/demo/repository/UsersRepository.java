package com.mutuelle.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.mutuelle.demo.model.security.User;


@Repository
public interface UsersRepository extends CrudRepository<User, Long>
{

    User findByuserName(String username);

    User findByEmail(String email);

    @Override
    List<User> findAll();

    @Modifying
    @Query("update User u set u.password = :password where u.userName = :userName")
    void updatePassword(@Param("password") String password, @Param("userName") String userName);
}
