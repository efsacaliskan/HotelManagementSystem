package com.ozyegin.hotelmanagement.repositorytest;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.ozyegin.hotelmanagement.model.Manager;
import com.ozyegin.hotelmanagement.repository.ManagerRepository;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
public class ManagerRepoTest {

    @Autowired
    private ManagerRepository managerRepository;

    //  create a new Manager object
    private Manager createManager(String name, String contactNumber, String email, String department) {
        Manager manager = new Manager();
        manager.setName(name);
        manager.setContactNumber(contactNumber);
        manager.setEmail(email);
        manager.setDepartment(department);
        return manager;
    }

    // Test for creating a manager
    @Test
    public void testCreateManager() {
        Manager manager = createManager("Random Manager", "123456789", "manager@example.com", "HR");

        managerRepository.save(manager);

        assertNotNull(manager);
        assertEquals("Random Manager", manager.getName());
        assertEquals("123456789", manager.getContactNumber());
        assertEquals("manager@example.com", manager.getEmail());
        assertEquals("HR", manager.getDepartment());
    }

    // Test for retrieving a manager by ID
    @Test
    public void testGetManagerById() {
    	
        Manager manager = createManager("Random Manager", "123456789", "manager@example.com", "Finance");
        managerRepository.save(manager);

        Optional<Manager> foundManager = managerRepository.findById(manager.getId());

        assertNotNull(foundManager);
        assertEquals(manager, foundManager.get());
       
    }
    


    // Test for retrieving all managers
    @Test
    public void testGetAllManagers() {
        managerRepository.deleteAll();
        Manager manager = createManager("Manager 1", "123456789", "manager1@example.com", "HR");
        managerRepository.save(manager);

        List<Manager> managers = managerRepository.findAll();

        assertFalse(managers.isEmpty());
        assertEquals(1, managers.size());
    }
    

    // Test for updating a manager
    @Test
    public void testUpdateManager() {
        Manager manager = createManager("Random Manager", "123456789", "manager@example.com", "HR");
        managerRepository.save(manager);

        manager.setName("Updated Manager");
        manager.setContactNumber("987654321");
        manager.setEmail("updated@example.com");
        manager.setDepartment("Marketing");
       

        Optional<Manager> retrievedManager = managerRepository.findById(manager.getId());
        assertTrue(retrievedManager.isPresent());
        assertEquals(manager, retrievedManager.get());
       
    }
    

    // Test for deleting a manager
    @Test
    public void testDeleteManager() {
        Manager manager = createManager("Manager to Delete", "123456789", "delete@example.com", "HR");
        managerRepository.save(manager);

        managerRepository.deleteById(manager.getId());

        Optional<Manager> deletedManager = managerRepository.findById(manager.getId());
        assertFalse(deletedManager.isPresent());
    }
    

  
  
}
