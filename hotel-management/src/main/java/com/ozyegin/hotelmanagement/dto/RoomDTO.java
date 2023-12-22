package com.ozyegin.hotelmanagement.dto;

public class RoomDTO {
    private Long id;
    private String roomNumber;
    private String type;
    private boolean isAvailable;
    private Double price;

    public RoomDTO() {}

    public RoomDTO(Long id, String roomNumber, String type, boolean isAvailable, Double price) {
        this.id = id;
        this.roomNumber = roomNumber;
        this.type = type;
        this.isAvailable = isAvailable;
        this.price = price;
    }

    // Getters
    public Long getId() { return id; }
    public String getRoomNumber() { return roomNumber; }
    public String getType() { return type; }
    public boolean isAvailable() { return isAvailable; }
    public Double getPrice() { return price; }

    // Setters
    public void setId(Long id) { this.id = id; }
    public void setRoomNumber(String roomNumber) { this.roomNumber = roomNumber; }
    public void setType(String type) { this.type = type; }
    public void setAvailable(boolean isAvailable) { this.isAvailable = isAvailable; }
    public void setPrice(Double price) { this.price = price; }
}