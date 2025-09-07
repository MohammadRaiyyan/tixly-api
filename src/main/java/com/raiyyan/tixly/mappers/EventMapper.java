package com.raiyyan.tixly.mappers;

import com.raiyyan.tixly.domain.CreateEventRequest;
import com.raiyyan.tixly.domain.CreateTicketTypeRequest;
import com.raiyyan.tixly.domain.dtos.CreateEventRequestDto;
import com.raiyyan.tixly.domain.dtos.CreateEventResponseDto;
import com.raiyyan.tixly.domain.dtos.CreateTicketTypeRequestDto;
import com.raiyyan.tixly.domain.entities.Event;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.springframework.data.repository.query.parser.Part;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface EventMapper {
  CreateEventRequest fromDto(CreateEventRequestDto eventRequestDto);
  CreateTicketTypeRequest fromDto(CreateTicketTypeRequestDto typeRequestDto);

  CreateEventResponseDto toDto(Event event);
}
