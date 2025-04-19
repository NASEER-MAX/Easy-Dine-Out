package com.restaurent.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.restaurent.entity.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

	List<Role> findByUserId(Long id);
}
