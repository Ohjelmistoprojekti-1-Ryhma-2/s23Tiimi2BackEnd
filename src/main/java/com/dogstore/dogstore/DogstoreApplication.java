package com.dogstore.dogstore;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.transaction.annotation.Transactional;

import com.dogstore.dogstore.models.Customer;
import com.dogstore.dogstore.models.Manufacturer;
import com.dogstore.dogstore.models.Product;
import com.dogstore.dogstore.repository.CustomerRepository;
import com.dogstore.dogstore.repository.ManufacturerRepository;
import com.dogstore.dogstore.repository.ProductRepository;

@SpringBootApplication
public class DogstoreApplication {

	// private static final Logger log =
	// LoggerFactory.getLogger(DogstoreApplication.class);
	private final ManufacturerRepository manufacturerRepository;
	private final ProductRepository productRepository;
	private final CustomerRepository customerRepository;

	public DogstoreApplication(ManufacturerRepository manufacturerRepository, ProductRepository productRepository,
			CustomerRepository customerRepository) {
		this.manufacturerRepository = manufacturerRepository;
		this.productRepository = productRepository;
		this.customerRepository = customerRepository;
	}

	public static void main(String[] args) {
		SpringApplication.run(DogstoreApplication.class, args);
	}

	@Bean
	@Transactional
	public CommandLineRunner initData() {
		return (args) -> {
			Manufacturer rukka = new Manufacturer("Rukka", "Suomusjarvi 99, Finland", "EU", "+358 559 A 000",
					"rukka@ltd.fi");
			rukka = manufacturerRepository.save(rukka);
			Manufacturer purenatural = new Manufacturer("Purenatural", "Oulunsalo A -9, Finland", "EU",
					"+358 888 B 000", "purenatural@ltd.fi");
			purenatural = manufacturerRepository.save(purenatural);
			Manufacturer littlebigger = new Manufacturer("Little & Bigger", " Zenchu street, China", "AS",
					"+86 AAA BBB CCC", "littlebigger@gmail.com");
			littlebigger = manufacturerRepository.save(littlebigger);

			customerRepository.save(new Customer(null, "Admin", "Koulukatu 8", "+3580000", "admin@gmail.com"));

			productRepository.save(new Product("Sadetakki", "Vaate", "Keltainen", "S", 39.99, rukka));
			productRepository.save(new Product("Talvitakki", "Vaate", "Musta", "L", 64.99, rukka));
			productRepository.save(new Product("Broilerin fileelastu", "Ruoka", "Keltaruskea", "-", 6.99, purenatural));
			productRepository.save(new Product("Crinkle Rope köysilenkki pallolla", "Lelu",
					"Siitrunakeltainen & Jännävioletti", "-", 7.99, littlebigger));

		};
	}
}
