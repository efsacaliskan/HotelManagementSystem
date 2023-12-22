package com.ozyegin.hotelmanagement.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ozyegin.hotelmanagement.model.Reservation;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
	
	public List<Reservation> findByGuestId(Long id);
}
