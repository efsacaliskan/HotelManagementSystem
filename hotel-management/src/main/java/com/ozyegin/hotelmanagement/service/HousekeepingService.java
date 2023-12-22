package com.ozyegin.hotelmanagement.service;

import java.util.List;

import com.ozyegin.hotelmanagement.dto.HousekeepingDTO;


public interface HousekeepingService {

	HousekeepingDTO createHousekeeping(HousekeepingDTO housekeepingDTO);

	HousekeepingDTO getHousekeepingById(Long id);

	HousekeepingDTO updateHousekeeping(Long id, HousekeepingDTO updatedHousekeeping);

	void deleteHousekeeping(Long id);

	List<HousekeepingDTO> getAllHousekeeping();



}
