package com.kuebiko.it.persistence.model.repository;

import com.kuebiko.it.persistence.model.ProductInsight;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductInsightRepository extends JpaRepository<ProductInsight, Long> {
  Optional<ProductInsight> findByProductId(Long productId);
}
