package com.ozyegin.hotelmanagement.servicetest;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.ozyegin.hotelmanagement.dto.GuestDTO;
import com.ozyegin.hotelmanagement.dto.ReservationDTO;
import com.ozyegin.hotelmanagement.dto.RoomDTO;
import com.ozyegin.hotelmanagement.repository.ReservationRepository;
import com.ozyegin.hotelmanagement.service.GuestService;
import com.ozyegin.hotelmanagement.service.ReservationService;
import com.ozyegin.hotelmanagement.service.RoomService;

import java.time.LocalDate;
import java.util.List;


import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
public class ReservationServiceTest {

    @Autowired
    private ReservationService reservationService;

    @Autowired
    private ReservationRepository reservationRepository;
    
    @Autowired
    private RoomService roomService;
    
    @Autowired
    private GuestService guestService;

    // create a new Reservation object
    private ReservationDTO createReservation(Long guest, Long room, LocalDate startDate, LocalDate endDate, String status) {
        ReservationDTO reservation = new ReservationDTO();
        reservation.setGuestId(guest);
        reservation.setRoomId(room);
        reservation.setStartDate(startDate);
        reservation.setEndDate(endDate);
        reservation.setStatus(status);
        return reservation;
    }
    
    // create a new Guest object
    private GuestDTO createGuest(String name, String address, String contactNumber, String email) {
        GuestDTO guest = new GuestDTO();
        guest.setName(name);
        guest.setAddress(address);
        guest.setContactNumber(contactNumber);
        guest.setEmail(email);
        return guest;
    }
    
    private RoomDTO createRoom(String roomNumber, String type, boolean isAvailable, double price) {
        RoomDTO room = new RoomDTO();
        room.setRoomNumber(roomNumber);
        room.setType(type);
        room.setAvailable(isAvailable);
        room.setPrice(price);
        return room;
    }


    // Test for creating a reservation
    @Test
    public void testCreateReservation() {
    	RoomDTO room = createRoom("20", " KingSuite", true,  1200.0);
    	roomService.createRoom(room);
    	
    	GuestDTO guest = createGuest("Random Guest","RandomPlace","5555","random@gmail.com");
     	guestService.createGuest(guest);
    	
      
        ReservationDTO reservation = createReservation(guest.getId(), room.getId(), LocalDate.now(), LocalDate.now().plusDays(3), "Confirmed");

        ReservationDTO savedReservation = reservationService.createReservation(reservation);

        assertNotNull(savedReservation);
       
    }

    // Test for retrieving a reservation by ID
    @Test
    public void testGetReservationById() {
    	RoomDTO room = createRoom("20", " KingSuite", true,  1200.0);
    	roomService.createRoom(room);
    	
    	GuestDTO guest = createGuest("Random Guest","RandomPlace","5555","random@gmail.com");
    	guestService.createGuest(guest);
    	
     	

        ReservationDTO reservation = createReservation(guest.getId(), room.getId(), LocalDate.now(), LocalDate.now().plusDays(3), "Confirmed");
        reservation = reservationService.createReservation(reservation);

        ReservationDTO foundReservation = reservationService.getReservationById(reservation.getId());

        assertNotNull(foundReservation);
       
    }

    // Test for retrieving all reservations
    @Test
    public void testGetAllReservations() {
        reservationRepository.deleteAll();
        
        RoomDTO room = createRoom("20", " KingSuite", true,  1200.0);
    	roomService.createRoom(room);
    	
    	GuestDTO guest = createGuest("Random Guest","RandomPlace","5555","random@gmail.com");
    	guestService.createGuest(guest);
    	
     
        ReservationDTO reservation = createReservation(guest.getId(), room.getId(), LocalDate.now(), LocalDate.now().plusDays(3), "Confirmed");
        reservation = reservationService.createReservation(reservation);
        
   
        List<ReservationDTO> reservations = reservationService.getAllReservations();

        assertFalse(reservations.isEmpty());
        assertEquals(1, reservations.size());
    }

    // Test for updating a reservation
    @Test
    public void testUpdateReservation() {
    	
    	RoomDTO room = createRoom("20", " KingSuite", true,  1200.0);
    	roomService.createRoom(room);
    	
    	GuestDTO guest = createGuest("Random Guest","RandomPlace","5555","random@gmail.com");
    	guestService.createGuest(guest);
    	
     	

        ReservationDTO reservation = createReservation(guest.getId(), room.getId(), LocalDate.now(), LocalDate.now().plusDays(3), "Confirmed");
        reservation = reservationService.createReservation(reservation);

        reservation.setStatus("Cancelled");
        ReservationDTO updatedReservation = reservationService.updateReservation(reservation.getId(), reservation);

        ReservationDTO retrievedReservation = reservationService.getReservationById(updatedReservation.getId());
      
        assertEquals("Cancelled", retrievedReservation.getStatus());
    }
    
 

    // Test for deleting a reservation
    @Test
    public void testDeleteReservation() {
    	RoomDTO room = createRoom("20", " KingSuite", true,  1200.0);
    	roomService.createRoom(room);
    	
    	GuestDTO guest = createGuest("Random Guest","RandomPlace","5555","random@gmail.com");
    	guestService.createGuest(guest);
    	

        ReservationDTO reservation = createReservation(guest.getId(), room.getId(), LocalDate.now(), LocalDate.now().plusDays(3), "Confirmed");
        reservation = reservationService.createReservation(reservation);

        reservationService.deleteReservation(reservation.getId());

        ReservationDTO deletedReservation = reservationService.getReservationById(reservation.getId());
        assertNull(deletedReservation);
    }
    
    // Test for findByGuestId
 	@Test
 	void testGetByGuestId() {
 		List<ReservationDTO> list= reservationService.getAllReservationsByGuestId(2L);
 		assertTrue(list.size()==2);
 	}
 	
 
    
    
}
