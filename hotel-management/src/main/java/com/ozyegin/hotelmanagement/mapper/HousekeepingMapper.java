package com.ozyegin.hotelmanagement.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.ozyegin.hotelmanagement.dto.HousekeepingDTO;
import com.ozyegin.hotelmanagement.model.Housekeeping;



@Mapper(componentModel = "spring")
public interface HousekeepingMapper {

    HousekeepingMapper INSTANCE = Mappers.getMapper(HousekeepingMapper.class);
    
  
    @Mapping(source = "room.id", target = "roomId")
    HousekeepingDTO housekeepingToHousekeepingDTO(Housekeeping housekeeping);

    @Mapping(source = "roomId", target = "room.id")
    Housekeeping housekeepingDTOToHousekeeping(HousekeepingDTO dto);


}


