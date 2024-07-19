package br.com.claudiocarige.mspersistencedb.core.usecases;

import br.com.claudiocarige.mspersistencedb.core.dtos.CompanyCustomerDTO;
import br.com.claudiocarige.mspersistencedb.core.dtos.CustomerResponseDTO;
import br.com.claudiocarige.mspersistencedb.core.dtos.IndividualCustomerDTO;

import java.util.List;


public interface CustomerService {

    CustomerResponseDTO createIndividualCustomer( IndividualCustomerDTO individualCustomerDTO );

    CustomerResponseDTO createCompanyCustomer( CompanyCustomerDTO companyCustomerDTO);

    CustomerResponseDTO updateIndividualCustomer( IndividualCustomerDTO individualCustomerDTO);

    CustomerResponseDTO updateCompanyCustomer( CompanyCustomerDTO companyCustomerDTO);

    CustomerResponseDTO findCustomerById( Long customerId);

    List<CustomerResponseDTO> listAllCustomers();

    CustomerResponseDTO findByPrimaryEmail( String customerName );

}
