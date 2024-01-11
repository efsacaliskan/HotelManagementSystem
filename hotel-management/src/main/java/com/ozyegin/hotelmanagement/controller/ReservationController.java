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

import com.ozyegin.hotelmanagement.dto.ReservationDTO;
import com.ozyegin.hotelmanagement.service.ReservationService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@CrossOrigin
@RestController
@RequestMapping("/api/reservations")
public class ReservationController {

	private final ReservationService reservationService;

	@Autowired
	public ReservationController(ReservationService reservationService) {
		this.reservationService = reservationService;
	}

	// Add a new reservation
	@PostMapping
	@Operation(summary = "Creates a new Reservation", description = "Saves a new reservation info into database")
	@ApiResponses(value = { @ApiResponse(responseCode = "201", description = "created"),
			@ApiResponse(responseCode = "200", description = "successful operation") })
	public ResponseEntity<ReservationDTO> addReservation(@RequestBody ReservationDTO reservationDTO) {
		ReservationDTO dto = reservationService.createReservation(reservationDTO);
		return ResponseEntity.status(HttpStatus.CREATED).body(dto);
	}

	// Get a reservation by ID
	@GetMapping("/{id}")
	@Operation(summary = "Find a reservation by ID", description = "Returns a single reservation", tags = { "id" })
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "successful operation"),
			@ApiResponse(responseCode = "404", description = "Contact not found") })
	public ReservationDTO getReservation(@PathVariable Long id) {

		return reservationService.getReservationById(id);

	}

	// Update reservation details
	@PutMapping("/{id}")
	public ReservationDTO updateReservation(@PathVariable Long id, @RequestBody ReservationDTO reservationDTO) {
		return reservationService.updateReservation(id, reservationDTO);
	}

	// Delete a reservation
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteReservation(@PathVariable Long id) {
		reservationService.deleteReservation(id);
		return ResponseEntity.ok().build();
	}

	// List all reservations
	@GetMapping
	public List<ReservationDTO> list() {

		return reservationService.getAllReservations();

	}

	@GetMapping("/Guest/{id}")
	public List<ReservationDTO> getAllReservationsByGuestId(@PathVariable Long id) {

		return reservationService.getAllReservationsByGuestId(id);

	}

}
