package com.dpfht.demo_api_services_mysql.repository;

import com.dpfht.demo_api_services_mysql.model.Vendor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VendorRepository extends JpaRepository<Vendor, Long> {}
