package com.kuebiko.it.service;

import com.google.common.base.Preconditions;
import com.kuebiko.it.exception.ProductNotFoundException;
import com.kuebiko.it.persistence.model.Category;
import com.kuebiko.it.persistence.model.Product;
import com.kuebiko.it.persistence.model.repository.ProductRepository;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductService {

  private final ProductRepository productRepository;

  public void loadProducts(List<Product> products) {
    Preconditions.checkArgument(!CollectionUtils.isEmpty(products));

    productRepository.saveAll(products);
  }

  public List<Product> productList(Category category) {
    return Optional.ofNullable(category)
        .map(productRepository::findByCategory)
        .orElseGet(productRepository::findAll);
  }

  public Product from(Long id) {
    String exceptionMessage = String.format("Product not found with id=%s", id);

    return productRepository
        .findById(id)
        .orElseThrow(() -> new ProductNotFoundException(exceptionMessage));
  }
}
