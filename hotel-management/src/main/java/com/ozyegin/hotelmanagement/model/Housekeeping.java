package com.ozyegin.hotelmanagement.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "housekeeping")
public class Housekeeping {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	// one room can have many housekeeping records.
	@ManyToOne
	@JoinColumn(name = "room_id", referencedColumnName = "id", nullable = false)
	private Room room;

	@Column(name = "status")
	private String status; // e.g., "Pending", "In Progress", "Completed"

	@Column(name = "notes")
	private String notes; // Additional notes or special requests

	// Constructors
	public Housekeeping() {
	}

	public Housekeeping(Room room, String status, String notes) {
		this.room = room;
		this.status = status;
		this.notes = notes;
	}

	// Getters and Setters
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Room getRoom() {
		return room;
	}

	public void setRoom(Room room2) {
		this.room = room2;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}
}
