package com.kuebiko.it.service;

import com.kuebiko.it.controller.model.ProductInsight;
import com.kuebiko.it.persistence.model.Category;
import com.kuebiko.it.persistence.model.Product;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductInsightRuleService {

  private final KieContainer kieContainer;

  private final ProductService productService;

  public List<ProductInsight> suggestOrderQuantity(Category category) {

    return productService
        .productList(category)
        .stream()
        .map(this::suggestOrderQuantity)
        .collect(Collectors.toList());
  }

  public ProductInsight suggestOrderQuantity(Product product) {
    ProductInsight productInsight = new ProductInsight();
    KieSession kieSession = kieContainer.newKieSession();
    kieSession.setGlobal("productInsight", productInsight);
    kieSession.insert(product);
    kieSession.fireAllRules();
    kieSession.dispose();

    return productInsight;
  }
}
