package com.kuebiko.it.persistence.model;

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
public class SaleAggregate {

  private Product product;

  private long totalCount;

  private long totalQuantity;

  private double averagePricePerUnit;
}
