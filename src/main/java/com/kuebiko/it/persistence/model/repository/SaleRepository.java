package com.kuebiko.it.persistence.model.repository;

import com.kuebiko.it.persistence.model.Sale;
import com.kuebiko.it.persistence.model.SaleAggregate;
import java.time.Instant;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface SaleRepository extends JpaRepository<Sale, Long> {

  List<Sale> findTop50ByOrderByCreatedAtDesc();

  @Query(
      "SELECT "
          + "new com.kuebiko.it.persistence.model.SaleAggregate"
          + "(s.product, COUNT(s), SUM(s.quantity), avg(s.pricePerUnit + 0.0)) "
          + "FROM "
          + "Sale s "
          + "WHERE s.createdAt >= :createdAfter "
          + "GROUP BY "
          + "s.product.id")
  List<SaleAggregate> findProductSaleCount(Instant createdAfter);
}
