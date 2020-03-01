package com.kuebiko.it.service;

import com.kuebiko.it.persistence.model.Priority;
import com.kuebiko.it.persistence.model.Product;
import com.kuebiko.it.persistence.model.ProductInsight;
import com.kuebiko.it.persistence.model.repository.ProductRepository;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductInsightService {

  private static final Map<Priority, Integer> PRIORITY_COUNT_MAP =
      Map.of(Priority.HIGH, 50, Priority.MEDIUM, 25, Priority.LOW, 10);

  private final ProductRepository productRepository;

  public List<ProductInsight> insights() {
    return productRepository
        .findAll()
        .stream()
        .filter(this::isOrderRequired)
        .map(this::from)
        .collect(Collectors.toList());
  }

  private boolean isOrderRequired(Product product) {
    return false;
  }

  private ProductInsight from(Product product) {
    //    return ProductInsight.builder()
    //        //.product(product)
    //        .orderQuantity(orderCountRequired(product.getPriority(), product.getQuantity()))
    //        .suggestedOrderDate(Instant.now())
    //        .build();
    return new ProductInsight();
  }

  private int orderCountRequired(Priority priority, int count) {
    return PRIORITY_COUNT_MAP.get(priority) - count;
  }
}
