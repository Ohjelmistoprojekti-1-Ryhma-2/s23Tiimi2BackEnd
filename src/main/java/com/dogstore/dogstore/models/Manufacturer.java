package com.dogstore.dogstore.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

//This entity is for informative list of many manufacturers.
//Manufacturers' company information is for ordering products
//directly from manufacturers to dogstore's storage, 
//now and in future.

@Entity
public class Manufacturer {
	// Automatic running Id
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	// Essential informative parameters about manufactures
	private String name;
	private String address;
	private String continent;
	private String phone;
	private String email;

	public Manufacturer() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Manufacturer(String name, String address, String continent, String phone, String email) {
		this.name = name;
		this.address = address;
		this.continent = continent;
		this.phone = phone;
		this.email = email;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getContinent() {
		return continent;

	}

	public void setContinent(String continent) {
		this.continent = continent;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
