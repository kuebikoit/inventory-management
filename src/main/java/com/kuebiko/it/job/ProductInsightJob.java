package com.kuebiko.it.job;

import com.kuebiko.it.persistence.model.Priority;
import com.kuebiko.it.persistence.model.Product;
import com.kuebiko.it.persistence.model.ProductInsight;
import com.kuebiko.it.persistence.model.SaleAggregate;
import com.kuebiko.it.persistence.model.repository.ProductInsightRepository;
import com.kuebiko.it.service.SaleService;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class ProductInsightJob implements Job {

  private final SaleService saleService;

  private final ProductInsightRepository productInsightRepository;

  @Override
  public void execute(JobExecutionContext jobExecutionContext) {
    log.info("ProductInsightJob execution ...");

    saleService.saleAggregates().stream().map(this::from).limit(10).collect(Collectors.toList());
  }

  private ProductInsight from(SaleAggregate saleAggregate) {
    Product product = saleAggregate.getProduct();
    log.info("product id={}, profit={}", product.getId(), product.getProfitPercentage());

    ProductInsight productInsight = getOrDefault(product.getId(), ProductInsight::new);
    productInsight.setOrderQuantity(100 - product.getQuantity());
    productInsight.setPriority(priority(saleAggregate.getCount()));
    productInsightRepository.save(productInsight);

    return productInsight;
  }

  private ProductInsight getOrDefault(
      Long productId, Supplier<ProductInsight> productInsightSupplier) {
    return productInsightRepository
        .findByProductId(productId)
        .orElse(productInsightSupplier.get().setProductId(productId));
  }

  private Priority priority(long count) {
    if (count > 2) return Priority.HIGH;
    else return Priority.MEDIUM;
  }
}
