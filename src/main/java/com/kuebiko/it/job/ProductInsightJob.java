package com.kuebiko.it.job;

import com.kuebiko.it.persistence.model.ProductInsight;
import com.kuebiko.it.persistence.model.Sale;
import com.kuebiko.it.persistence.model.repository.ProductInsightRepository;
import com.kuebiko.it.service.SaleService;
import java.util.Comparator;
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

    productInsightRepository.saveAll(
        saleService
            .mostRecent50Sales()
            .stream()
            .map(Sale::getProduct)
            .sorted(Comparator.comparingDouble(p -> p.getQuantity() * p.getProfitPercentage()))
            .map(
                product -> {
                  ProductInsight productInsight = new ProductInsight();
                  productInsight.setProduct(product);
                  productInsight.setOrderQuantity(100 - product.getQuantity());
                  return productInsight;
                })
            .limit(10)
            .collect(Collectors.toList()));
  }
}
