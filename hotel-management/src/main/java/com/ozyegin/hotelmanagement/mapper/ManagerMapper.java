package com.ozyegin.hotelmanagement.mapper;


import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.ozyegin.hotelmanagement.dto.ManagerDTO;
import com.ozyegin.hotelmanagement.model.Manager;



@Mapper(componentModel = "spring")
public interface ManagerMapper {

    ManagerMapper INSTANCE = Mappers.getMapper(ManagerMapper.class);

    ManagerDTO managerToManagerDTO(Manager manager);
    
    @InheritInverseConfiguration
    Manager managerDTOToManager(ManagerDTO managerDTO);
}

