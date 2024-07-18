package br.com.claudiocarige.mspersistencedb.core.usecases.impl;

import br.com.claudiocarige.mspersistencedb.core.domain.entities.Address;
import br.com.claudiocarige.mspersistencedb.core.domain.entities.CompanyCustomer;
import br.com.claudiocarige.mspersistencedb.core.domain.entities.Customers;
import br.com.claudiocarige.mspersistencedb.core.domain.entities.IndividualCustomer;
import br.com.claudiocarige.mspersistencedb.core.dtos.CompanyCustomerDTO;
import br.com.claudiocarige.mspersistencedb.core.dtos.CustomerDTO;
import br.com.claudiocarige.mspersistencedb.core.dtos.CustomerResponseDTO;
import br.com.claudiocarige.mspersistencedb.core.dtos.IndividualCustomerDTO;
import br.com.claudiocarige.mspersistencedb.core.exceptions.DataIntegrityViolationException;
import br.com.claudiocarige.mspersistencedb.core.exceptions.NoSuchElementException;
import br.com.claudiocarige.mspersistencedb.core.usecases.CustomerService;
import br.com.claudiocarige.mspersistencedb.infra.persistence.repositories.postgresrepository.AddressRepository;
import br.com.claudiocarige.mspersistencedb.infra.persistence.repositories.postgresrepository.CompanyCustomerRepository;
import br.com.claudiocarige.mspersistencedb.infra.persistence.repositories.postgresrepository.CustomerRepository;
import br.com.claudiocarige.mspersistencedb.infra.persistence.repositories.postgresrepository.IndividualCustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class CustomerServiceImpl implements CustomerService {


    private final IndividualCustomerRepository individualCustomerRepository;

    private final CompanyCustomerRepository companyCustomerRepository;

    private final CustomerRepository customerRepository;

    private final AddressRepository addressRepository;

    private final ConvertClassToDTOService convertClassDTOService;


    public CustomerServiceImpl( IndividualCustomerRepository individualCustomerRepository,
                                CompanyCustomerRepository companyCustomerRepository, CustomerRepository customerRepository,
                                AddressRepository addressRepository, ConvertClassToDTOService convertClassDTOService ) {

        this.individualCustomerRepository = individualCustomerRepository;
        this.companyCustomerRepository = companyCustomerRepository;
        this.customerRepository = customerRepository;
        this.addressRepository = addressRepository;
        this.convertClassDTOService = convertClassDTOService;
    }

    @Override
    public CustomerResponseDTO createIndividualCustomer( IndividualCustomerDTO individualCustomerDTO ) {

        individualCustomerDTO.setId( null );
        checkFields(individualCustomerDTO);
        Address savedAddress = addressRepository.save( individualCustomerDTO.getAddress() );
        IndividualCustomer individualCustomer = convertClassDTOService
                .convertIndividualCustomerDTOToEntite( individualCustomerDTO );
        individualCustomer.setAddress( savedAddress );
        individualCustomer = individualCustomerRepository.save( individualCustomer );
        return convertClassDTOService.convertIndividualCustomerToCustomerResponseDTO( individualCustomer );

    }

    @Override
    public CustomerResponseDTO createCompanyCustomer( CompanyCustomerDTO companyCustomerDTO ) {

        companyCustomerDTO.setId( null );
        checkFields(companyCustomerDTO);
        Address savedAddress = addressRepository.save( companyCustomerDTO.getAddress() );
        CompanyCustomer companyCustomer = convertClassDTOService
                .convertCompanyCustomerDTOToEntite( companyCustomerDTO );
        companyCustomer.setAddress( savedAddress );
        companyCustomer = companyCustomerRepository.save( companyCustomer );
        return convertClassDTOService.convertCompanyCustomerToCustomerResponseDTO( companyCustomer );
    }

    @Override
    public CustomerResponseDTO updateIndividualCustomer( IndividualCustomerDTO individualCustomerDTO ) {

        return convertClassDTOService
                   .convertIndividualCustomerToCustomerResponseDTO( individualCustomerRepository.save(
                        convertClassDTOService.convertIndividualCustomerDTOToEntite( individualCustomerDTO ) ) );
    }

    @Override
    public CustomerResponseDTO updateCompanyCustomer( CompanyCustomerDTO companyCustomerDTO ) {

        return convertClassDTOService
                   .convertCompanyCustomerToCustomerResponseDTO( companyCustomerRepository.save(
                        convertClassDTOService.convertCompanyCustomerDTOToEntite( companyCustomerDTO ) ) );
    }

    @Override
    public CustomerResponseDTO findCustomerById( Long customerId ) {

        Customers customer = customerRepository.findById( customerId )
                .orElseThrow( () -> new NoSuchElementException( "Customer not found." ) );
        if( customer instanceof IndividualCustomer ) {
            return convertClassDTOService
                    .convertIndividualCustomerToCustomerResponseDTO( ( IndividualCustomer ) customer );
        }
        return convertClassDTOService.convertCompanyCustomerToCustomerResponseDTO( ( CompanyCustomer ) customer );
    }

    @Override
    public List< CustomerResponseDTO > listAllCustomers() {

        List< Customers > customersList = customerRepository.findAll();
        return customersList.stream().map( obj -> {
            if( obj instanceof IndividualCustomer ) {
                return convertClassDTOService
                        .convertIndividualCustomerToCustomerResponseDTO( ( IndividualCustomer ) obj );
            }
            return convertClassDTOService
                    .convertCompanyCustomerToCustomerResponseDTO( ( CompanyCustomer ) obj );
        } ).toList();
    }

    private void checkFields( CustomerDTO customerDTO) {

        if(customerRepository.existsByPrincipalEmail(customerDTO.getPrincipalEmail())) {
            throw new DataIntegrityViolationException("Email already registered! Please review your request");
        }
        if(customerRepository.existsByCnpj(customerDTO.getCnpj())) {
            throw new DataIntegrityViolationException("CNPJ already registered! Please review your request");
        }
        if(customerRepository.existsByPhoneNumber(customerDTO.getPhoneNumber())) {
            throw new DataIntegrityViolationException("Phone number already registered! Please review your request");
        }
        if(customerRepository.existsByCustomerName(customerDTO.getCustomerName())) {
            throw new DataIntegrityViolationException("Customer name already registered! Please review your request");
        }
        if(customerRepository.existsByCpf(customerDTO.getCpf())) {
            throw new DataIntegrityViolationException("CPF already registered! Please review your request");
        }
    }

}
