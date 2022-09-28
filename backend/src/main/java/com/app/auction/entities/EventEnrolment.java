package com.app.auction.entities;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Table(name = "enrolment")
@Entity
@Getter
@Setter

public class EventEnrolment {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int enrolmentId;

	@ManyToOne
	@JoinColumn(name = "event_Id")
	private Event event;
	
	@OneToOne
	@JoinColumn(name="user_Id")
	private User user;
	
	private float bidAmount;
	
	@Column(columnDefinition = "TIMESTAMP")
	private LocalDateTime bidTime;

}
