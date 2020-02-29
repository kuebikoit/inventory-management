package com.kuebiko.it.controller;

import com.kuebiko.it.controller.model.ProductInsight;
import com.kuebiko.it.persistence.model.Category;
import com.kuebiko.it.service.ProductInsightRuleService;
import com.kuebiko.it.service.ProductInsightService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/products/insights")
public class ProductInsightController {

  private final ProductInsightService productInsightService;
  private final ProductInsightRuleService productInsightRuleService;

  @GetMapping
  public List<ProductInsight> insights() {
    log.info("Get product insights");

    return productInsightService.insights();
  }

  @GetMapping("/rules")
  public List<ProductInsight> ruleInsight(
      @RequestParam(value = "category", required = false) Category category) {
    log.info("Get product rule insight for category={}", category);

    return productInsightRuleService.suggestOrderQuantity(category);
  }
}
