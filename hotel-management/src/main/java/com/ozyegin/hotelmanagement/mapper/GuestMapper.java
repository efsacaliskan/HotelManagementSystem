package com.ozyegin.hotelmanagement.mapper;


import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.ozyegin.hotelmanagement.dto.GuestDTO;
import com.ozyegin.hotelmanagement.model.Guest;




@Mapper(componentModel = "spring")
public interface GuestMapper {
	
	 GuestMapper INSTANCE = Mappers.getMapper(GuestMapper.class);
	 
	   
	    GuestDTO guestToGuestDTO(Guest guest);
	    
	    @InheritInverseConfiguration
	    @Mapping(target = "reservations", ignore = true)
	    Guest guestDTOToGuest(GuestDTO guestDTO);

	    
	  
}


