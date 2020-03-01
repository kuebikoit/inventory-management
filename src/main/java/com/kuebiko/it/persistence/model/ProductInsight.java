package com.kuebiko.it.persistence.model;

import java.time.Instant;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class ProductInsight {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  private int orderQuantity;

  private Instant suggestedOrderDate;

  @ManyToOne
  @JoinColumn(name = "product_id", nullable = false)
  private Product product;
}
