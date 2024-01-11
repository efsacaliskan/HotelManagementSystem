package com.ozyegin.hotelmanagement.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ozyegin.hotelmanagement.dto.EventDTO;
import com.ozyegin.hotelmanagement.service.EventService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@CrossOrigin
@RestController
@RequestMapping("/api/events")
public class EventController {

	private final EventService eventService;

	@Autowired
	public EventController(EventService eventService) {
		this.eventService = eventService;
	}

	// Add a new event
	@PostMapping
	@Operation(summary = "Creates a new Event", description = "Saves a new event info into database")
	@ApiResponses(value = { @ApiResponse(responseCode = "201", description = "created"),
			@ApiResponse(responseCode = "200", description = "successful operation") })

	public ResponseEntity<EventDTO> addEvent(@RequestBody EventDTO eventDTO) {

		EventDTO dto = eventService.createEvent(eventDTO);
		return ResponseEntity.status(HttpStatus.CREATED).body(dto);
	}

	/*
	 * @PostMapping public ResponseEntity<EventDTO> addEvent(@RequestBody EventDTO
	 * eventDTO) { Event event = EventMapper.INSTANCE.eventDTOToEvent((EventDTO)
	 * eventDTO); Event savedEvent = eventService.createEvent((Event) event);
	 * EventDTO savedEventDTO = EventMapper.INSTANCE.eventToEventDTO(savedEvent);
	 * return ResponseEntity.ok(savedEventDTO); }
	 */

	// Get an event by ID
	@GetMapping(value = "/{id}")
	@Operation(summary = "Find a event by ID", description = "Returns a single event", tags = { "id" })
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "successful operation"),
			@ApiResponse(responseCode = "404", description = "Contact not found") })

	public EventDTO getEvent(@PathVariable Long id) {

		return eventService.getEventById(id);
	}

	/*
	 * public ResponseEntity<EventDTO> getEvent(@PathVariable Long id) { Event event
	 * = (Event) eventService.getEventById(id); EventDTO eventDTO =
	 * EventMapper.INSTANCE.eventToEventDTO(event); return
	 * ResponseEntity.ok(eventDTO);
	 * 
	 * 
	 * }
	 */

	// Update event details
	@PutMapping("/{id}")
	public EventDTO updateEvent(@PathVariable Long id, @RequestBody EventDTO eventDTO) {
		return eventService.updateEvent(id, eventDTO);
	}

	// Delete an event
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteEvent(@PathVariable Long id) {
		eventService.deleteEvent(id);
		return ResponseEntity.ok().build();
	}

	// List all events
	@GetMapping
	public List<EventDTO> list() {

		return eventService.getAllEvents();

	}

	@GetMapping("/Manager/{id}")
	public List<EventDTO> getAllEventsByManagerId(Long id) {

		return eventService.getAllEventsByManagerId(id);

	}

}
