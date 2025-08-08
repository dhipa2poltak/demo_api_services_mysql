package com.dpfht.demo_api_services_mysql.VendorService.service;

import com.dpfht.demo_api_services_mysql.VendorService.model.Vendor;
import com.dpfht.demo_api_services_mysql.VendorService.repository.VendorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VendorServiceImpl implements VendorService {

    @Autowired
    private VendorRepository repo;

    @Override
    public List<Vendor> findAll() {
        return repo.findAll();
    }

    @Override
    public Vendor findById(Long id) {
        return repo.findById(id).orElseThrow(() -> new RuntimeException("Vendor not found"));
    }

    @Override
    public Vendor save(Vendor vendor) {
        return repo.save(vendor);
    }

    @Override
    public Vendor update(Long id, Vendor vendor) {
        Vendor existing = findById(id);
        existing.setName(vendor.getName());

        return repo.save(existing);
    }

    @Override
    public void delete(Long id) {
        repo.deleteById(id);
    }
}
