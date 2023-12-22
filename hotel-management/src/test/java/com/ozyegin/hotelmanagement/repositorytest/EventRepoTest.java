package com.ozyegin.hotelmanagement.repositorytest;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.ozyegin.hotelmanagement.model.Event;
import com.ozyegin.hotelmanagement.repository.EventRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
public class EventRepoTest {

    @Autowired
    private EventRepository eventRepository;
    

   // create a new Event object
    private Event createEvent(String name, String description, String location) {
        Event event = new Event();
        event.setName(name);
        event.setDescription(description);
        event.setEventDateTime(LocalDateTime.now());
        event.setLocation(location);
        return event;
       
    }

    // Test for creating an event
    @Test
    public void testCreateEvent() {
        Event event = createEvent("Sample Event", "This is a sample event.", "Sample Location");

        eventRepository.save(event);

        assertNotNull(event);
        assertEquals("Sample Event", event.getName());
        assertEquals("This is a sample event.", event.getDescription());
        assertEquals("Sample Location", event.getLocation());
        
    }

    // Test for retrieving an event by ID
    @Test
    public void testGetEventById() {
        Event event = createEvent("Sample Event", "This is a sample event.", "Sample Location");
        eventRepository.save(event);
        

        Optional<Event> foundEvent = eventRepository.findById(event.getId());
        
   
        assertNotNull(foundEvent);
        assertEquals(event, foundEvent.get());
       
    }
    
    // Test for retrieving all events
    @Test
    public void testGetAllEvents() {
    	eventRepository.deleteAll();
    	
    	Event event = createEvent("Sample Event", "This is a sample event.", "Sample Location");
        eventRepository.save(event);


        List<Event> events = eventRepository.findAll();
        assertFalse(events.isEmpty());
        assertEquals(1, events.size());
        
     
    }

    // Test for updating an event
    @Test
    public void testUpdateEvent() {
        Event event = createEvent("Sample Event", "This is a sample event.", "Sample Location");
        eventRepository.save(event);
     

        event.setName("Updated Event");
        event.setDescription("Updated Description");
        event.setLocation("Updated Location");
    

        Optional<Event> retrievedEvent = eventRepository.findById(event.getId());
        assertTrue(retrievedEvent.isPresent());
        assertEquals("Updated Event", retrievedEvent.get().getName());
        assertEquals("Updated Description", retrievedEvent.get().getDescription());
        assertEquals("Updated Location", retrievedEvent.get().getLocation());      
     
    }

    // Test for deleting an event
    @Test
    public void testDeleteEvent() {
        Event event = createEvent("Sample Event", "This is a sample event.", "Sample Location");
        eventRepository.save(event);

        eventRepository.deleteById(event.getId());

        Optional<Event> deletedEvent = eventRepository.findById(event.getId());
        assertFalse(deletedEvent.isPresent());
       
    }
}
    
  