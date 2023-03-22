package com.dev.manicure.repository;

import com.dev.manicure.entity.ServiceProvied;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServiceProviedRepository extends JpaRepository<ServiceProvied, Long> {

}
