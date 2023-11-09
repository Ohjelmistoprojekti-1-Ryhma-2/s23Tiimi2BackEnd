package com.dogstore.dogstore.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.dogstore.dogstore.models.Manufacturer;
import com.dogstore.dogstore.repository.ManufacturerRepository;

@Controller
public class ManufacturerController {

	@Autowired
	private ManufacturerRepository manufacturerRepository;

	// Redirects to list of manufacturers page.
	@GetMapping("/listmanufacturers")
	public String listOfManufacturers(Model model) {
		model.addAttribute("manufacturers", manufacturerRepository.findAll());
		return "listmanufacturers"; // Moves to endpoint /listmanufacturers.html.
	}

	// Move to /addmanufacturer -endpoint,
	// which has a form for the new manufacturer.
	@GetMapping("/addmanufacturer")
	public String addManufacturerForm(Model model) {
		model.addAttribute("manufacturer", new Manufacturer());
		return "addmanufacturer";
	}

	// Add and save the new manufacturer's contact info.
	// Moves back to /listmanufacturer -endpoint.
	@PostMapping("/addmanufacturer")
	public String addProduct(@ModelAttribute Manufacturer manufacturer) {
		manufacturerRepository.save(manufacturer);
		return "redirect:/listmanufacturers";
	}

	// Retrieving a manufacturer by its ID in order delete it from list of
	// manufacturers.
	@GetMapping("/deletemanufacturer/{id}")
	public String deleteManufacturer(@PathVariable("id") Long id, Model model) {
		model.addAttribute("manufacturer", manufacturerRepository.findById(id));
		manufacturerRepository.deleteById(id);
		return "redirect:/listmanufacturers"; // Redirect to endpoint /listmanufacturers.html
	}
}
