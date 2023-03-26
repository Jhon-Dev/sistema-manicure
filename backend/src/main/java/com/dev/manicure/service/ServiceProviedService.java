package com.dev.manicure.service;

import com.dev.manicure.entity.ServiceProvied;
import com.dev.manicure.entity.User;
import com.dev.manicure.repository.ServiceProviedRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServiceProviedService {

    @Autowired
    ServiceProviedRepository serviceProviedRepository;

    public List<ServiceProvied> serviceProviedList() {
        List<ServiceProvied> serviceProvieds = serviceProviedRepository.findAll();
        return serviceProvieds;
    }

    public List<ServiceProvied> findByUserId(Long id) {
        return serviceProviedRepository.findByServiceProviedUser(id);
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

    public ServiceProvied updateServiceProvied(Long id, ServiceProvied serviceProviedHolder) {
        ServiceProvied serviceProvied = serviceProviedRepository.findById(id).orElseThrow(() -> new UsernameNotFoundException("User not found!"));
        serviceProvied.setServiceType(serviceProviedHolder.getServiceType());
        serviceProvied.setValor(serviceProviedHolder.getValor());
        serviceProvied.setData(serviceProviedHolder.getData());
        serviceProvied.setUser(serviceProviedHolder.getUser());
        return serviceProviedRepository.save(serviceProvied);
    }
}
