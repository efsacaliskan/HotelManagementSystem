package com.ozyegin.hotelmanagement.service;
import java.util.List;

import com.ozyegin.hotelmanagement.dto.ManagerDTO;


public interface ManagerService {

	ManagerDTO createManager(ManagerDTO managerDTO);

	ManagerDTO getManagerById(Long id);

	ManagerDTO updateManager(Long id, ManagerDTO updatedManager);

	void deleteManager(Long id);

	List<ManagerDTO> getAllManagers();

	List<ManagerDTO> getAllManagersByEventId(Long id);
}
