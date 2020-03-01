package com.kuebiko.it.controller;

import com.kuebiko.it.controller.model.BulkSaleRequest;
import com.kuebiko.it.service.SaleService;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/sales")
public class SaleController {

  private final SaleService saleService;

  @PostMapping
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void load(@RequestBody @Valid BulkSaleRequest bulkSaleRequest) {
    log.info("Bulk product upload size={}", bulkSaleRequest.getSales().size());

    this.saleService.loadSales(bulkSaleRequest.getSales());
  }
}
