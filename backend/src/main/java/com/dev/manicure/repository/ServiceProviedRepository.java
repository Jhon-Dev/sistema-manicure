package com.dev.manicure.repository;

import com.dev.manicure.entity.ServiceProvied;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ServiceProviedRepository extends JpaRepository<ServiceProvied, Long> {

    @Query("SELECT service FROM ServiceProvied service WHERE service.user.id = :id")
    List<ServiceProvied> findByServiceProviedUser(Long id);

}
