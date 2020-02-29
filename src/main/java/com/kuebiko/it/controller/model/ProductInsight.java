package com.kuebiko.it.controller.model;

import com.kuebiko.it.persistence.model.Product;
import java.time.Instant;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ProductInsight {

  private Product product;

  private int orderQuantity;

  private Instant suggestedOrderDate;
}
