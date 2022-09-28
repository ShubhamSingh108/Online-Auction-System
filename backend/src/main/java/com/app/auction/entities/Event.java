package com.app.auction.entities;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
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

@Table(name = "event")
@Entity
@Setter
@Getter
@NoArgsConstructor
@ToString
public class Event {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int eventId;

	@Column(name = "startDate", columnDefinition = "TIMESTAMP")
	private LocalDateTime startDate;

	@Column(name = "endDate", columnDefinition = "TIMESTAMP")
	private LocalDateTime endDate;

	@ManyToOne//
	@JoinColumn(name = "user_id")
	private User user;
	
	@OneToMany(mappedBy = "event", cascade = CascadeType.ALL)
	private Set<EventEnrolment> enrolment = new HashSet<>();
	
	@OneToOne
	@JoinColumn(name = "item_id")
	private Item item;

	@OneToOne//(cascade={CascadeType.MERGE, CascadeType.PERSIST}, fetch=FetchType.EAGER)
	@JoinColumn(name = "winner")
	private User winner;

	@Enumerated(EnumType.STRING)
	private Status status;

}
