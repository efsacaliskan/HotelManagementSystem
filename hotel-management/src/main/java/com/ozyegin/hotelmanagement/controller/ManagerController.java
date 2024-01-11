package com.ozyegin.hotelmanagement.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ozyegin.hotelmanagement.dto.ManagerDTO;
import com.ozyegin.hotelmanagement.service.ManagerService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@CrossOrigin
@RestController
@RequestMapping("/api/managers")
public class ManagerController {

	private final ManagerService managerService;

	@Autowired
	public ManagerController(ManagerService managerService) {
		this.managerService = managerService;
	}

	// Add a new manager
	@PostMapping
	@Operation(summary = "Creates a new Manager", description = "Saves a new manager info into database")
	@ApiResponses(value = { @ApiResponse(responseCode = "201", description = "created"),
			@ApiResponse(responseCode = "200", description = "successful operation") })
	public ResponseEntity<ManagerDTO> createManager(@RequestBody ManagerDTO managerDTO) {

		ManagerDTO dto = managerService.createManager(managerDTO);
		return ResponseEntity.status(HttpStatus.CREATED).body(dto);

	}

	// Get a manager by ID
	@GetMapping("/{id}")
	@Operation(summary = "Find a manager by ID", description = "Returns a single manager", tags = { "id" })
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "successful operation"),
			@ApiResponse(responseCode = "404", description = "Contact not found") })

	public ManagerDTO getManager(@PathVariable Long id) {

		return managerService.getManagerById(id);

	}

	// Update manager details
	@PutMapping("/{id}")
	public ManagerDTO updateManager(@PathVariable Long id, @RequestBody ManagerDTO managerDTO) {
		return managerService.updateManager(id, managerDTO);
	}

	// Delete a manager
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteManager(@PathVariable Long id) {
		managerService.deleteManager(id);
		return ResponseEntity.ok().build();
	}

	// List all managers
	@GetMapping
	public List<ManagerDTO> list() {

		return managerService.getAllManagers();

	}

	@GetMapping("/Event/{id}")
	public List<ManagerDTO> getAllManagersByEventId(Long id) {
		return managerService.getAllManagersByEventId(id);
	}

}
