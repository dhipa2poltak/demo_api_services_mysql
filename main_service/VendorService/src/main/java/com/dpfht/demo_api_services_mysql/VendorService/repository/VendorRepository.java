package com.dpfht.demo_api_services_mysql.VendorService.repository;

import com.dpfht.demo_api_services_mysql.VendorService.model.Vendor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VendorRepository extends JpaRepository<Vendor, Long> {}
