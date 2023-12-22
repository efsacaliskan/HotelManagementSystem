package com.ozyegin.hotelmanagement.servicetest;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.ozyegin.hotelmanagement.dto.RoomDTO;
import com.ozyegin.hotelmanagement.service.RoomService;

import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
public class RoomServiceTest {

    @Autowired
    private RoomService roomService;

   

    // create a new Room object
    private RoomDTO createRoom(String roomNumber, String type, boolean isAvailable, double price) {
        RoomDTO room = new RoomDTO();
        room.setRoomNumber(roomNumber);
        room.setType(type);
        room.setAvailable(isAvailable);
        room.setPrice(price);
        return room;
    }

    // Test for adding a room
    @Test
    public void testAddRoom() {
        RoomDTO room = createRoom("101", "Standard", true, 500.0);

        RoomDTO savedRoom = roomService.createRoom(room);

        assertNotNull(savedRoom);
        assertEquals("101", savedRoom.getRoomNumber());
        assertEquals("Standard", savedRoom.getType());
        assertTrue(savedRoom.isAvailable());
        assertEquals(500.0, savedRoom.getPrice());
    }
   

    // Test for retrieving a room by ID
    @Test
    public void testGetRoomById() {
    	RoomDTO room = createRoom("101", "Standard", true, 500.0);

        RoomDTO savedRoom = roomService.createRoom(room);
        RoomDTO foundRoom = roomService.getRoomById(savedRoom.getId());

        assertNotNull(foundRoom);
        assertEquals("101", foundRoom.getRoomNumber());
        assertEquals("Standard", foundRoom.getType());
    }

    // Test for retrieving all rooms
    @Test
    public void testGetAllRooms() {
        //roomRepository.deleteAll();
    	RoomDTO room = createRoom("101", "Standard", true, 500.0);

        roomService.createRoom(room);

        List<RoomDTO> rooms = roomService.getAllRooms();

        assertFalse(rooms.isEmpty());
        assertEquals(5, rooms.size());
    }

    // Test for updating a room
    @Test
    public void testUpdateRoom() {
        RoomDTO room = createRoom("101", "Standard", true, 500.0);

        room = roomService.createRoom(room);

        room.setRoomNumber("105A");
        room.setType("Suite");
        room.setAvailable(false);
        room.setPrice(1000.0);
        RoomDTO updatedRoom = roomService.updateRoom(room.getId(), room);

        RoomDTO retrievedRoom = roomService.getRoomById(updatedRoom.getId());
        assertEquals("105A", retrievedRoom.getRoomNumber());
        assertEquals("Suite", retrievedRoom.getType());
        assertFalse(retrievedRoom.isAvailable());
        assertEquals(1000.0, retrievedRoom.getPrice());
    }


    // Test for deleting a room
    @Test
    public void testDeleteRoom() {
        RoomDTO room = createRoom("106", "Deluxe", true, 750.0);
        room = roomService.createRoom(room);

        roomService.deleteRoom(room.getId());

        RoomDTO deletedRoom = roomService.getRoomById(room.getId());
        assertNull(deletedRoom);
    }
    
    
    // Test for findByIsAvailable
	@Test
	void testGetAvaliableRooms() {
		List<RoomDTO> list= roomService.getAllAvailableRooms();
		assertTrue(list.size()==4);
	}
	
	// Test for findByHousekeepingsId
	@Test
	void testGetRoomsByHousekeepingId() {
		List<RoomDTO> list= roomService.getAllRoomsByHousekeepingId(1000L);
		assertTrue(list.size()==1);
	}
    


    
}

