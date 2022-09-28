package com.app.auction.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.auction.entities.Role;

public interface RoleRepository extends JpaRepository<Role, Integer> {

}
