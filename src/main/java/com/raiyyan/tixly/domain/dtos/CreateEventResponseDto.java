package com.raiyyan.tixly.domain.dtos;

import com.raiyyan.tixly.domain.entities.EventStatusEnum;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateEventResponseDto {
  private  String name;

  private LocalDateTime start;
  private LocalDateTime end;

  private String venue;

  private LocalDateTime salesStart;
  private LocalDateTime salesEnd;

  private EventStatusEnum status;

  private List<CreateTicketTypeResponseDto> ticketTypes;

  private LocalDateTime createdAt;
  private LocalDateTime updatedAt;
}
