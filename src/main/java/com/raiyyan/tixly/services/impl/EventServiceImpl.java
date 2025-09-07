package com.raiyyan.tixly.services.impl;

import com.raiyyan.tixly.domain.CreateEventRequest;
import com.raiyyan.tixly.domain.entities.Event;
import com.raiyyan.tixly.domain.entities.TicketType;
import com.raiyyan.tixly.domain.entities.User;
import com.raiyyan.tixly.exceptions.UserNotFoundException;
import com.raiyyan.tixly.repositories.EventRepository;
import com.raiyyan.tixly.repositories.UserRepository;
import com.raiyyan.tixly.services.EventService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class EventServiceImpl implements EventService {
  private  final UserRepository userRepository;
  private final EventRepository eventRepository;
  @Override
  public Event createEvent(UUID organizerId, CreateEventRequest eventRequest) {
    User user = userRepository.findById(organizerId).orElseThrow(()-> new UserNotFoundException("User not found"));
    Event eventToCreate = new Event();
    List<TicketType> ticketTypesToCreate = eventRequest.getTicketTypes().stream().map(ticketType -> {
      TicketType ticketTypeToCreate = new TicketType();
      ticketTypeToCreate.setName(ticketType.getName());
      ticketTypeToCreate.setDescription(ticketType.getDescription());
      ticketTypeToCreate.setPrice(ticketType.getPrice());
      ticketTypeToCreate.setTotalAvailable(ticketType.getTotalAvailable());
      ticketTypeToCreate.setEvent(eventToCreate);
      return ticketTypeToCreate;
    }).toList();
    eventToCreate.setName(eventRequest.getName());
    eventToCreate.setVenue(eventRequest.getVenue());
    eventToCreate.setStart(eventRequest.getStart());
    eventToCreate.setEnd(eventRequest.getEnd());
    eventToCreate.setStatus(eventRequest.getStatus());
    eventToCreate.setSalesStart(eventRequest.getSalesStart());
    eventToCreate.setSalesEnd(eventRequest.getSalesEnd());
    eventToCreate.setOrganizer(user);
    eventToCreate.setTicketTypes(ticketTypesToCreate);

    return eventRepository.save(eventToCreate);
  }
}
