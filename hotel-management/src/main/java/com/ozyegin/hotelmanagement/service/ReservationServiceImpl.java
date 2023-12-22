package com.ozyegin.hotelmanagement.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ozyegin.hotelmanagement.dto.ReservationDTO;
import com.ozyegin.hotelmanagement.mapper.ReservationMapper;
import com.ozyegin.hotelmanagement.model.Reservation;
import com.ozyegin.hotelmanagement.repository.ReservationRepository;



@Service
@Transactional
public class ReservationServiceImpl implements ReservationService {
	@Autowired
	private final ReservationRepository reservationRepository;

	@Autowired
	public ReservationServiceImpl(ReservationRepository reservationRepository) {
		this.reservationRepository = reservationRepository;
	}

	@Autowired
	ReservationMapper reservationMapper;

	// Create a new reservation
	@Override
	public ReservationDTO createReservation(ReservationDTO reservationDTO) {
		Reservation reservation = reservationMapper.reservationDTOToReservation(reservationDTO);
		Reservation savedReservation = reservationRepository.save(reservation);
		ReservationDTO savedReservationDTO = reservationMapper.reservationToReservationDTO(savedReservation);
		return savedReservationDTO;

	}

	// Retrieve a reservation by ID
	@Override
	public ReservationDTO getReservationById(Long id) {
		Optional<Reservation> opt = reservationRepository.findById(id);
		if (opt.isPresent()) {
			Reservation reservation = opt.get();
			return reservationMapper.reservationToReservationDTO(reservation);
		} else
			return null;
	}

	// Update reservation details
	@Override
	public ReservationDTO updateReservation(Long id, ReservationDTO updatedReservation) {

		ReservationDTO reservationDTO = getReservationById(id);
		reservationDTO.setGuestId(updatedReservation.getGuestId());
		reservationDTO.setRoomId(updatedReservation.getRoomId());
		reservationDTO.setStartDate(updatedReservation.getStartDate());
		reservationDTO.setEndDate(updatedReservation.getEndDate());
		reservationDTO.setStatus(updatedReservation.getStatus());
		Reservation reservation = reservationMapper.reservationDTOToReservation(reservationDTO);
		Reservation updated = reservationRepository.save(reservation);
		return reservationMapper.reservationToReservationDTO(updated);

	}

	// Delete a reservation
	@Override
	public void deleteReservation(Long id) {
		reservationRepository.deleteById(id);
	}

	// List all reservations
	@Override
	public List<ReservationDTO> getAllReservations() {

		List<Reservation> list = reservationRepository.findAll();
		List<ReservationDTO> dtoList = new ArrayList<ReservationDTO>();
		for (Reservation reservation : list) {
			dtoList.add(reservationMapper.reservationToReservationDTO(reservation));
		}
		return dtoList;
	}

	@Override
	public List<ReservationDTO> getAllReservationsByGuestId(Long id) {
		List<Reservation> list = reservationRepository.findByGuestId(id);
		List<ReservationDTO> dtoList = new ArrayList<ReservationDTO>();
		for (Reservation reservation : list) {
			dtoList.add(reservationMapper.reservationToReservationDTO(reservation));
		}
		return dtoList;
	}

}
