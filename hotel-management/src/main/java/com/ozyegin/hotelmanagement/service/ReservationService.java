package com.ozyegin.hotelmanagement.service;


import java.util.List;

import com.ozyegin.hotelmanagement.dto.ReservationDTO;



public interface ReservationService {

	ReservationDTO createReservation(ReservationDTO reservationDTO);

	ReservationDTO getReservationById(Long id);

	ReservationDTO updateReservation(Long id, ReservationDTO updatedReservation);

	void deleteReservation(Long id);

	List<ReservationDTO> getAllReservations();

	List<ReservationDTO> getAllReservationsByGuestId(Long id);

}
