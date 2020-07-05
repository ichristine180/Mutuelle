package com.example.demo.Repo;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.Model.security.Role;

public interface RoleRepository extends CrudRepository<Role, Integer> {
	Role findByName(String name);
}