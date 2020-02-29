package com.kuebiko.it.persistence.model.repository;

import com.kuebiko.it.persistence.model.Category;
import com.kuebiko.it.persistence.model.Product;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

  List<Product> findByCategory(Category category);
}
