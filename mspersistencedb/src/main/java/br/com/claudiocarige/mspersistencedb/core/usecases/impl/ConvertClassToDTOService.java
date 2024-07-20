package br.com.claudiocarige.mspersistencedb.core.usecases.impl;

import br.com.claudiocarige.mspersistencedb.core.domain.entities.CompanyCustomer;
import br.com.claudiocarige.mspersistencedb.core.domain.entities.Delivery;
import br.com.claudiocarige.mspersistencedb.core.domain.entities.IndividualCustomer;
import br.com.claudiocarige.mspersistencedb.core.dtos.CompanyCustomerDTO;
import br.com.claudiocarige.mspersistencedb.core.dtos.CustomerResponseDTO;
import br.com.claudiocarige.mspersistencedb.core.dtos.DeliveryDTO;
import br.com.claudiocarige.mspersistencedb.core.dtos.IndividualCustomerDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ConvertClassToDTOService {

    private final ModelMapper modelMapper;

    @Autowired
    public ConvertClassToDTOService( ModelMapper modelMapper ) { this.modelMapper = modelMapper; }

    public IndividualCustomer convertIndividualCustomerDTOToEntite( IndividualCustomerDTO individualCustomerDTO ) {

        return modelMapper.map( individualCustomerDTO, IndividualCustomer.class );
    }

    public CompanyCustomer convertCompanyCustomerDTOToEntite( CompanyCustomerDTO companyCustomerDTO ) {

        return modelMapper.map( companyCustomerDTO, CompanyCustomer.class );
    }

    public CustomerResponseDTO convertIndividualCustomerToCustomerResponseDTO( IndividualCustomer individualCustomer ) {

        return modelMapper.map( individualCustomer, CustomerResponseDTO.class );
    }

    public CustomerResponseDTO convertCompanyCustomerToCustomerResponseDTO( CompanyCustomer companyCustomer ) {

        return modelMapper.map( companyCustomer, CustomerResponseDTO.class );
    }

    public DeliveryDTO convertDeliveryToDTO( Delivery delivery ) {

        return modelMapper.map( delivery, DeliveryDTO.class );
    }

}
