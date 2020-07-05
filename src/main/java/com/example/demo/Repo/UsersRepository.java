package com.example.demo.Repo;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.example.demo.Model.security.Users;

public interface UsersRepository extends CrudRepository<Users, Long> {

	Users findByuserName(String username);

	Users findByEmail(String email);

	@Override
	List<Users> findAll();

	@Modifying
	@Query("update User u set u.password = :password where u.id = :id")
	void updatePassword(@Param("password") String password, @Param("id") Long id);
}
