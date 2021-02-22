package com.mutuelle.demo.Repo;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.mutuelle.demo.Model.User;




@Repository
public interface UsersRepository extends CrudRepository<User, Long> {

	User findByusername(String username);

	

	@Override
	List<User> findAll();

	@Modifying
	@Query("update User u set u.password = :password where u.username = :username")
	void updatePassword(@Param("password") String password, @Param("username") String username);



	User findByUsername(String username);


	
	
	
}
