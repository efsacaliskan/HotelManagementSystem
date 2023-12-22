package com.ozyegin.hotelmanagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ozyegin.hotelmanagement.dto.HousekeepingDTO;
import com.ozyegin.hotelmanagement.service.HousekeepingService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import java.util.List;

@RestController
@RequestMapping("/api/housekeeping")
public class HousekeepingController {

    private final HousekeepingService housekeepingService;

    @Autowired
    public HousekeepingController(HousekeepingService housekeepingService) {
        this.housekeepingService = housekeepingService;
    }

    // Add a new housekeeping record
    @PostMapping
	@Operation(summary = "Creates a new Student", description = "Saves a new guest info into database")
	@ApiResponses(value = { @ApiResponse(responseCode = "201", description = "created"),
			@ApiResponse(responseCode = "200", description = "successful operation") })
    public ResponseEntity<HousekeepingDTO> addHousekeeping(@RequestBody HousekeepingDTO housekeepingDTO) {
     			
        HousekeepingDTO dto = housekeepingService.createHousekeeping(housekeepingDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(dto);
        
    }

    // Get a housekeeping record by ID
    @GetMapping("/{id}")
    @Operation(summary = "Find a housekeeping by ID", 
	description = "Returns a single housekeeping", tags = { "id" })
    @ApiResponses(value = {
	@ApiResponse(responseCode = "200", description = "successful operation"),
	@ApiResponse(responseCode = "404", description = "Contact not found") })
    public HousekeepingDTO getHousekeeping(@PathVariable Long id) {
       
    	   return housekeepingService.getHousekeepingById(id);
    }

    // Update housekeeping details
    @PutMapping("/{id}")
    public HousekeepingDTO updateHousekeeping(@PathVariable Long id, @RequestBody HousekeepingDTO housekeepingDTO) {
    	return housekeepingService.updateHousekeeping(id, housekeepingDTO);
    }

    // Delete a housekeeping record
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteHousekeeping(@PathVariable Long id) {
        housekeepingService.deleteHousekeeping(id);
        return ResponseEntity.ok().build();
    }

    // List all housekeeping records
 
    
    // List all housekeeping records
    @GetMapping
  	public List<HousekeepingDTO> list(){
  		
  		return housekeepingService.getAllHousekeeping();
  		
  	}

}
