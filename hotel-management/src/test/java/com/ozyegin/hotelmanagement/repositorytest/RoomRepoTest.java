package com.ozyegin.hotelmanagement.repositorytest;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.ozyegin.hotelmanagement.model.Room;
import com.ozyegin.hotelmanagement.repository.RoomRepository;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
public class RoomRepoTest {


    @Autowired
    private RoomRepository roomRepository;

    // create a new Room object
    private Room createRoom(String roomNumber, String type, boolean isAvailable, double price) {
        Room room = new Room();
        room.setRoomNumber(roomNumber);
        room.setType(type);
        room.setAvailable(isAvailable);
        room.setPrice(price);
        return room;
    }

    // Test for adding a room
    @Test
    public void testAddRoom() {
        Room room = createRoom("101", "Standard", true, 500.0);

        roomRepository.save(room);

        assertNotNull(room);
        assertEquals("101", room.getRoomNumber());
        assertEquals("Standard", room.getType());
        assertTrue(room.isAvailable());
        assertEquals(500.0, room.getPrice());
    }
    
  

    // Test for retrieving a room by ID
    @Test
    public void testGetRoomById() {
        Room room = createRoom("102", "Deluxe", true, 750.0);
        roomRepository.save(room);

        Optional<Room> foundRoom = roomRepository.findById(room.getId());

        assertNotNull(foundRoom);
        assertEquals(room, foundRoom.get());
    
    }
    

    // Test for retrieving all rooms
    @Test
    public void testGetAllRooms() {
        Room room = createRoom("103", "Standard", true, 500.0);
        roomRepository.save(room);
        

        List<Room> rooms = roomRepository.findAll();

        assertFalse(rooms.isEmpty());
        assertEquals(5, rooms.size());
    }

 

    // Test for updating a room
    @Test
    public void testUpdateRoom() {
        Room room = createRoom("105", "Standard", true, 500.0);
        roomRepository.save(room);

        room.setRoomNumber("105A");
        room.setType("Suite");
        room.setAvailable(false);
        room.setPrice(1000.0);
        

        Optional<Room> retrievedRoom = roomRepository.findById(room.getId());
        assertTrue(retrievedRoom.isPresent());
        assertEquals("105A", retrievedRoom.get().getRoomNumber());
        assertEquals("Suite", retrievedRoom.get().getType());
        assertFalse(retrievedRoom.get().isAvailable());
        assertEquals(1000.0, retrievedRoom.get().getPrice());
    }


    // Test for deleting a room
    @Test
    public void testDeleteRoom() {
        Room room = createRoom("106", "Deluxe", true, 750.0);
        roomRepository.save(room);

        roomRepository.deleteById(room.getId());

        Optional<Room> deletedRoom = roomRepository.findById(room.getId());
        assertFalse(deletedRoom.isPresent());
    }
    
    // Test for findByIsAvailable
	@Test
	void testGetAvaliableRooms() {
		List<Room> list= roomRepository.findByIsAvailable(true);
		assertTrue(list.size()==4);
	}
	
	// Test for findByHousekeepingsId
	@Test
	void testGetRoomsByHousekeepingId() {
		List<Room> list= roomRepository.findByHousekeepingsId(1000L);
		assertTrue(list.size()==1);
	}
	
    
   

}

