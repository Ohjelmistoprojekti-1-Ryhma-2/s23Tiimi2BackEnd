package com.dogstore.dogstore;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.dogstore.dogstore.models.Manufacturer;
import com.dogstore.dogstore.models.Product;
import com.dogstore.dogstore.repository.ManufacturerRepository;
import com.dogstore.dogstore.repository.ProductRepository;

@SpringBootApplication
public class DogstoreApplication {

	// private static final Logger log =
	// LoggerFactory.getLogger(DogstoreApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(DogstoreApplication.class, args);
	}

	@Bean
	public CommandLineRunner product_create(ProductRepository pRepository) {
		return (args) -> {
			pRepository.save(new Product("Sadetakki", "Keltainen", "S", 39.99, "Rukka"));
			pRepository.save(new Product("Talvitakki", "Musta", "XL", 64.99, "Feel Active"));
		};
	}

	@Bean
	public CommandLineRunner manufacturer_create(ManufacturerRepository mRepository) {
		return (args) -> {
			mRepository
					.save(new Manufacturer("Rukka", "Suomusjarvi 99, Finland", "EU", "+358 559 A 000", "rukka@ltd.fi"));
		};
	}

}
