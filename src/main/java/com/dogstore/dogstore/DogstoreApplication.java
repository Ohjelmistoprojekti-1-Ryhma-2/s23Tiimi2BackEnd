package com.dogstore.dogstore;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.transaction.annotation.Transactional;

import com.dogstore.dogstore.models.Manufacturer;
import com.dogstore.dogstore.models.Product;
import com.dogstore.dogstore.repository.ManufacturerRepository;
import com.dogstore.dogstore.repository.ProductRepository;

@SpringBootApplication
public class DogstoreApplication {

	// private static final Logger log =
	// LoggerFactory.getLogger(DogstoreApplication.class);
	private final ManufacturerRepository manufacturerRepository;
    private final ProductRepository productRepository;

	public DogstoreApplication(ManufacturerRepository manufacturerRepository, ProductRepository productRepository) {
        this.manufacturerRepository = manufacturerRepository;
        this.productRepository = productRepository;
    }

	public static void main(String[] args) {
		SpringApplication.run(DogstoreApplication.class, args);
	}

	@Bean
	@Transactional
	public CommandLineRunner initData() {
		return (args) -> {
			Manufacturer rukka = new Manufacturer("Rukka", "Suomusjarvi 99, Finland", "EU", "+358 559 A 000", "rukka@ltd.fi");
			rukka = manufacturerRepository.save(rukka);

			productRepository.save(new Product("Sadetakki", "Keltainen", "S", 39.99, rukka));
			productRepository.save(new Product("Talvitakki", "Musta", "XL", 64.99, rukka));
	};
}
}
