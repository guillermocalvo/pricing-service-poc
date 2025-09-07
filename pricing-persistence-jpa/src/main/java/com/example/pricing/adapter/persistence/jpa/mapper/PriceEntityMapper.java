package com.example.pricing.adapter.persistence.jpa.mapper;

import static org.mapstruct.ReportingPolicy.ERROR;
import static org.mapstruct.factory.Mappers.getMapper;

import com.example.pricing.adapter.persistence.jpa.entity.PriceEntity;
import com.example.pricing.domain.Price;
import org.mapstruct.AnnotateWith;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/** Maps entities to domain objects. */
@Mapper(typeConversionPolicy = ERROR, unmappedTargetPolicy = ERROR)
@AnnotateWith(value = lombok.Generated.class) // Exclude from coverage
public interface PriceEntityMapper {

  /** An instance of this mapper. */
  PriceEntityMapper INSTANCE = getMapper(PriceEntityMapper.class);

  /**
   * Maps an entity to a domain object.
   *
   * @param entity The entity.
   * @return A domain object.
   */
  @Mapping(target = "amount.value", source = "entity.price")
  @Mapping(target = "amount.currency", source = "entity.currency")
  @Mapping(target = "period.from", source = "entity.startDate")
  @Mapping(target = "period.to", source = "entity.endDate")
  Price toDomain(PriceEntity entity);
}
