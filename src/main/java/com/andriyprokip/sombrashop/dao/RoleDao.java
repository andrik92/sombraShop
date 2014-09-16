package com.andriyprokip.sombrashop.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.andriyprokip.sombrashop.entity.Role;

@Repository
public interface RoleDao extends JpaRepository<Role, Integer>{

	Role findByName(String name);

}

