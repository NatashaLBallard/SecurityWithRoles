package com.securitywithroles.demo;


import org.springframework.data.repository.CrudRepository;

public interface RoleRepository extends CrudRepository<RoleClass, Long>{
    RoleClass findByRole(String role);
}
