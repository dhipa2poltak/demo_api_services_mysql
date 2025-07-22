package com.dpfht.demo_api_services_mysql.controller;

import com.dpfht.demo_api_services_mysql.model.Vendor;
import com.dpfht.demo_api_services_mysql.service.VendorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/vendors")
public class VendorController {

    @Autowired
    private VendorService service;

    @GetMapping
    public List<Vendor> getAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Vendor> getById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @PostMapping
    public ResponseEntity<Vendor> create(@RequestBody Vendor vendor) {
        return ResponseEntity.ok(service.save(vendor));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Vendor> update(@PathVariable Long id, @RequestBody Vendor vendor) {
        return ResponseEntity.ok(service.update(id, vendor));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        service.delete(id);

        return ResponseEntity.noContent().build();
    }
}
