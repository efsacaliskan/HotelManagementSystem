package com.ozyegin.hotelmanagement.service;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ozyegin.hotelmanagement.dto.HousekeepingDTO;
import com.ozyegin.hotelmanagement.mapper.HousekeepingMapper;
import com.ozyegin.hotelmanagement.model.Housekeeping;
import com.ozyegin.hotelmanagement.repository.HousekeepingRepository;


@Service
@Transactional
public class HousekeepingServiceImpl implements HousekeepingService {

	@Autowired
	private final HousekeepingRepository housekeepingRepository;

	@Autowired
	public HousekeepingServiceImpl(HousekeepingRepository housekeepingRepository) {
		this.housekeepingRepository = housekeepingRepository;
	}

	@Autowired
	HousekeepingMapper housekeepingMapper;

	// Add a new housekeeping record
	@Override
	public HousekeepingDTO createHousekeeping(HousekeepingDTO housekeepingDTO) {
		Housekeeping housekeeping = housekeepingMapper.housekeepingDTOToHousekeeping(housekeepingDTO);
		Housekeeping savedHousekeeping = housekeepingRepository.save(housekeeping);
		HousekeepingDTO savedHousekeepingDTO = housekeepingMapper.housekeepingToHousekeepingDTO(savedHousekeeping); 
        return  savedHousekeepingDTO;
		
        
	}

	// Retrieve a housekeeping record by ID
	@Override
	public HousekeepingDTO getHousekeepingById(Long id) {
		
		Optional<Housekeeping> opt=housekeepingRepository.findById(id);
		if (opt.isPresent()) {
			Housekeeping housekeeping= opt.get();
			return housekeepingMapper.housekeepingToHousekeepingDTO(housekeeping);
		}
		else 
			return null;
		
	
	}

	// Update housekeeping details
	@Override
	public HousekeepingDTO updateHousekeeping(Long id, HousekeepingDTO updatedHousekeeping) {
		  
		     
			HousekeepingDTO housekeepingDTO = getHousekeepingById(id);
			housekeepingDTO.setRoomId(updatedHousekeeping.getRoomId());
			housekeepingDTO.setStatus(updatedHousekeeping.getStatus());
			housekeepingDTO.setNotes(updatedHousekeeping.getNotes());
			
			
	        Housekeeping housekeeping = housekeepingMapper.housekeepingDTOToHousekeeping(housekeepingDTO);
	        Housekeeping updated = housekeepingRepository.save(housekeeping);
	        return housekeepingMapper.housekeepingToHousekeepingDTO(updated);
	}
       

	// Delete a housekeeping record
	@Override
	public void deleteHousekeeping(Long id) {
		housekeepingRepository.deleteById(id);
	}
	
	

	// Retrieve all housekeeping records
	  @Override 
	  public List<HousekeepingDTO> getAllHousekeeping() {
		  List<Housekeeping> list =housekeepingRepository.findAll();
		  List<HousekeepingDTO> dtoList=new ArrayList<HousekeepingDTO>(); for
		  (Housekeeping housekeeping : list) {
			  dtoList.add(housekeepingMapper.housekeepingToHousekeepingDTO(housekeeping));
		  	}
		  return dtoList; }
	 

}
