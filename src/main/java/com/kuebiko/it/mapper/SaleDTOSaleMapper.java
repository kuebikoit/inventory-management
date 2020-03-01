package com.kuebiko.it.mapper;

import com.kuebiko.it.controller.model.SaleDTO;
import com.kuebiko.it.persistence.model.Sale;
import java.util.UUID;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface SaleDTOSaleMapper {

  @Mapping(target = "invoiceId", source = "invoiceId", qualifiedByName = "UUIDToString")
  Sale saleDTOToSale(SaleDTO saleDTO);

  @Named("UUIDToString")
  default String from(UUID uuid) {
    return uuid.toString();
  }
}
