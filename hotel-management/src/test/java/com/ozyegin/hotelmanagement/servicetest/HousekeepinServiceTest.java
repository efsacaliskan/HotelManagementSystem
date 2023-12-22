package com.ozyegin.hotelmanagement.servicetest;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.ozyegin.hotelmanagement.dto.HousekeepingDTO;
import com.ozyegin.hotelmanagement.dto.RoomDTO;
import com.ozyegin.hotelmanagement.repository.HousekeepingRepository;
import com.ozyegin.hotelmanagement.service.HousekeepingService;
import com.ozyegin.hotelmanagement.service.RoomService;

import java.util.List;


import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
public class HousekeepinServiceTest {

    @Autowired
    private HousekeepingService housekeepingService;

    @Autowired
    private RoomService roomService;
    
    @Autowired
    private HousekeepingRepository housekeepingRepository;
    
    
    // create a new Housekeeping object
    private HousekeepingDTO createHousekeeping(String status, Long i, String notes) {
        HousekeepingDTO housekeeping = new HousekeepingDTO();
        housekeeping.setStatus(status);
        housekeeping.setRoomId(i);
        housekeeping.setNotes(notes);
        return housekeeping;
    }
    
    //  create new Room object 
    private RoomDTO createRoom(String string, String type, boolean isAvailable, Double price) {
    	RoomDTO room = new RoomDTO();
    
        room.setRoomNumber(string);
        room.setType(type);
        room.setAvailable(isAvailable);
        room.setPrice(price);
      
        return room;
    }

    // Test for adding housekeeping record
    @Test
    public void testAddHousekeeping() {
    	RoomDTO room = createRoom("20", "KingSuite", true,  1200.0);
    	roomService.createRoom(room);
    		
    	System.out.println(room.getId());
    	
        HousekeepingDTO housekeeping = createHousekeeping("Available",  room.getId(), "Cleaned");

        housekeepingService.createHousekeeping(housekeeping);

        assertNotNull(housekeeping);
        assertEquals("Available", housekeeping.getStatus());
        assertEquals(room.getId(), housekeeping.getRoomId());
        assertEquals("Cleaned", housekeeping.getNotes());
    }
    
 

    // Test for retrieving a housekeeping record by ID
    @Test
    public void testGetHousekeepingById() {
    	RoomDTO room = createRoom("20", " KingSuite", true,  1200.0);
    	roomService.createRoom(room);
    	 HousekeepingDTO housekeeping = createHousekeeping("Available",  room.getId(), "Cleaned");

        
        HousekeepingDTO savedHousekeeping = housekeepingService.createHousekeeping(housekeeping);
        HousekeepingDTO foundHousekeeping = housekeepingService.getHousekeepingById(savedHousekeeping.getId());

        assertNotNull(foundHousekeeping);
        assertEquals("Available", foundHousekeeping.getStatus());
        assertEquals( room.getId(), foundHousekeeping.getRoomId());
        assertEquals("Cleaned", foundHousekeeping.getNotes());
    }
    
   

    // Test for retrieving all housekeeping records
    @Test
    public void testGetAllHousekeeping() {
        housekeepingRepository.deleteAll();
        RoomDTO room = createRoom("20", " KingSuite", true,  1200.0);
    	roomService.createRoom(room);
        
    	 HousekeepingDTO housekeeping = createHousekeeping("Available",  room.getId(), "Cleaned");

        
        housekeepingService.createHousekeeping(housekeeping);

        List<HousekeepingDTO> records = housekeepingService.getAllHousekeeping();

        assertFalse(records.isEmpty());
        assertEquals(1, records.size());
    }
    


    // Test for updating a housekeeping record
    @Test
    public void testUpdateHousekeeping() {
    	RoomDTO room = createRoom("20", " KingSuite", true,  1200.0);
    	roomService.createRoom(room);
    	 HousekeepingDTO housekeeping = createHousekeeping("Available",  room.getId(), "Cleaned");

        
    	housekeeping = housekeepingService.createHousekeeping(housekeeping);

        housekeeping.setStatus("Busy");
        housekeeping.setNotes("In Use");
        HousekeepingDTO updatedHousekeeping = housekeepingService.updateHousekeeping(housekeeping.getId(), housekeeping);

        HousekeepingDTO retrievedHousekeeping = housekeepingService.getHousekeepingById(updatedHousekeeping.getId());
       
        assertEquals("Busy", retrievedHousekeeping.getStatus());
        assertEquals("In Use", retrievedHousekeeping.getNotes());
    }
    


    // Test for deleting a housekeeping record
    @Test
    public void testDeleteHousekeeping() {
    	RoomDTO room = createRoom("20", " KingSuite", true,  1200.0);
    	roomService.createRoom(room);
    	 HousekeepingDTO housekeeping = createHousekeeping("Available",  room.getId(), "Cleaned");

        
       	housekeeping = housekeepingService.createHousekeeping(housekeeping);

        housekeepingService.deleteHousekeeping(housekeeping.getId());

        HousekeepingDTO deletedHousekeeping = housekeepingService.getHousekeepingById(housekeeping.getId());
        assertNull(deletedHousekeeping);
    }
}


