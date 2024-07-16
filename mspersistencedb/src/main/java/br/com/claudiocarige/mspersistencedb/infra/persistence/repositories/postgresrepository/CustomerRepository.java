package br.com.claudiocarige.mspersistencedb.infra.persistence.repositories.postgresrepository;

import br.com.claudiocarige.mspersistencedb.core.domain.entities.Customers;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CustomerRepository extends JpaRepository< Customers, Long> {
}
