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

	User findByusername(String username);

	User findByEmail(String email);

    @Override
    List<User> findAll();

    @Modifying
    @Query("update User u set u.password = :password where u.username = :username")
    void updatePassword(@Param("password") String password, @Param("username") String username);

    User findByUserId(Long id);
	 @Query("select u from User u where u.username = :username and u.userId !=:userId")
	 User findByUsernameAndUserId(@Param("username") String username, @Param("userId")long id);
}
