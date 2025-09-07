package com.example.pricing.adapter.query.rest.mapper;

import static org.mapstruct.ReportingPolicy.ERROR;
import static org.mapstruct.factory.Mappers.getMapper;

import com.example.pricing.adapter.query.rest.dto.PriceQueryApiResponse;
import com.example.pricing.domain.Price;
import org.mapstruct.AnnotateWith;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/** Maps domain objects to data-transfer objects. */
@Mapper(typeConversionPolicy = ERROR, unmappedTargetPolicy = ERROR)
@AnnotateWith(value = lombok.Generated.class) // Exclude from coverage
public interface PriceQueryResponseMapper {

  /** An instance of this mapper. */
  PriceQueryResponseMapper INSTANCE = getMapper(PriceQueryResponseMapper.class);

  /**
   * Maps a domain object to a data-transfer object.
   *
   * @param domain The domain object.
   * @return A data-transfer object.
   */
  @Mapping(target = "price", source = "amount.value")
  @Mapping(target = "currency", source = "amount.currency")
  @Mapping(target = "startDate", source = "period.from")
  @Mapping(target = "endDate", source = "period.to")
  PriceQueryApiResponse toDto(Price domain);
}
