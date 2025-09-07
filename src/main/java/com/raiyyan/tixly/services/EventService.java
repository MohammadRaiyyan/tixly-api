package com.raiyyan.tixly.services;

import com.raiyyan.tixly.domain.CreateEventRequest;
import com.raiyyan.tixly.domain.entities.Event;

import java.util.UUID;

public interface EventService {
  Event createEvent(UUID organizerId,CreateEventRequest eventRequest);
}
