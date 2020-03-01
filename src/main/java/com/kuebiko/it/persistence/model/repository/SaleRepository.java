package com.kuebiko.it.persistence.model.repository;

import com.kuebiko.it.persistence.model.Sale;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SaleRepository extends JpaRepository<Sale, Long> {

  List<Sale> findTop50ByOrderByCreatedAtDesc();
}
