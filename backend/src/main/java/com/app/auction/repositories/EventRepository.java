
package com.app.auction.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.app.auction.entities.Event;
import com.app.auction.entities.User;


public interface EventRepository extends JpaRepository<Event, Integer> {

	List<Event> findByUser(User user);
	
	@Query(value = "select * from event where end_date < current_timestamp() and status = 'ACTIVE'", nativeQuery = true)
	List<Event> findCompletedEvents();

	

}
