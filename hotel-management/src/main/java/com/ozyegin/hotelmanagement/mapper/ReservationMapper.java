package com.ozyegin.hotelmanagement.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.ozyegin.hotelmanagement.dto.ReservationDTO;
import com.ozyegin.hotelmanagement.model.Reservation;


@Mapper(componentModel = "spring")
public interface ReservationMapper {

	ReservationMapper INSTANCE = Mappers.getMapper(ReservationMapper.class);


	@Mapping(source = "guest.id", target = "guestId")
	@Mapping(source = "room.id", target = "roomId")
	ReservationDTO reservationToReservationDTO(Reservation reservation);

	@Mapping(source = "guestId", target = "guest.id")
	@Mapping(source = "roomId", target = "room.id")
	@Mapping(target = "name", ignore = true)
	Reservation reservationDTOToReservation(ReservationDTO dto);
}
