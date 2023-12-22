package com.ozyegin.hotelmanagement.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ozyegin.hotelmanagement.dto.ManagerDTO;
import com.ozyegin.hotelmanagement.mapper.ManagerMapper;
import com.ozyegin.hotelmanagement.model.Manager;
import com.ozyegin.hotelmanagement.repository.ManagerRepository;



@Service
@Transactional
public class ManagerServiceImpl implements ManagerService {
	@Autowired
	private final ManagerRepository managerRepository;

	@Autowired
	public ManagerServiceImpl(ManagerRepository managerRepository) {
		this.managerRepository = managerRepository;
	}

	@Autowired
	ManagerMapper managerMapper;

	// Create a new manager
	@Override
	public ManagerDTO createManager(ManagerDTO managerDTO) {
		Manager manager = managerMapper.managerDTOToManager(managerDTO);
		Manager savedManager = managerRepository.save(manager);
		ManagerDTO savedManagerDTO = managerMapper.managerToManagerDTO(savedManager);
		return savedManagerDTO;

	}

	// Retrieve a manager by ID
	@Override
	public ManagerDTO getManagerById(Long id) {
		Optional<Manager> opt = managerRepository.findById(id);
		if (opt.isPresent()) {
			Manager manager = opt.get();
			return managerMapper.managerToManagerDTO(manager);
		} else
			return null;
	}

	// Update manager details
	@Override
	public ManagerDTO updateManager(Long id, ManagerDTO updatedManager) {
		ManagerDTO managerDTO = getManagerById(id);
		managerDTO.setName(updatedManager.getName());
		managerDTO.setContactNumber(updatedManager.getContactNumber());
		managerDTO.setEmail(updatedManager.getEmail());
		managerDTO.setDepartment(updatedManager.getDepartment());

		Manager manager = managerMapper.managerDTOToManager(managerDTO);
		Manager updated = managerRepository.save(manager);
		return managerMapper.managerToManagerDTO(updated);

	}

	// Delete a manager
	@Override
	public void deleteManager(Long id) {
		managerRepository.deleteById(id);
	}

	// List all managers
	@Override
	public List<ManagerDTO> getAllManagers() {
		List<Manager> list = managerRepository.findAll();
		List<ManagerDTO> dtoList = new ArrayList<ManagerDTO>();
		for (Manager manager : list) {
			dtoList.add(managerMapper.managerToManagerDTO(manager));
		}
		return dtoList;
	}

	public List<ManagerDTO> getAllManagersByEventId(Long id) {
		List<Manager> list = managerRepository.findByEventsId(id);
		List<ManagerDTO> dtoList = new ArrayList<ManagerDTO>();
		for (Manager manager : list) {
			dtoList.add(managerMapper.managerToManagerDTO(manager));
		}
		return dtoList;
	}

}