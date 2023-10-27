package com.dogstore.dogstore;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.dogstore.dogstore.models.Product;
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
			pRepository.save(new Product("Ruoka", "musta", 12.00, "rukka"));
		};
	}

}
