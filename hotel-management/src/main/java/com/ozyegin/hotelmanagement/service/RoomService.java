package com.ozyegin.hotelmanagement.service;

import java.util.List;

import com.ozyegin.hotelmanagement.dto.RoomDTO;

public interface RoomService {

	public RoomDTO getRoomById(Long id);

	public RoomDTO updateRoom(Long id, RoomDTO updatedRoom);

	public void deleteRoom(Long id);

	RoomDTO createRoom(RoomDTO roomDTO);

	List<RoomDTO> getAllRooms();

	List<RoomDTO> getAllAvailableRooms();

	List<RoomDTO> getAllRoomsByHousekeepingId(Long id);

}
