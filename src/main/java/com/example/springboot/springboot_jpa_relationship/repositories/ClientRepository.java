package com.example.springboot.springboot_jpa_relationship.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.example.springboot.springboot_jpa_relationship.entities.Client;
// JOIN:
// Une tablas/relaciones para consultar o filtrar datos,
// pero no necesariamente carga la relación en memoria.

// JOIN FETCH:
// Une y además carga la relación inmediatamente dentro de la entidad.
public interface ClientRepository extends CrudRepository<Client,Long>{ 

    @Query("select c from Client c left join fetch c.addresses where c.id = ?1") 
    Optional<Client> findOneWithAddresses(Long id);

    @Query("select c from Client c left join fetch c.invoices where c.id = ?1") 
    Optional<Client> findOneWithInvoices(Long id);

    @Query("select c from Client c left join fetch c.invoices left join fetch c.addresses where c.id = ?1") 
    Optional<Client> findOne(Long id);

}
