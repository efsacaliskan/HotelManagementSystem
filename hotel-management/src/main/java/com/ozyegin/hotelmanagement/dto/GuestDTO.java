package com.ozyegin.hotelmanagement.dto;


public class GuestDTO {
    private Long id;
    private String name;
    private String contactNumber;
    private String email;
    private String address;

    public GuestDTO() {}

    public GuestDTO(Long id, String name, String contactNumber, String email, String address) {
        this.id = id;
        this.name = name;
        this.contactNumber = contactNumber;
        this.email = email;
        this.address = address;
    }
    
    // Getters
    public Long getId() { return id; }
    public String getName() { return name; }
    public String getContactNumber() { return contactNumber; }
    public String getEmail() { return email; }
    public String getAddress() { return address; }

    // Setters
    public void setId(Long id) { this.id = id; }
    public void setName(String name) { this.name = name; }
    public void setContactNumber(String contactNumber) { this.contactNumber = contactNumber; }
    public void setEmail(String email) { this.email = email; }
    public void setAddress(String address) { this.address = address; }

}