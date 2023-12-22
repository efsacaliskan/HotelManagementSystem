package com.ozyegin.hotelmanagement.repositorytest;



import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.ozyegin.hotelmanagement.model.Guest;
import com.ozyegin.hotelmanagement.repository.GuestRepository;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
public class GuestRepoTest {

   
    @Autowired
    private GuestRepository guestRepository;
    
    
    // create a new Guest object
    private Guest createGuest(String name, String address, String contactNumber, String email) {
        Guest guest = new Guest();
        guest.setName(name);
        guest.setAddress(address);
        guest.setContactNumber(contactNumber);
        guest.setEmail(email);
        return guest;
    }
   
  
    // Test for adding a guest
    @Test
    public void testAddGuest() {
    	
    	Guest guest = createGuest("Random Guest","RandomPlace","5555","random@gmail.com");
    	guestRepository.save(guest);
        

        assertNotNull(guest);
        assertEquals("Random Guest", guest.getName());
        assertEquals("RandomPlace", guest.getAddress());
        assertEquals("5555", guest.getContactNumber());
        assertEquals("random@gmail.com", guest.getEmail());
    }

    // Test for retrieving a guest by ID
    @Test
    public void testGetGuestById() {
    	

       Guest guest = createGuest("Random Guest","RandomPlace","5555","random@gmail.com");
      
       guestRepository.save(guest);

       Optional<Guest> foundGuest = guestRepository.findById(guest.getId());

       assertNotNull(foundGuest);
       assertEquals(guest, foundGuest.get());
      
    }
      

    // Test for retrieving all guests
    @Test
    public void testGetAllGuests() {
    	
    	Guest guest = createGuest("Random guest","RandomPlace","5555","random@gmail.com");
    
        guestRepository.save(guest);
          
        List<Guest> guests = guestRepository.findAll();
        assertFalse(guests.isEmpty());
        assertEquals(3, guests.size());
     
    }
      
    
 
    // Test for updating a guest
    @Test
    public void testUpdateGuest() {
    	Guest guest = createGuest("Random guest","RandomPlace","5555","random@gmail.com");
    
        guestRepository.save(guest);
        
      
        guest.setName("Updated Name");
        guest.setContactNumber("Updated Contact");
        guest.setEmail("updated@example.com");
        guest.setAddress("Updated Address");
  
        
        Optional<Guest> updatedGuest = guestRepository.findById(guest.getId());
        assertNotNull(updatedGuest);
        assertEquals(guest, updatedGuest.get());
        
    }

    // Test for deleting a guest
    @Test
    public void testDeleteGuest() {
    	Guest guest = createGuest("Random guest","RandomPlace","5555","random@gmail.com");
    
        guestRepository.save(guest);

        guestRepository.deleteById(guest.getId());

        Optional<Guest> deletedGuest = guestRepository.findById(guest.getId());
        assertFalse(deletedGuest.isPresent());
        
    }
    
    // Test for findGuestsWithRoomType
    @Test
	void testGetByRoomType() {
		List<Guest> list= guestRepository.findGuestsWithRoomType("KingSuite");
		assertTrue(list.size()==2);
	}
    
   
    
    
    
    
    
    
}
