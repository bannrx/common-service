package com.bannrx.common_service.repository;

import com.bannrx.common_service.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface UserRepository extends JpaRepository<User, String> {

    Optional<User> findByPhoneNo(String phoneNo);
}
