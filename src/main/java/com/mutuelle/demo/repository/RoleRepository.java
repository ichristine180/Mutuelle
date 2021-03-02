package com.mutuelle.demo.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.mutuelle.demo.model.security.Role;


@Repository
public interface RoleRepository extends CrudRepository<Role, Integer>
{
    Role findByName(String name);
}