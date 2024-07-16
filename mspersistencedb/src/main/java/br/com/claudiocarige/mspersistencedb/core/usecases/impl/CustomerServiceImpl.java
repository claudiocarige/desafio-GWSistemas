package br.com.claudiocarige.mspersistencedb.core.usecases.impl;

import br.com.claudiocarige.mspersistencedb.core.domain.entities.Address;
import br.com.claudiocarige.mspersistencedb.core.domain.entities.IndividualCustomer;
import br.com.claudiocarige.mspersistencedb.core.dtos.CompanyCustomerDTO;
import br.com.claudiocarige.mspersistencedb.core.dtos.CustomerResponseDTO;
import br.com.claudiocarige.mspersistencedb.core.dtos.IndividualCustomerDTO;
import br.com.claudiocarige.mspersistencedb.core.usecases.CustomerService;
import br.com.claudiocarige.mspersistencedb.infra.persistence.repositories.postgresrepository.AddressRepository;
import br.com.claudiocarige.mspersistencedb.infra.persistence.repositories.postgresrepository.CompanyCustomerRepository;
import br.com.claudiocarige.mspersistencedb.infra.persistence.repositories.postgresrepository.IndividualCustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class CustomerServiceImpl implements CustomerService {


    private final IndividualCustomerRepository individualCustomerRepository;

    private final CompanyCustomerRepository companyCustomerRepository;

    private final AddressRepository addressRepository;

    private final ConvertClassToDTOService convertClassDTOService;


    public CustomerServiceImpl( IndividualCustomerRepository individualCustomerRepository,
                                CompanyCustomerRepository companyCustomerRepository,
                                AddressRepository addressRepository, ConvertClassToDTOService convertClassDTOService ) {

        this.individualCustomerRepository = individualCustomerRepository;
        this.companyCustomerRepository = companyCustomerRepository;
        this.addressRepository = addressRepository;
        this.convertClassDTOService = convertClassDTOService;
    }

    @Override
    public CustomerResponseDTO createIndividualCustomer( IndividualCustomerDTO individualCustomerDTO ) {

        return null;
    }

    @Override
    public CustomerResponseDTO createCompanyCustomer( CompanyCustomerDTO companyCustomerDTO ) {

        return null;
    }

    @Override
    public CustomerResponseDTO updateIndividualCustomer( IndividualCustomerDTO individualCustomer ) {

        return null;
    }

    @Override
    public CustomerResponseDTO updateCompanyCustomer( CompanyCustomerDTO companyCustomer ) {

        return null;
    }

    @Override
    public CustomerResponseDTO findCustomerById( Long customerId ) {

        return null;
    }

    @Override
    public List< CustomerResponseDTO > listAllCustomers() {

        return List.of();
    }

}
