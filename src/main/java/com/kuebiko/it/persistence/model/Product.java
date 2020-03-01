package com.kuebiko.it.persistence.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.Instant;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;

@Entity
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Product implements Serializable {

  public static final BigDecimal ONE_HUNDRED = new BigDecimal(100);

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  private String name;

  private String upc;

  private int quantity;

  @Enumerated(EnumType.STRING)
  @NotNull
  private Category category;

  @NotNull private BigDecimal costAmount;

  @NotNull private BigDecimal saleAmount;

  @Transient private double profitPercentage;

  @ManyToOne
  @JoinColumn(name = "vendor_id")
  private Vendor vendor;

  @CreationTimestamp private Instant createdAt;

  public double getProfitPercentage() {
    BigDecimal profitMargin =
        saleAmount.subtract(costAmount).divide(costAmount, 3, RoundingMode.HALF_UP);

    return profitMargin.multiply(ONE_HUNDRED).doubleValue();
  }
}
