package com.ozyegin.hotelmanagement.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ozyegin.hotelmanagement.model.Guest;

public interface GuestRepository extends JpaRepository<Guest, Long> {


	@Query(value = "SELECT g.id,g.address,g.contact_number,g.email,g.name FROM RESERVATIONS res, ROOMS r,GUESTS g WHERE res.room_id=r.id AND g.id=res.guest_id AND r.type=:roomtype", nativeQuery = true)
	public List<Guest> findGuestsWithRoomType(@Param("roomtype") String roomtype);
}
