package com.ozyegin.hotelmanagement.dto;

public class ManagerDTO {
    private Long id;
    private String name;
    private String contactNumber;
    private String email;
    private String department;

    public ManagerDTO() {}

    public ManagerDTO(Long id, String name, String contactNumber, String email, String department) {
        this.id = id;
        this.name = name;
        this.contactNumber = contactNumber;
        this.email = email;
        this.department = department;
    }

    // Getters
    public Long getId() { return id; }
    public String getName() { return name; }
    public String getContactNumber() { return contactNumber; }
    public String getEmail() { return email; }
    public String getDepartment() { return department; }

    // Setters
    public void setId(Long id) { this.id = id; }
    public void setName(String name) { this.name = name; }
    public void setContactNumber(String contactNumber) { this.contactNumber = contactNumber; }
    public void setEmail(String email) { this.email = email; }
    public void setDepartment(String department) { this.department = department; }
}