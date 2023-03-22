package com.dev.manicure.service;

import com.dev.manicure.entity.ServiceProvied;
import com.dev.manicure.repository.ServiceProviedRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;

@Service
public class ServiceProviedService {

    @Autowired
    ServiceProviedRepository serviceProviedRepository;

    @ExceptionHandler
    public List<ServiceProvied> serviceProviedList() {
        List<ServiceProvied> serviceProvieds = serviceProviedRepository.findAll();
        return serviceProvieds;
    }

    public ServiceProvied register(ServiceProvied serviceProvied) {
        serviceProvied = serviceProvied.builder()
                .serviceType(serviceProvied.getServiceType())
                .valor(serviceProvied.getValor())
                .data(serviceProvied.getData())
                .user(serviceProvied.getUser())
                .build();
        var saveServiceProvied = serviceProviedRepository.save(serviceProvied);

        return saveServiceProvied;
    }
}
