package com.raiyyan.tixly.controllers;

import com.raiyyan.tixly.domain.CreateEventRequest;
import com.raiyyan.tixly.domain.dtos.CreateEventRequestDto;
import com.raiyyan.tixly.domain.dtos.CreateEventResponseDto;
import com.raiyyan.tixly.domain.entities.Event;
import com.raiyyan.tixly.mappers.EventMapper;
import com.raiyyan.tixly.services.EventService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping(path = "/api/v1/events")
@RequiredArgsConstructor
public class EventController {
  private final EventMapper eventMapper;
  private  final EventService eventService;

  @PostMapping
  public ResponseEntity<CreateEventResponseDto> createEvent(
          @AuthenticationPrincipal Jwt jwt,
          @Valid  @RequestBody CreateEventRequestDto eventRequestDto){
    CreateEventRequest createEventRequest = eventMapper.fromDto(eventRequestDto);
    UUID userId = UUID.fromString(jwt.getSubject());
    Event createdEvent = eventService.createEvent(userId,createEventRequest);
    return  new ResponseEntity<>(eventMapper.toDto(createdEvent), HttpStatus.CREATED);
  }
}
