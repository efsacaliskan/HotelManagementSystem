package com.ozyegin.hotelmanagement.dto;


import java.time.LocalDateTime;

public class EventDTO {
    private Long id;
    private String name;
    private String description;
    private LocalDateTime eventDateTime;
    private String location;


    public EventDTO() {}

    public EventDTO(Long id, String name, String description, LocalDateTime eventDateTime, String location) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.eventDateTime = eventDateTime;
        this.location = location;
    }

    // Getters
    public Long getId() { return id; }
    public String getName() { return name; }
    public String getDescription() { return description; }
    public LocalDateTime getEventDateTime() { return eventDateTime; }
    public String getLocation() { return location; }

    // Setters
    public void setId(Long id) { this.id = id; }
    public void setName(String name) { this.name = name; }
    public void setDescription(String description) { this.description = description; }
    public void setEventDateTime(LocalDateTime eventDateTime) { this.eventDateTime = eventDateTime; }
    public void setLocation(String location) { this.location = location; }
    
    
    
}