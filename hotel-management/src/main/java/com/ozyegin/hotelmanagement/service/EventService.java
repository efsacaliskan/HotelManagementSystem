package com.ozyegin.hotelmanagement.service;


import java.util.List;

import com.ozyegin.hotelmanagement.dto.EventDTO;



public interface EventService {

	EventDTO createEvent(EventDTO eventDTO);

	EventDTO getEventById(Long id);

	EventDTO updateEvent(Long id, EventDTO eventDTO);

	void deleteEvent(Long id);

	List<EventDTO> getAllEvents();

	List<EventDTO> getAllEventsByManagerId(Long id);

}
