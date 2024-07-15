package br.com.claudiocarige.mspersistencedb.infra.persistence.repositories.postgresrepository;

import br.com.claudiocarige.mspersistencedb.core.domain.entities.CompanyCustomer;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CompanyCustomerRepository extends JpaRepository< CompanyCustomer, Long > {
}
