package com.bannrx.common_service.repository;

import com.bannrx.common_service.entities.Address;
import com.bannrx.common_service.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface AddressRepository extends JpaRepository<Address, String> {
}
