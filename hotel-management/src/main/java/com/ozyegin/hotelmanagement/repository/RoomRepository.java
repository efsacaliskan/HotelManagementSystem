package com.ozyegin.hotelmanagement.repository;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

import com.ozyegin.hotelmanagement.model.Room;

public interface RoomRepository extends JpaRepository<Room, Long> {


	public List<Room> findByIsAvailable(boolean isAvailable);

	public List<Room> findByHousekeepingsId(long id);
}
