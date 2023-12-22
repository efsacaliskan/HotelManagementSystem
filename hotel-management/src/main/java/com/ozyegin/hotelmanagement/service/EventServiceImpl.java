package com.ozyegin.hotelmanagement.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ozyegin.hotelmanagement.dto.EventDTO;
import com.ozyegin.hotelmanagement.mapper.EventMapper;
import com.ozyegin.hotelmanagement.model.Event;
import com.ozyegin.hotelmanagement.repository.EventRepository;




@Service
@Transactional
public class EventServiceImpl implements EventService {

	@Autowired
	private final EventRepository eventRepository;

	@Autowired
	public EventServiceImpl(EventRepository eventRepository) {
		this.eventRepository = eventRepository;
	}

	@Autowired
	EventMapper eventMapper;

	// Add a new event
	@Override
	public EventDTO createEvent(EventDTO eventDTO) {
		Event event = eventMapper.eventDTOToEvent(eventDTO);
		Event savedEvent = eventRepository.save(event);
		EventDTO savedEventDTO = eventMapper.eventToEventDTO(savedEvent);
		return savedEventDTO;

	}

	// Retrieve an event by ID
	@Override
	public EventDTO getEventById(Long id) {
		Optional<Event> opt = eventRepository.findById(id);
		if (opt.isPresent()) {
			Event event = opt.get();
			return eventMapper.eventToEventDTO(event);
		} else
			return null;
	}

	// Update event details
	@Override
	public EventDTO updateEvent(Long id, EventDTO event2DTO) {

		EventDTO eventDTO = getEventById(id);
		eventDTO.setName(event2DTO.getName());
		eventDTO.setDescription(event2DTO.getDescription());
		eventDTO.setEventDateTime(event2DTO.getEventDateTime());
		eventDTO.setLocation(event2DTO.getLocation());
		Event event = eventMapper.eventDTOToEvent(eventDTO);
		Event updated = eventRepository.save(event);
		return eventMapper.eventToEventDTO(updated);

	}

	// Delete an event
	@Override
	public void deleteEvent(Long id) {
		eventRepository.deleteById(id);
	}

	// List all events
	@Override
	public List<EventDTO> getAllEvents() {
		List<Event> list = eventRepository.findAll();
		List<EventDTO> dtoList = new ArrayList<EventDTO>();
		for (Event event : list) {
			dtoList.add(eventMapper.eventToEventDTO(event));
		}
		return dtoList;
	}

	@Override
	public List<EventDTO> getAllEventsByManagerId(Long id) {
		List<Event> list = eventRepository.findByManagerId(id);
		List<EventDTO> dtoList = new ArrayList<EventDTO>();
		for (Event event : list) {
			dtoList.add(eventMapper.eventToEventDTO(event));
		}
		return dtoList;
	}

}
