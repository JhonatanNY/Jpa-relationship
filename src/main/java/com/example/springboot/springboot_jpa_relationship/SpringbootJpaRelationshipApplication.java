package com.example.springboot.springboot_jpa_relationship;

import java.util.Arrays;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.Transactional;

import com.example.springboot.springboot_jpa_relationship.entities.Address;
import com.example.springboot.springboot_jpa_relationship.entities.Client;
import com.example.springboot.springboot_jpa_relationship.entities.Invoice;
import com.example.springboot.springboot_jpa_relationship.repositories.ClientRepository;
import com.example.springboot.springboot_jpa_relationship.repositories.InvoiceRepository;

@SpringBootApplication
public class SpringbootJpaRelationshipApplication implements CommandLineRunner {

	@Autowired
	private ClientRepository clientRepository;

	@Autowired
	private InvoiceRepository invoiceRepository;

	public static void main(String[] args) {
		SpringApplication.run(SpringbootJpaRelationshipApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		OneToManyFindById();
	}

	@Transactional
	public void OneToManyFindById() {

		Optional<Client> optional = clientRepository.findById(2L);
		optional.ifPresent(client -> {

			Address address1 = new Address("El verjel", 1234);
			Address address2 = new Address("Vasco de Gama", 9875);

			client.setAddresses(Arrays.asList(address1,address2));

			clientRepository.save(client);

			System.out.println(client);

		});
		

	}

	@Transactional
	public void OneToMany() {
		Client client = new Client("Fran", "Moras");

		Address address1 = new Address("El verjel", 1234);
		Address address2 = new Address("Vasco de Gama", 9875);

		client.getAddresses().add(address1);
		client.getAddresses().add(address2);

		clientRepository.save(client);

		System.out.println(client);

	}

	@Transactional
	public void manyToOne() {

		Client client = new Client("Jhon", "Yataco");
		clientRepository.save(client);

		Invoice invoice = new Invoice("Compras de oficina", 2000L);
		invoice.setClient(client);
		Invoice invoiceDB = invoiceRepository.save(invoice);
		System.out.println(invoiceDB);

	}

	@Transactional
	public void manyToOneFindByIdClient() {

		Optional<Client> optionalClient = clientRepository.findById(1L);
		if (optionalClient.isPresent()) {
			Client client = optionalClient.orElseThrow();
			Invoice invoice = new Invoice("Compras de oficina 2", 3000L);
			invoice.setClient(client);
			Invoice invoiceDB = invoiceRepository.save(invoice);
			System.out.println(invoiceDB);
		}

	}

}
