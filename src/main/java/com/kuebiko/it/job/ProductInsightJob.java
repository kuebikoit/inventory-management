package com.kuebiko.it.job;

import static com.kuebiko.it.persistence.model.Product.ONE_HUNDRED;

import com.kuebiko.it.persistence.model.Product;
import com.kuebiko.it.persistence.model.ProductInsight;
import com.kuebiko.it.persistence.model.SaleAggregate;
import com.kuebiko.it.persistence.model.repository.ProductInsightRepository;
import com.kuebiko.it.service.SaleService;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.Instant;
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

    saleService
        .saleAggregates(Instant.now().minusSeconds(30))
        .stream()
        .map(this::from)
        .limit(10)
        .collect(Collectors.toList());
  }

  private ProductInsight from(SaleAggregate saleAggregate) {
    Product product = saleAggregate.getProduct();
    log.info("product id={}, profit={}", product.getId(), product.getProfitPercentage());

    BigDecimal avgPrice = BigDecimal.valueOf(saleAggregate.getAveragePricePerUnit());
    double profitPercent = profitPercentage(product.getCostAmount(), avgPrice);

    ProductInsight productInsight =
        new ProductInsight()
            .setProductId(product.getId())
            .setProductName(product.getName())
            .setAverageSaleAmount(avgPrice)
            .setProfitPercentage(profitPercent)
            .setTotalProfitAmount(
                totalProfit(product.getCostAmount(), avgPrice, saleAggregate.getTotalQuantity()));

    productInsightRepository.save(productInsight);

    return productInsight;
  }

  private ProductInsight getOrDefault(
      Long productId, Supplier<ProductInsight> productInsightSupplier) {
    return productInsightRepository
        .findByProductId(productId)
        .orElse(productInsightSupplier.get().setProductId(productId));
  }

  private double profitPercentage(BigDecimal costAmount, BigDecimal saleAmount) {
    BigDecimal profitMargin =
        saleAmount.subtract(costAmount).divide(costAmount, 3, RoundingMode.HALF_UP);

    return profitMargin.multiply(ONE_HUNDRED).doubleValue();
  }

  private BigDecimal totalProfit(BigDecimal costAmount, BigDecimal saleAmount, long quantity) {
    return saleAmount.subtract(costAmount).multiply(BigDecimal.valueOf(quantity));
  }
}
