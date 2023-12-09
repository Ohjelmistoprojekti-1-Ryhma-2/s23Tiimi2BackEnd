package com.dogstore.dogstore;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.dogstore.dogstore.models.Customer;
import com.dogstore.dogstore.models.Manufacturer;
import com.dogstore.dogstore.models.Product;
import com.dogstore.dogstore.models.Type;
import com.dogstore.dogstore.repository.CustomerRepository;
import com.dogstore.dogstore.repository.ManufacturerRepository;
import com.dogstore.dogstore.repository.ProductRepository;
import com.dogstore.dogstore.repository.TypeRepository;

@SpringBootApplication
public class DogstoreApplication {

	// private static final Logger log =
	// LoggerFactory.getLogger(DogstoreApplication.class);
	private final ManufacturerRepository manufacturerRepository;
	private final ProductRepository productRepository;
	private final TypeRepository typeRepository;
	private final CustomerRepository customerRepository;

	public static void main(String[] args) {
		SpringApplication.run(DogstoreApplication.class, args);
	}

	public DogstoreApplication(ManufacturerRepository manufacturerRepository, ProductRepository productRepository,
			TypeRepository typeRepository, CustomerRepository customerRepository) {
		this.typeRepository = typeRepository;
		this.manufacturerRepository = manufacturerRepository;
		this.productRepository = productRepository;
		this.customerRepository = customerRepository;
	}

	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/api/products").allowedOrigins("http://localhost:5173");
			}
		};
	}

	@Bean
	@Transactional
	public CommandLineRunner initData() {
		return (args) -> {

			// First we create manufacturer's info.
			Manufacturer rukka = new Manufacturer("Rukka", "Suomusjarvi 99, Finland", "EU", "+358 559 A 000",
					"rukka@ltd.fi");
			rukka = manufacturerRepository.save(rukka);
			Manufacturer purenatural = new Manufacturer("Purenatural", "Oulunsalo A -9, Finland", "EU",
					"+358 888 B 000", "purenatural@ltd.fi");
			purenatural = manufacturerRepository.save(purenatural);
			Manufacturer littlebigger = new Manufacturer("Little & Bigger", " Zenchu street, China", "AS",
					"+86 AAA BBB CCC", "littlebigger@gmail.com");
			littlebigger = manufacturerRepository.save(littlebigger);

			// Secondly we create different type info.

			Type clothing = typeRepository.save(new Type("clothing"));
			Type food = typeRepository.save(new Type("food"));
			Type toy = typeRepository.save(new Type("toy"));

			// Afterward we add and save product info,
			// that has also recently created manufacturer and type info.

			productRepository.save(new Product("Sadetakki", "Keltainen", "S",39.99, clothing, rukka));
			productRepository.save(new Product("Talvitakki", "Musta", "L", 64.99, clothing, rukka));
			productRepository.save(new Product("Broilerin fileelastu", "Keltaruskea", "-", 6.99, food, purenatural));
			productRepository.save(new Product("Crinkle Rope köysilenkki pallolla",  "Siitrunakeltainen & Jännävioletti", "-",
					7.99, toy, littlebigger));

			// Lastly, we create and save Admin's personal info.
			customerRepository.save(new Customer(null, "Admin", "Koulukatu 8", "+3580000", "admin@gmail.com"));
		};
	}
}
