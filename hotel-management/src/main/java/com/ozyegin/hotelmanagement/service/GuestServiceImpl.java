package com.ozyegin.hotelmanagement.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ozyegin.hotelmanagement.dto.GuestDTO;
import com.ozyegin.hotelmanagement.mapper.GuestMapper;
import com.ozyegin.hotelmanagement.model.Guest;
import com.ozyegin.hotelmanagement.repository.GuestRepository;


@Service
@Transactional
public class GuestServiceImpl implements GuestService {
	@Autowired
	private final GuestRepository guestRepository;

	@Autowired
	public GuestServiceImpl(GuestRepository guestRepository) {
		this.guestRepository = guestRepository;
	}

	@Autowired
	GuestMapper guestMapper;

	// Retrieve a guest by ID
	@Override
	public GuestDTO getGuestById(Long id) {

		Optional<Guest> opt = guestRepository.findById(id);
		if (opt.isPresent()) {
			Guest guest = opt.get();
			return guestMapper.guestToGuestDTO(guest);
		} else
			return null;
	}

	// Update guest information
	@Override
	public GuestDTO updateGuest(Long id, GuestDTO updatedGuest) {
		GuestDTO guestDTO = getGuestById(id);
		guestDTO.setName(updatedGuest.getName());
		guestDTO.setContactNumber(updatedGuest.getContactNumber());
		guestDTO.setEmail(updatedGuest.getEmail());
		guestDTO.setAddress(updatedGuest.getAddress());
		Guest guest = guestMapper.guestDTOToGuest(guestDTO);
		Guest updated = guestRepository.save(guest);
		return guestMapper.guestToGuestDTO(updated);
	}

	// Delete a guest
	@Override
	public void deleteGuest(Long id) {
		guestRepository.deleteById(id);
	}

	// List all guests
	@Override
	public List<GuestDTO> getAllGuests() {
		List<Guest> list = guestRepository.findAll();
		List<GuestDTO> dtoList = new ArrayList<GuestDTO>();
		for (Guest guest : list) {
			dtoList.add(guestMapper.guestToGuestDTO(guest));
		}
		return dtoList;
	}

	@Override
	public GuestDTO createGuest(GuestDTO guestDTO) {

		Guest guest = guestMapper.guestDTOToGuest(guestDTO);
		Guest savedGuest = guestRepository.save(guest);
		GuestDTO savedGuestDTO = guestMapper.guestToGuestDTO(savedGuest);
		return savedGuestDTO;

	}

	@Override
	public List<GuestDTO> getGuestsByRoomType(String roomtype) {
		List<Guest> list = guestRepository.findGuestsWithRoomType(roomtype);
		List<GuestDTO> dtoList = new ArrayList<GuestDTO>();
		for (Guest guest : list) {
			dtoList.add(guestMapper.guestToGuestDTO(guest));
		}
		return dtoList;
	}

}