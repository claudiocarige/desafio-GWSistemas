package br.com.claudiocarige.mspersistencedb.infra.persistence.repositories.postgresrepository;

import br.com.claudiocarige.mspersistencedb.core.domain.entities.Customers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface CustomerRepository extends JpaRepository< Customers, Long > {

    boolean existsByPrincipalEmail( String principalEmail );

    boolean existsByPhoneNumber( String phoneNumber );

    @Query( "SELECT CASE WHEN COUNT(c) > 0 THEN TRUE ELSE FALSE END FROM CompanyCustomer c WHERE c.cnpj = :cnpj" )
    boolean existsByCnpj( @Param( "cnpj" ) String cnpj );

    boolean existsByCustomerName( String customerName );

    @Query( "SELECT CASE WHEN COUNT(c) > 0 THEN TRUE ELSE FALSE END FROM IndividualCustomer c WHERE c.cpf = :cpf" )
    boolean existsByCpf( @Param( "cpf" ) String cpf );

}
