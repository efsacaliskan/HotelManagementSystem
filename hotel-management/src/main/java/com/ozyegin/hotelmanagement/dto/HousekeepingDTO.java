package com.ozyegin.hotelmanagement.dto;

public class HousekeepingDTO {
    private Long id;
    private Long roomId;
    private String status;
    private String notes;

    public HousekeepingDTO() {}

    public HousekeepingDTO(Long id,  String status, String notes, Long roomId) {
        this.id = id;
        this.roomId = roomId;
        this.status = status;
        this.notes = notes;
    }
    // Getters
    public Long getId() { return id; }
    public Long getRoomId() { return roomId; }
    public String getStatus() { return status; }
    public String getNotes() { return notes; }

    // Setters
    public void setId(Long id) { this.id = id; }
    public void setRoomId(Long i) { this.roomId = i; }
    public void setStatus(String status) { this.status = status; }
    public void setNotes(String notes) { this.notes = notes; }
}