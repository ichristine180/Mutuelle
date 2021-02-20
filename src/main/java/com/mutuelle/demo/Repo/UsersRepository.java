package com.mutuelle.demo.Repo;

import java.util.List;


import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.mutuelle.demo.Model.security.Users;

@Repository
public interface UsersRepository extends CrudRepository<Users, Long> {

	Users findByuserName(String username);

	Users findByEmail(String email);

	@Override
	List<Users> findAll();

	@Modifying
	@Query("update Users u set u.password = :password where u.userName = :userName")
	void updatePassword(@Param("password") String password, @Param("userName") String userName);
}
