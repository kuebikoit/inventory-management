package com.kuebiko.it.service;

import com.google.common.base.Preconditions;
import com.kuebiko.it.controller.model.SaleDTO;
import com.kuebiko.it.mapper.SaleDTOSaleMapper;
import com.kuebiko.it.persistence.model.Product;
import com.kuebiko.it.persistence.model.Sale;
import com.kuebiko.it.persistence.model.SaleAggregate;
import com.kuebiko.it.persistence.model.repository.SaleRepository;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

@Slf4j
@Service
@RequiredArgsConstructor
public class SaleService {

  private final SaleRepository saleRepository;

  private final SaleDTOSaleMapper saleDTOSaleMapper;

  private final ProductService productService;

  public void loadSales(List<SaleDTO> saleDTOs) {
    Preconditions.checkArgument(!CollectionUtils.isEmpty(saleDTOs));

    List<Sale> sales = new ArrayList<>();

    for (SaleDTO saleDTO : saleDTOs) {
      Sale sale = saleDTOSaleMapper.saleDTOToSale(saleDTO);
      addProductInPlace(Long.valueOf(saleDTO.getProductId()), sale);

      sales.add(sale);
    }

    saleRepository.saveAll(sales);
  }

  private void addProductInPlace(Long productId, Sale sale) {
    Product product = productService.from(productId);
    product.setQuantity(product.getQuantity() - sale.getQuantity());
    productService.save(product);

    sale.setProduct(product);
  }

  public List<Sale> mostRecent50Sales() {
    return saleRepository.findTop50ByOrderByCreatedAtDesc();
  }

  public List<SaleAggregate> saleAggregates() {
    return saleRepository.findProductSaleCount();
  }
}
