package com.ozyegin.hotelmanagement.servicetest;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.ozyegin.hotelmanagement.dto.ManagerDTO;
import com.ozyegin.hotelmanagement.repository.ManagerRepository;
import com.ozyegin.hotelmanagement.service.ManagerService;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
public class ManagerServiceTest {

    @Autowired
    private ManagerService managerService;

    @Autowired
    private ManagerRepository managerRepository;

    //  create a new Manager object
    private ManagerDTO createManager(String name, String contactNumber, String email, String department) {
        ManagerDTO manager = new ManagerDTO();
        manager.setName(name);
        manager.setContactNumber(contactNumber);
        manager.setEmail(email);
        manager.setDepartment(department);
        return manager;
    }
    
    

    // Test for creating a manager
    @Test
    public void testCreateManager() {
        ManagerDTO manager = createManager("Random Manager", "123456789", "manager@example.com", "HR");

        ManagerDTO savedManager = managerService.createManager(manager);

        assertNotNull(savedManager);
        assertEquals("Random Manager", savedManager.getName());
        assertEquals("123456789", savedManager.getContactNumber());
        assertEquals("manager@example.com", savedManager.getEmail());
        assertEquals("HR", savedManager.getDepartment());
    }
  

    // Test for retrieving a manager by ID
    @Test
    public void testGetManagerById() {
        ManagerDTO manager = createManager("Random Manager", "123456789", "manager@example.com", "Finance");
        ManagerDTO savedManager = managerService.createManager(manager);

        ManagerDTO foundManager = managerService.getManagerById(savedManager.getId());

        assertNotNull(foundManager);
        assertEquals("Random Manager", foundManager.getName());
        assertEquals("Finance", foundManager.getDepartment());
    }
    
    

    // Test for retrieving all managers
    @Test
    public void testGetAllManagers() {
        managerRepository.deleteAll();
        ManagerDTO manager = createManager("Random Manager", "123456789", "manager@example.com", "Finance");
        ManagerDTO savedManager = managerService.createManager(manager);


        List<ManagerDTO> managers = managerService.getAllManagers();

        assertFalse(managers.isEmpty());
        assertEquals(1, managers.size());
    }
    
	

    // Test for updating a manager
    @Test
    public void testUpdateManager() {
        ManagerDTO manager = createManager("Random Manager", "123456789", "manager@example.com", "HR");
        manager = managerService.createManager(manager);

        manager.setName("Updated Manager");
        manager.setContactNumber("987654321");
        manager.setEmail("updated@example.com");
        manager.setDepartment("Marketing");
        ManagerDTO updatedManager = managerService.updateManager(manager.getId(), manager);

        ManagerDTO retrievedManager = managerService.getManagerById(manager.getId());
       
        assertEquals("Updated Manager", retrievedManager.getName());
        assertEquals("Marketing", retrievedManager.getDepartment());
    }

    

    // Test for deleting a manager
    @Test
    public void testDeleteManager() {
        ManagerDTO manager = createManager("Manager to Delete", "123456789", "delete@example.com", "HR");
        manager = managerService.createManager(manager);

        managerService.deleteManager(manager.getId());

        ManagerDTO deletedManager = managerService.getManagerById(manager.getId());
        assertNull(deletedManager);
    }
    
  
    
}
