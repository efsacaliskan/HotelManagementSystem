package com.ozyegin.hotelmanagement.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ozyegin.hotelmanagement.dto.RoomDTO;
import com.ozyegin.hotelmanagement.mapper.RoomMapper;
import com.ozyegin.hotelmanagement.model.Room;
import com.ozyegin.hotelmanagement.repository.RoomRepository;



@Service
@Transactional
public class RoomServiceImpl implements RoomService {

	@Autowired
	private final RoomRepository roomRepository;

	@Autowired
	public RoomServiceImpl(RoomRepository roomRepository) {
		this.roomRepository = roomRepository;

	}

	@Autowired
	RoomMapper roomMapper;

	@Override
	public RoomDTO createRoom(RoomDTO roomDTO) {
		Room room = roomMapper.roomDTOToRoom(roomDTO);
		Room savedRoom = roomRepository.save(room);
		RoomDTO savedRoomDTO = roomMapper.roomToRoomDTO(savedRoom);
		return savedRoomDTO;

	}

	@Override
	public RoomDTO getRoomById(Long id) {
		Optional<Room> opt = roomRepository.findById(id);
		if (opt.isPresent()) {
			Room room = opt.get();
			return roomMapper.roomToRoomDTO(room);
		} else
			return null;
	}

	@Override
	public RoomDTO updateRoom(Long id, RoomDTO updatedRoom) {
		RoomDTO roomDTO = getRoomById(id);
		roomDTO.setRoomNumber(updatedRoom.getRoomNumber());
		roomDTO.setType(updatedRoom.getType());
		roomDTO.setAvailable(updatedRoom.isAvailable());
		roomDTO.setPrice(updatedRoom.getPrice());
		Room room = roomMapper.roomDTOToRoom(roomDTO);
		Room updated = roomRepository.save(room);
		return roomMapper.roomToRoomDTO(updated);

	}

	@Override
	public void deleteRoom(Long id) {
		roomRepository.deleteById(id);

	}

	@Override
	public List<RoomDTO> getAllRooms() {

		List<Room> list = roomRepository.findAll();
		List<RoomDTO> dtoList = new ArrayList<RoomDTO>();
		for (Room room : list) {
			dtoList.add(roomMapper.roomToRoomDTO(room));
		}
		return dtoList;
	}

	@Override
	public List<RoomDTO> getAllAvailableRooms() {
		List<RoomDTO> dtoList = new ArrayList<RoomDTO>();
		for (Room room : roomRepository.findByIsAvailable(true)) {
			dtoList.add(roomMapper.roomToRoomDTO(room));
		}
		return dtoList;
	}

	@Override
	public List<RoomDTO> getAllRoomsByHousekeepingId(Long id) {
		List<Room> list = roomRepository.findByHousekeepingsId(id);
		List<RoomDTO> dtoList = new ArrayList<RoomDTO>();
		for (Room room : list) {
			dtoList.add(roomMapper.roomToRoomDTO(room));
		}
		return dtoList;
	}

}
