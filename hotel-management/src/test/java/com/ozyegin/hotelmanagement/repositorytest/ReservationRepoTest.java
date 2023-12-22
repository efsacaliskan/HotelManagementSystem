package com.ozyegin.hotelmanagement.repositorytest;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.ozyegin.hotelmanagement.model.Guest;
import com.ozyegin.hotelmanagement.model.Reservation;
import com.ozyegin.hotelmanagement.model.Room;
import com.ozyegin.hotelmanagement.repository.GuestRepository;
import com.ozyegin.hotelmanagement.repository.ReservationRepository;
import com.ozyegin.hotelmanagement.repository.RoomRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
public class ReservationRepoTest {


    @Autowired
    private ReservationRepository reservationRepository;
    
    @Autowired
    private RoomRepository roomRepository;
    
    @Autowired
    private GuestRepository guestRepository;

    // create a new Reservation object
    private Reservation createReservation(Guest guest, Room room, LocalDate startDate, LocalDate endDate, String status) {
        Reservation reservation = new Reservation();
        reservation.setGuest(guest);
        reservation.setRoom(room);
        reservation.setStartDate(startDate);
        reservation.setEndDate(endDate);
        reservation.setStatus(status);
        return reservation;
    }
    
    // create a new Guest object
    private Guest createGuest(String name, String address, String contactNumber, String email) {
        Guest guest = new Guest();
        guest.setName(name);
        guest.setAddress(address);
        guest.setContactNumber(contactNumber);
        guest.setEmail(email);
        return guest;
    }
    
    private Room createRoom(String roomNumber, String type, boolean isAvailable, double price) {
        Room room = new Room();
        room.setRoomNumber(roomNumber);
        room.setType(type);
        room.setAvailable(isAvailable);
        room.setPrice(price);
        return room;
    }


    // Test for creating a reservation
    @Test
    public void testCreateReservation() {
    	Room room = createRoom("20", " KingSuite", true,  1200.0);
    	roomRepository.save(room);
    	
    	Guest guest = createGuest("Random Guest","RandomPlace","5555","random@gmail.com");
     	guestRepository.save(guest);
    	
      
        Reservation reservation = createReservation(guest, room, LocalDate.now(), LocalDate.now().plusDays(3), "Confirmed");

        reservationRepository.save(reservation);

        assertNotNull(reservation);
        assertEquals(guest, reservation.getGuest());
        assertEquals(room, reservation.getRoom());
        assertEquals(reservation.getStartDate(), reservation.getStartDate());
        assertEquals(reservation.getEndDate(), reservation.getEndDate());
        assertEquals("Confirmed", reservation.getStatus());
        
    }
   

    // Test for retrieving a reservation by ID
    @Test
    public void testGetReservationById() {
    	Room room = createRoom("20", " KingSuite", true,  1200.0);
    	roomRepository.save(room);
    	
    	Guest guest = createGuest("Random Guest","RandomPlace","5555","random@gmail.com");
     	guestRepository.save(guest);
     	
        Reservation reservation = createReservation(guest, room, LocalDate.now(), LocalDate.now().plusDays(2), "Confirmed");
        reservationRepository.save(reservation);

        Optional<Reservation> foundReservation = reservationRepository.findById(reservation.getId());

        assertNotNull(foundReservation);
        assertEquals(reservation, foundReservation.get());
       
       
    }

    // Test for retrieving all reservations
    @Test
    public void testGetAllReservations() {
        reservationRepository.deleteAll();
        
    	Room room = createRoom("20", " KingSuite", true,  1200.0);
    	roomRepository.save(room);
    	
    	Guest guest = createGuest("Random Guest","RandomPlace","5555","random@gmail.com");
     	guestRepository.save(guest);
     	
        reservationRepository.save(createReservation(guest, room, LocalDate.now(), LocalDate.now().plusDays(1), "Confirmed"));

        List<Reservation> reservations = reservationRepository.findAll();

        assertFalse(reservations.isEmpty());
        assertEquals(1, reservations.size());
    }
    
  

    // Test for updating a reservation
    @Test
    public void testUpdateReservation() {
    	
    	Room room = createRoom("20", " KingSuite", true,  1200.0);
    	roomRepository.save(room);
    	
    	Guest guest = createGuest("Random Guest","RandomPlace","5555","random@gmail.com");
     	guestRepository.save(guest);
     	
        Reservation reservation = createReservation(guest, room, LocalDate.now(), LocalDate.now().plusDays(1), "Confirmed");
        reservationRepository.save(reservation);

        reservation.setStatus("Cancelled");
       

        Optional<Reservation> retrievedReservation = reservationRepository.findById(reservation.getId());
        assertTrue(retrievedReservation.isPresent());
        assertEquals("Cancelled", retrievedReservation.get().getStatus());
    }
    
  

    // Test for deleting a reservation
    @Test
    public void testDeleteReservation() {
    	Room room = createRoom("20", " KingSuite", true,  1200.0);
    	roomRepository.save(room);
    	
    	Guest guest = createGuest("Random Guest","RandomPlace","5555","random@gmail.com");
     	guestRepository.save(guest);
     	
        Reservation reservation = createReservation(guest,room, LocalDate.now(), LocalDate.now().plusDays(1), "Confirmed");
        reservationRepository.save(reservation);

        reservationRepository.deleteById(reservation.getId());

        Optional<Reservation> deletedReservation = reservationRepository.findById(reservation.getId());
        assertFalse(deletedReservation.isPresent());
    }
    
    // Test for findByGuestId
	@Test
	void testGetByGuestId() {
		List<Reservation> list= reservationRepository.findByGuestId(2L);
		assertTrue(list.size()==2);
	}
 
    
  
}
