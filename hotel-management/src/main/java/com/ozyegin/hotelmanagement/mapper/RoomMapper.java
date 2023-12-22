package com.ozyegin.hotelmanagement.mapper;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.ozyegin.hotelmanagement.dto.RoomDTO;
import com.ozyegin.hotelmanagement.model.Room;



@Mapper(componentModel = "spring")
public interface RoomMapper {

    RoomMapper INSTANCE = Mappers.getMapper(RoomMapper.class);
    

    RoomDTO roomToRoomDTO(Room room);
    
    @InheritInverseConfiguration
    @Mapping(target = "reservations", ignore = true)
    Room roomDTOToRoom(RoomDTO roomDTO);
}





