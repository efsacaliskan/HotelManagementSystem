package com.ozyegin.hotelmanagement.dto;


import java.time.LocalDate;

public class ReservationDTO {
    private Long id;
    private Long guestId;
    private Long roomId;
    private LocalDate startDate;
    private LocalDate endDate;
    private String status;


    public ReservationDTO() {}

    public ReservationDTO(Long id, LocalDate startDate, LocalDate endDate, String status, Long guestId, Long roomId) {
        this.id = id;
        this.guestId = guestId;
        this.roomId = roomId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.status = status;
    }

    // Getters
    public Long getId() { return id; }
    public Long getGuestId() { return guestId; }
    public Long getRoomId() { return roomId; }
    public LocalDate getStartDate() { return startDate; }
    public LocalDate getEndDate() { return endDate; }
    public String getStatus() { return status; }

    // Setters
    public void setId(Long id) { this.id = id; }
    public void setGuestId(Long guestId) { this.guestId = guestId; }
    public void setRoomId(Long roomId) { this.roomId = roomId; }
    public void setStartDate(LocalDate startDate) { this.startDate = startDate; }
    public void setEndDate(LocalDate endDate) { this.endDate = endDate; }
    public void setStatus(String status) { this.status = status; }
}