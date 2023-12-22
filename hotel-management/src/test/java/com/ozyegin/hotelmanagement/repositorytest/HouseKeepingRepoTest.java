package com.ozyegin.hotelmanagement.repositorytest;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.ozyegin.hotelmanagement.model.Housekeeping;
import com.ozyegin.hotelmanagement.model.Room;
import com.ozyegin.hotelmanagement.repository.HousekeepingRepository;
import com.ozyegin.hotelmanagement.repository.RoomRepository;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
public class HouseKeepingRepoTest {

  

    @Autowired
    private HousekeepingRepository housekeepingRepository;
    
    @Autowired
    private RoomRepository roomRepository;
    

    // create a new Housekeeping object
    private Housekeeping createHousekeeping(String status, Room room, String notes) {
        Housekeeping housekeeping = new Housekeeping();
        housekeeping.setStatus(status);
        housekeeping.setRoom(room);
        housekeeping.setNotes(notes);
        return housekeeping;
    }
    
    //  create new Room object 
    private Room createRoom(String roomNumber, String type, boolean isAvailable, Double price) {
    	Room room = new Room();
    
        room.setRoomNumber(roomNumber);
        room.setType(type);
        room.setAvailable(isAvailable);
        room.setPrice(price);
      
        return room;
    }

    // Test for adding housekeeping record
    @Test
    public void testAddHousekeeping() {
    	Room room = createRoom("20", " KingSuite", true,  1200.0);
    	roomRepository.save(room);
    	
        Housekeeping housekeeping = createHousekeeping("Available", room, "Cleaned");

        housekeepingRepository.save(housekeeping);

        assertNotNull(housekeeping);
        assertEquals("Available", housekeeping.getStatus());
        assertEquals(room, housekeeping.getRoom());
        assertEquals("Cleaned", housekeeping.getNotes());
    }
      

    // Test for retrieving a housekeeping record by ID
    @Test
    public void testGetHousekeepingById() {
    	Room room = createRoom("20", " KingSuite", true,  1200.0);
       	roomRepository.save(room);
        Housekeeping housekeeping = createHousekeeping("Available", room, "Cleaned");
        housekeepingRepository.save(housekeeping);

        Optional<Housekeeping> foundHousekeeping = housekeepingRepository.findById(housekeeping.getId());

        assertNotNull(foundHousekeeping);
        assertEquals(housekeeping, foundHousekeeping.get());
     
    }
    

    // Test for retrieving all housekeeping records
    @Test
    public void testGetAllHousekeeping() {
        housekeepingRepository.deleteAll();
        
        Room room = createRoom("20", " KingSuite", true,  1200.0);
       	roomRepository.save(room);
        Housekeeping housekeeping = createHousekeeping("Available", room, "Cleaned");
        housekeepingRepository.save(housekeeping);
      

        List<Housekeeping> housekeepings = housekeepingRepository.findAll();

        assertFalse(housekeepings.isEmpty());
        assertEquals(1, housekeepings.size());
    }
    

    

    // Test for updating a housekeeping record
    @Test
    public void testUpdateHousekeeping() {
    	Room room = createRoom("20", " KingSuite", true,  1200.0);
       	room = roomRepository.save(room);
        Housekeeping housekeeping = createHousekeeping("Available", room, "Cleaned");
        housekeepingRepository.save(housekeeping);

        housekeeping.setStatus("Busy");
        housekeeping.setNotes("In Use");
        Optional<Housekeeping> updatedHousekeeping = housekeepingRepository.findById(housekeeping.getId());

        assertNotNull(updatedHousekeeping);
        assertTrue(updatedHousekeeping.isPresent());
        assertEquals(housekeeping, updatedHousekeeping.get());
       
    }
  
    

    // Test for deleting a housekeeping record
    @Test
    public void testDeleteHousekeeping() {
    	Room room = createRoom("20", " KingSuite", true,  1200.0);
       	roomRepository.save(room);
        Housekeeping housekeeping = createHousekeeping("Available", room, "Cleaned");
        housekeepingRepository.save(housekeeping);

        housekeepingRepository.deleteById(housekeeping.getId());

        Optional<Housekeeping> deletedHousekeeping = housekeepingRepository.findById(housekeeping.getId());
        assertFalse(deletedHousekeeping.isPresent());
    }
    
    

}
