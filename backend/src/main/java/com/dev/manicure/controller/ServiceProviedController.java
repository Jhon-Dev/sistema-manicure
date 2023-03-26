package com.dev.manicure.controller;

import com.dev.manicure.entity.ServiceProvied;
import com.dev.manicure.service.ServiceProviedService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api")
@RequiredArgsConstructor
public class ServiceProviedController {

    @Autowired
    ServiceProviedService serviceProviedService;


    @PostMapping("/serviceprovied/register")
    public ResponseEntity<ServiceProvied> register(
            @RequestBody ServiceProvied serviceProvied) {
        return ResponseEntity.ok(serviceProviedService.register(serviceProvied));
    }
    @GetMapping(value = "/serviceprovied/list")
    public List<ServiceProvied> serviceProviedList() {
        return serviceProviedService.serviceProviedList();
    }

    @GetMapping(value = "/serviceprovied/findByUserId/{id}")
    public ResponseEntity<List<ServiceProvied>> getByUserId(@PathVariable(value = "id") Long id) {
        List<ServiceProvied> serviceProviedList = serviceProviedService.findByUserId(id);
        return ResponseEntity.ok(serviceProviedList);
    }
}
