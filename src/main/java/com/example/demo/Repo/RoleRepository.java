package com.example.demo.Repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.Model.security.Role;

@Repository
public interface RoleRepository extends CrudRepository<Role, Integer> {
	Role findByName(String name);
}