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
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.ozyegin.hotelmanagement.dto.GuestDTO;
import com.ozyegin.hotelmanagement.service.GuestService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@CrossOrigin
@RestController
@RequestMapping("/api/guests")
public class GuestController {

	private final GuestService guestService;

	@Autowired
	public GuestController(GuestService guestService) {
		this.guestService = guestService;
	}

	// Add a new guest
	@PostMapping
	@Operation(summary = "Creates a new Guest", description = "Saves a new guest info into database")
	@ApiResponses(value = { @ApiResponse(responseCode = "201", description = "created"),
			@ApiResponse(responseCode = "200", description = "successful operation") })
	public ResponseEntity<GuestDTO> addGuest(@RequestBody GuestDTO guestDTO) {

		GuestDTO dto = guestService.createGuest(guestDTO);
		return ResponseEntity.status(HttpStatus.CREATED).body(dto);

	}

	// Get a guest by ID
	@GetMapping(value = "/{id}")
	@Operation(summary = "Find a guest by ID", description = "Returns a single guest", tags = { "id" })
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "successful operation"),
			@ApiResponse(responseCode = "404", description = "Contact not found") })

	public GuestDTO getGuest(@PathVariable Long id) {

		return guestService.getGuestById(id);

	}

	// Update guest details
	@PutMapping("/{id}")
	public GuestDTO updateGuest(@PathVariable Long id, @RequestBody GuestDTO guestDTO) {

		return guestService.updateGuest(id, guestDTO);

	}

	// Delete a guest
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteGuest(@PathVariable Long id) {
		guestService.deleteGuest(id);
		return ResponseEntity.ok().build();
	}

	// List all guests
	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public List<GuestDTO> getAllGuests() {

		return guestService.getAllGuests();

	}

	@GetMapping("/Reservations/{type}")
	public List<GuestDTO> findGuestsWithRoomType(String roomtype) {
		return guestService.getGuestsByRoomType(roomtype);
	}

	/*
	 * // Get a guest by ID
	 * 
	 * @GetMapping(value = "/{reservationId}/ guests")
	 * 
	 * @Operation(summary = "Find guests by Reservation ID", description =
	 * "Returns guests info", tags = { "reservationId" })
	 * 
	 * @ApiResponses(value = {
	 * 
	 * @ApiResponse(responseCode = "200", description = "successful operation"),
	 * 
	 * @ApiResponse(responseCode = "404", description = "Contact not found") })
	 * 
	 * public List<GuestDTO>
	 * getAllGuestsByReservationId(@PathVariable("reservationId") Long
	 * reservationId) {
	 * 
	 * return guestService.getAllGuestsByReservationId(reservationId);
	 * 
	 * }
	 */

}
