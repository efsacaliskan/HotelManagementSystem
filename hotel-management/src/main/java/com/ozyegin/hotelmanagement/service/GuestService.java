package com.ozyegin.hotelmanagement.service;

import java.util.List;

import com.ozyegin.hotelmanagement.dto.GuestDTO;


public interface GuestService {


	GuestDTO getGuestById(Long id);

	GuestDTO updateGuest(Long id, GuestDTO guest);

	void deleteGuest(Long id);

	List<GuestDTO> getAllGuests();

	GuestDTO createGuest(GuestDTO guestDTO);

	List<GuestDTO> getGuestsByRoomType(String roomtype);
}