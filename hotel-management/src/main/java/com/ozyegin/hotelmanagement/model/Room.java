package com.ozyegin.hotelmanagement.model;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "rooms")
public class Room {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "room_number", nullable = false, unique = true)
	private String roomNumber;

	@Column(name = "type")
	private String type;

	@Column(name = "is_available")
	private boolean isAvailable;

	@Column(name = "price")
	private Double price;

	@OneToMany(mappedBy = "room")
	private List<Reservation> reservations;

	@OneToMany(mappedBy = "room")
	private List<Housekeeping> housekeepings;

	// Constructors
	public Room() {
	}

	public Room(String roomNumber, String type, boolean isAvailable, Double price) {
		this.roomNumber = roomNumber;
		this.type = type;
		this.isAvailable = isAvailable;
		this.price = price;
	}

	// Getters and Setters
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getRoomNumber() {
		return roomNumber;
	}

	public void setRoomNumber(String roomNumber) {
		this.roomNumber = roomNumber;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public boolean isAvailable() {
		return isAvailable;
	}

	public void setAvailable(boolean available) {
		isAvailable = available;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	// Relationship Getters and Setters
	public List<Reservation> getReservations() {
		return reservations;
	}

	public void setReservations(List<Reservation> reservations) {
		this.reservations = reservations;
	}
}
