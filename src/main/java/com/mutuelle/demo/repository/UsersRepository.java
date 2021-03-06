package com.mutuelle.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.mutuelle.demo.model.security.Users;


@Repository
public interface UsersRepository extends CrudRepository<Users, Long>
{

    Users findByusername(String username);

    Users findByEmail(String email);

    @Override
    List<Users> findAll();

    @Modifying
    @Query("update Users u set u.password = :password where u.username = :username")
    void updatePassword(@Param("password") String password, @Param("username") String username);

	Users findByUserId(Long id);
	 @Query("select u from Users u where u.username = :username and u.userId !=:userId")
	Users findByUsernameAndUserId(@Param("username") String username, @Param("userId")long id);
}
