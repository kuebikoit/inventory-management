package com.kuebiko.it.persistence.model;

import java.time.Instant;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
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

  private int orderQuantity;

  @Enumerated(EnumType.STRING)
  private Priority priority = Priority.LOW;

  private Instant suggestedOrderDate;

  @NotNull private Long productId;

  @UpdateTimestamp private Instant updatedAt;
}
