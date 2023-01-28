package com.openroad.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.openroad.api.model.Admin;

@Repository
public interface AdminRepository extends JpaRepository<Admin, String> {

}
