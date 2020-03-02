package com.kuebiko.it.persistence.model;

import java.math.BigDecimal;
import java.time.Instant;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Getter
@Setter
@ToString
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class ProductInsight {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  private double profitPercentage;

  private BigDecimal averageSaleAmount;

  private BigDecimal totalProfitAmount;

  @NotNull private Long productId;

  private String productName;

  @UpdateTimestamp private Instant updatedAt;
}
