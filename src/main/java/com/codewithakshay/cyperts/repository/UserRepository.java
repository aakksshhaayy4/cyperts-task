package com.codewithakshay.cyperts.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.codewithakshay.cyperts.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

}
