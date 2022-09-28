package com.app.auction.entities;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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

import org.hibernate.boot.archive.scan.spi.ClassDescriptor.Categorization;

import com.app.auction.entities.Category;
import com.app.auction.entities.Status;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "items")
@Getter
@Setter
@AllArgsConstructor
public class Item {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int itemID;
	private String itemName;

	private String description;
	private LocalDate purchaseDate;

	private float price;
	@Column(length = 300)
	private String imageURL;

	//private String addedBy;
	//@Enumerated(EnumType.STRING)
//	private Status status;
	@Enumerated(EnumType.STRING)
	private Category category;
	@ManyToOne
	@JoinColumn(name="user_Id")
	private User user;
	//@OneToOne(cascade = CascadeType.ALL)
    //@JoinColumn(name = "event_id", referencedColumnName = "itemId")
	
	//private Event event;
	/*
	 * @OneToMany(mappedBy="item",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	 * 
	 * private List<Seller> sellers=new ArrayList<>();
	 */
	public Item() {
		super();
		// TODO Auto-generated constructor stub
	}

}
