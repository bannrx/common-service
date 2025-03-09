package com.bannrx.common_service.repository;

import com.bannrx.common_service.entities.Business;
import com.bannrx.common_service.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface BusinessRepository extends JpaRepository<Business, String> {
}
