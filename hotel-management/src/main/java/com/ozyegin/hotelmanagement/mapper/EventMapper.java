package com.ozyegin.hotelmanagement.mapper;


import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.ozyegin.hotelmanagement.dto.EventDTO;
import com.ozyegin.hotelmanagement.model.Event;

@Mapper(componentModel = "spring")
public interface EventMapper {
	EventMapper INSTANCE = Mappers.getMapper(EventMapper.class);

	@Mapping(target = "id", source = "event.id")
    @Mapping(target = "name", source = "event.name")
    @Mapping(target = "description", source = "event.description")
    @Mapping(target = "eventDateTime", source = "event.eventDateTime")
    @Mapping(target = "location", source = "event.location")
	EventDTO eventToEventDTO(Event event);

	@InheritInverseConfiguration
	@Mapping(target = "name", source = "dto.name")
    @Mapping(target = "description", source = "dto.description")
    @Mapping(target = "eventDateTime", source = "dto.eventDateTime")
    @Mapping(target = "location", source = "dto.location")
	Event eventDTOToEvent(EventDTO dto);
	
}

