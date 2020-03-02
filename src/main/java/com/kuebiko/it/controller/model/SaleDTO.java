package com.kuebiko.it.controller.model;

import java.math.BigDecimal;
import java.util.UUID;
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
public class SaleDTO {

  private int id;

  private String productId;

  private int quantity;

  private BigDecimal pricePerUnit;

  private UUID invoiceId;
}
