package com.ozyegin.hotelmanagement.servicetest;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.ozyegin.hotelmanagement.dto.EventDTO;
import com.ozyegin.hotelmanagement.repository.EventRepository;
import com.ozyegin.hotelmanagement.service.EventService;

import java.time.LocalDateTime;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
public class EventServiceTest {

    @Autowired
    private EventService eventService;

    @Autowired
    private EventRepository eventRepository;

   // create a new Event object
    private EventDTO createEvent(String name, String description, String location) {
        EventDTO event = new EventDTO();
        event.setName(name);
        event.setDescription(description);
        event.setEventDateTime(LocalDateTime.now());
        event.setLocation(location);
        return event;
    }

    // Test for creating an event
    @Test
    public void testCreateEvent() {
        EventDTO event = createEvent("Sample Event", "This is a sample event.", "Sample Location");

        EventDTO createdEvent = eventService.createEvent(event);

        assertNotNull(createdEvent);
        assertEquals("Sample Event", createdEvent.getName());
        assertEquals("This is a sample event.", createdEvent.getDescription());
        assertEquals("Sample Location", createdEvent.getLocation());
    }

    // Test for retrieving an event by ID
    @Test
    public void testGetEventById() {
        EventDTO event = createEvent("Sample Event", "This is a sample event.", "Sample Location");
        
        EventDTO createdEvent = eventService.createEvent(event);

        EventDTO foundEvent = eventService.getEventById(createdEvent.getId());

        assertNotNull(foundEvent);
        assertEquals("Sample Event", foundEvent.getName());
    }
    
    // Test for retrieving all events
    @Test
    public void testGetAllEvents() {
    	eventRepository.deleteAll();
    	
    	EventDTO event = createEvent("Sample Event", "This is a sample event.", "Sample Location");
    	eventService.createEvent(event);

        List<EventDTO> events = eventService.getAllEvents();
        assertFalse(events.isEmpty());
        assertEquals(1, events.size());
     
    }

    // Test for updating an event
    @Test
    public void testUpdateEvent() {
        EventDTO event = createEvent("Sample Event", "This is a sample event.", "Sample Location");
        event = eventService.createEvent(event);

        event.setName("Updated Event");
        event.setDescription("Updated Description");
        event.setLocation("Updated Location");    
        EventDTO updatedEventDTO = eventService.updateEvent(event.getId(), event);
        EventDTO retrievedEvent = eventService.getEventById(updatedEventDTO.getId());
        
        assertEquals("Updated Event", retrievedEvent.getName());
        assertEquals("Updated Description", retrievedEvent.getDescription());
        assertEquals("Updated Location", retrievedEvent.getLocation());
    }
    


    // Test for deleting an event
    @Test
    public void testDeleteEvent() {
        EventDTO event = createEvent("Sample Event", "This is a sample event.", "Sample Location");
        event = eventService.createEvent(event);
        
        eventService.deleteEvent(event.getId());
     
        EventDTO deletedEvent = eventService.getEventById(event.getId());
        assertNull(deletedEvent);
        
       
    }
}


























