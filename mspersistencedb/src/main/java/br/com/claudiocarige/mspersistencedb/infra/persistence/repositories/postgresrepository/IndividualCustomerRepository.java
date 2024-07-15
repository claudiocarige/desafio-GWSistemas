package br.com.claudiocarige.mspersistencedb.infra.persistence.repositories.postgresrepository;

import br.com.claudiocarige.mspersistencedb.core.domain.entities.IndividualCustomer;
import org.springframework.data.jpa.repository.JpaRepository;


public interface IndividualCustomerRepository extends JpaRepository< IndividualCustomer, Long > {

}
