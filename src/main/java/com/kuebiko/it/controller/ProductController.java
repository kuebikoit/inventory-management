package com.kuebiko.it.controller;

import com.kuebiko.it.controller.model.BulkProductRequest;
import com.kuebiko.it.persistence.model.Category;
import com.kuebiko.it.persistence.model.Product;
import com.kuebiko.it.service.ProductService;
import java.util.List;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/products")
public class ProductController {

  private final ProductService productService;

  @PostMapping
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void load(@RequestBody @Valid BulkProductRequest bulkProductRequest) {
    log.info("Bulk product upload size={}", bulkProductRequest.getProducts().size());

    this.productService.loadProducts(bulkProductRequest.getProducts());
  }

  @GetMapping("/{id}")
  public Product getProduct(@PathVariable(value = "id") Long id) {
    log.info("Get product by id={}", id);

    return productService.from(id);
  }

  @GetMapping
  public List<Product> getProducts(
      @RequestParam(value = "category", required = false) Category category) {
    log.info("Get all products, category={}", category);

    return productService.productList(category);
  }
}
