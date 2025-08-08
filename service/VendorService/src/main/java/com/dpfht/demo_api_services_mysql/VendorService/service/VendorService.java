package com.dpfht.demo_api_services_mysql.VendorService.service;

import com.dpfht.demo_api_services_mysql.VendorService.model.Vendor;

import java.util.List;

public interface VendorService {

    public List<Vendor> findAll();
    public Vendor findById(Long id);
    public Vendor save(Vendor vendor);
    public Vendor update(Long id, Vendor vendor);
    public void delete(Long id);
}
