package com.ozyegin.hotelmanagement.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ozyegin.hotelmanagement.model.Manager;

public interface ManagerRepository extends JpaRepository<Manager, Long> {

	public List<Manager> findByEventsId(Long id);
}
