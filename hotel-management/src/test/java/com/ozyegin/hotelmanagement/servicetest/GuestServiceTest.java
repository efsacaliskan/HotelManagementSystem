package com.ozyegin.hotelmanagement.servicetest;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.ozyegin.hotelmanagement.dto.GuestDTO;
import com.ozyegin.hotelmanagement.service.GuestService;

import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
public class GuestServiceTest {

    @Autowired
    private GuestService guestService;

  
    
    // create a new Guest object
    private GuestDTO createGuest(String name, String address, String contactNumber, String email) {
        GuestDTO guest = new GuestDTO	();
        guest.setName(name);
        guest.setAddress(address);
        guest.setContactNumber(contactNumber);
        guest.setEmail(email);
        return guest;
    }
   
  
    // Test for adding a guest
    @Test
    public void testAddGuest() {
    	
    	GuestDTO guest = createGuest("Random Guest","RandomPlace","5555","random@gmail.com");

        GuestDTO createdGuest = guestService.createGuest(guest);

        assertNotNull(createdGuest);
        assertEquals("Random Guest", createdGuest.getName());
        assertEquals("RandomPlace", createdGuest.getAddress());
        assertEquals("5555", createdGuest.getContactNumber());
        assertEquals("random@gmail.com", createdGuest.getEmail());
    }
    

    // Test for retrieving a guest by ID
    @Test
    public void testGetGuestById() {
    	
       GuestDTO guest = createGuest("Random Guest","RandomPlace","5555","random@gmail.com");
      
       GuestDTO createdGuest = guestService.createGuest(guest);

       GuestDTO foundGuest = guestService.getGuestById(createdGuest.getId());

       assertNotNull(foundGuest);
       assertEquals("Random Guest", foundGuest.getName());
       assertEquals("RandomPlace", foundGuest.getAddress());
       assertEquals("5555", foundGuest.getContactNumber());
       assertEquals("random@gmail.com", foundGuest.getEmail());
    }
    

    

    // Test for retrieving all guests
    @Test
    public void testGetAllGuests() {
    	 	
    	GuestDTO guest = createGuest("Random guest","RandomPlace","5555","random@gmail.com");
    
    	guestService.createGuest(guest);
       
        List<GuestDTO> guests = guestService.getAllGuests();
        assertFalse(guests.isEmpty());
        assertEquals(3, guests.size());
     
    }
    
 

    // Test for updating a guest
    @Test
    public void testUpdateGuest() {
    	GuestDTO guest = createGuest("Random guest","RandomPlace","5555","random@gmail.com");
    
        guest = guestService.createGuest(guest);
  

        guest.setName("Updated Name");
        GuestDTO updatedGuest = guestService.updateGuest(guest.getId(), guest);

        
        
        GuestDTO retrievedGuest = guestService.getGuestById(updatedGuest.getId());
  
        assertEquals("Updated Name", retrievedGuest.getName());
    }
    
    

    // Test for deleting a guest
    @Test
    public void testDeleteGuest() {
    	GuestDTO guest = createGuest("Random guest","RandomPlace","5555","random@gmail.com");
    
    	guest = guestService.createGuest(guest);

        guestService.deleteGuest(guest.getId());

        GuestDTO deletedGuest = guestService.getGuestById(guest.getId());
        assertNull(deletedGuest);
    }
    
    // Test for findGuestsWithRoomType
    @Test
	void testGetByRoomType() {
		List<GuestDTO> list= guestService.getGuestsByRoomType("KingSuite");
		assertTrue(list.size()==2);
	}
  
    
   
}
