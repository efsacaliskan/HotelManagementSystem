package com.ozyegin.hotelmanagement.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ozyegin.hotelmanagement.model.Event;

public interface EventRepository extends JpaRepository<Event, Long> {


	public List<Event> findByManagerId(Long id);

}
