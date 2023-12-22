package com.ozyegin.hotelmanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ozyegin.hotelmanagement.model.Housekeeping;

public interface HousekeepingRepository extends JpaRepository<Housekeeping, Long> {

}
