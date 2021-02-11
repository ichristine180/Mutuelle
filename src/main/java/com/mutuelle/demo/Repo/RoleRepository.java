package com.mutuelle.demo.Repo;

import org.springframework.data.repository.CrudRepository;

import org.springframework.stereotype.Repository;

import com.mutuelle.demo.Model.security.Role;

@Repository
public interface RoleRepository extends CrudRepository<Role, Integer> {
	Role findByName(String name);
}