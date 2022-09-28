package com.app.auction.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.auction.entities.Event;
import com.app.auction.entities.EventEnrolment;
import com.app.auction.entities.User;

public interface EnrolmentRepository extends JpaRepository<EventEnrolment, Integer> {

	List<EventEnrolment> findByEvent(Event event);

	Optional<EventEnrolment> findByEventAndUser(Event event, User user);
	
	

}
