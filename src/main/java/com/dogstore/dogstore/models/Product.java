package com.dogstore.dogstore.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
public class Product {
	// Automatic running Id
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	// Other essential parameters about product.

	// Values can be written / given in Finnish.

	@NotBlank(message = "Name is mandatory!")
	private String name;

	@NotBlank(message = "Color is mandatory!")
	private String color;

	@NotBlank(message = "Size is mandatory!")
	private String size;
	
	@NotNull(message = "Price is mandatory!")
	private double price;

	@NotNull(message = "Quantity is mandatory!")
	@Min(value = 0, message = "Quantity can't be less than 0!")
	private int quantity;

	@ManyToOne // According to Juha Hinkula's book "Hands-On Full Stack Development with Spring
				// Boot 2 and React - Second Edition", Chapter 'Relationships between tables'
	@JoinColumn(name = "manufacturer_id")
	private Manufacturer manufacturer;

	@ManyToOne
	@JoinColumn(name = "type_id")
	private Type type;

	public Product() {
		// this.type = "";
	}

	public Product(String name, String color, String size, double price, Type type, Manufacturer manufacturer) {
		this.name = name;
		this.color = color;
		this.size = size;
		this.price = price;
		this.type = type;
		this.manufacturer = manufacturer;
	}

	public Product(@NotBlank(message = "Name is mandatory!") String name,
			@NotBlank(message = "Color is mandatory!") String color,
			@NotBlank(message = "Size is mandatory!") String size,
			@NotNull(message = "Price is mandatory!") double price,
			@NotNull(message = "Quantity is mandatory!") @Min(value = 0, message = "Quantity can't be less than 0!") int quantity,
			Type type, Manufacturer manufacturer) {
		this.name = name;
		this.color = color;
		this.size = size;
		this.price = price;
		this.quantity = quantity;
		this.type = type;
		this.manufacturer = manufacturer;
	}

	/**
	 * @return Long return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return String return the color
	 */
	public String getColor() {
		return color;
	}

	/**
	 * @param color the color to set
	 */
	public void setColor(String color) {
		this.color = color;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}
	/**
	 * @return double return the price
	 */
	public double getPrice() {
		return price;
	}

	/**
	 * @param price the price to set
	 */
	public void setPrice(double price) {
		this.price = price;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	/**
	 * @return String return the type
	 */
	public Type getType() {
		return type;
	}

	/**
	 * @param type the product_type to set
	 */
	public void setType(Type type) {
		this.type = type;
	}

	public Manufacturer getManufacturer() {
		return manufacturer;
	}

	public void setManufacturer(Manufacturer manufacturer) {
		this.manufacturer = manufacturer;
	}

}
