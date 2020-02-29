package com.kuebiko.it.persistence.model.repository;

import com.kuebiko.it.persistence.model.Vendor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VendorRepository extends JpaRepository<Vendor, Long> {}
