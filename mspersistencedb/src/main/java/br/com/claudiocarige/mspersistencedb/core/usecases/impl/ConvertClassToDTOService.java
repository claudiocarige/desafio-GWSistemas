package br.com.claudiocarige.mspersistencedb.core.usecases.impl;

import br.com.claudiocarige.mspersistencedb.core.domain.entities.CompanyCustomer;
import br.com.claudiocarige.mspersistencedb.core.domain.entities.IndividualCustomer;
import br.com.claudiocarige.mspersistencedb.core.dtos.CompanyCustomerDTO;
import br.com.claudiocarige.mspersistencedb.core.dtos.CustomerResponseDTO;
import br.com.claudiocarige.mspersistencedb.core.dtos.IndividualCustomerDTO;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;


@Service
public class ConvertClassToDTOService {

    private final ModelMapper modelMapper;

    public ConvertClassToDTOService( ModelMapper modelMapper ) { this.modelMapper = modelMapper; }

    public IndividualCustomer convertIndividualCustomerDTOToEntite( IndividualCustomerDTO individualCustomerDTO ) {
        return modelMapper.map( individualCustomerDTO, IndividualCustomer.class );
    }
    
    public IndividualCustomerDTO convertIndividualCustomerToDTO( IndividualCustomer individualCustomer ) {
        return modelMapper.map( individualCustomer, IndividualCustomerDTO.class );
    }

    public CompanyCustomer convertCompanyCustomerDTOToEntite( CompanyCustomerDTO companyCustomerDTO ) {
        return modelMapper.map( companyCustomerDTO, CompanyCustomer.class );
    }
    
    public CompanyCustomerDTO convertCompanyCustomerToDTO( CompanyCustomer companyCustomer ) {
        return modelMapper.map( companyCustomer, CompanyCustomerDTO.class );
    }

    public CustomerResponseDTO convertIndividualCustomerToCustomerResponseDTO( IndividualCustomer individualCustomer ) {
        return modelMapper.map( individualCustomer, CustomerResponseDTO.class );
    }

    public CustomerResponseDTO convertCompanyCustomerToCustomerResponseDTO( CompanyCustomer companyCustomer ) {
        return modelMapper.map( companyCustomer, CustomerResponseDTO.class );
    }

}
