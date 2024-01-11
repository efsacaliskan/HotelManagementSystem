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

import com.ozyegin.hotelmanagement.dto.RoomDTO;
import com.ozyegin.hotelmanagement.service.RoomService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@CrossOrigin
@RestController
@RequestMapping("/api/rooms")
public class RoomController {

	private final RoomService roomService;

	@Autowired
	public RoomController(RoomService roomService) {
		this.roomService = roomService;
	}

	/*
	 * @PostMapping public RoomDTO save(@RequestBody RoomDTO dto){
	 * 
	 * return roomService.addRoom(dto);
	 * 
	 * }
	 */

	@PostMapping
	@Operation(summary = "Creates a new Room", description = "Saves a new room info into database")
	@ApiResponses(value = { @ApiResponse(responseCode = "201", description = "created"),
			@ApiResponse(responseCode = "200", description = "successful operation") })
	public ResponseEntity<RoomDTO> addRoom(@RequestBody RoomDTO roomDTO) {
		RoomDTO dto = roomService.createRoom(roomDTO);
		return ResponseEntity.status(HttpStatus.CREATED).body(dto);
	}

	// Get a room by ID
	@GetMapping("/{id}")
	@Operation(summary = "Find a room by ID", description = "Returns a single room", tags = { "id" })
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "successful operation"),
			@ApiResponse(responseCode = "404", description = "Contact not found") })
	public RoomDTO getRoom(@PathVariable Long id) {
		return roomService.getRoomById(id);
	}

	// Update room details
	@PutMapping("/{id}")
	public RoomDTO updateRoom(@PathVariable Long id, @RequestBody RoomDTO roomDTO) {
		return roomService.updateRoom(id, roomDTO);
	}

	// Delete a room
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteRoom(@PathVariable Long id) {
		roomService.deleteRoom(id);
		return ResponseEntity.ok().build();
	}

	// List all rooms
	@GetMapping
	public List<RoomDTO> list() {

		return roomService.getAllRooms();

	}

	@GetMapping("/Get-All-Available-Rooms")
	public List<RoomDTO> getAllAvailableRooms() {

		return roomService.getAllAvailableRooms();
	}

	@GetMapping("/housekeeping/{id}")
	public List<RoomDTO> getAllRoomsByHousekeepingId(Long id) {
		return roomService.getAllRoomsByHousekeepingId(id);
	}
}
