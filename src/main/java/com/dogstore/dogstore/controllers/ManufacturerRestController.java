package com.dogstore.dogstore.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dogstore.dogstore.models.Manufacturer;
import com.dogstore.dogstore.repository.ManufacturerRepository;

// @CrossOrigin
@RestController
public class ManufacturerRestController {

	@Autowired
	private ManufacturerRepository manufacturerRepository;

	// REST Manufacturer interface
	@GetMapping("/manufacturers")
	public List<Manufacturer> manufacturersListRest() {
		return (List<Manufacturer>) manufacturerRepository.findAll();
	}

}
