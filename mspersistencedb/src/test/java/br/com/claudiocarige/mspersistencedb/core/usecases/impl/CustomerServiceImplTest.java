package br.com.claudiocarige.mspersistencedb.core.usecases.impl;

import br.com.claudiocarige.mspersistencedb.core.domain.entities.Address;
import br.com.claudiocarige.mspersistencedb.core.domain.entities.IndividualCustomer;
import br.com.claudiocarige.mspersistencedb.core.dtos.CustomerResponseDTO;
import br.com.claudiocarige.mspersistencedb.core.dtos.IndividualCustomerDTO;
import br.com.claudiocarige.mspersistencedb.infra.persistence.repositories.postgresrepository.AddressRepository;
import br.com.claudiocarige.mspersistencedb.infra.persistence.repositories.postgresrepository.IndividualCustomerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static br.com.claudiocarige.mspersistencedb.core.domain.builders.IndividualCustomerBuilder.oneIndividualCustomer;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@ExtendWith( MockitoExtension.class )
class CustomerServiceImplTest {


    private Address address;

    private IndividualCustomer individualCustomer;

    private IndividualCustomerDTO individualCustomerDTO;

    private CustomerResponseDTO customerResponseDTO;


    @Mock
    private IndividualCustomerRepository individualCustomerRepository;

    @Mock
    private AddressRepository addressRepository;

    @Mock
    private ConvertClassToDTOService convertClassDTOService;

    @InjectMocks
    private CustomerServiceImpl customerService;

    @BeforeEach
    void setup() {

        startEntities();
    }

    @Test
    @DisplayName( "Should create a new IndividualCustomer" )
    void shouldCreateANewIndividualCustomer() {

        when( addressRepository.save( any( Address.class ) ) ).thenReturn( address );
        when( individualCustomerRepository.save( individualCustomer ) ).thenReturn( individualCustomer );

        when( convertClassDTOService.convertIndividualCustomerDTOToEntite( individualCustomerDTO ) )
                                                                                    .thenReturn( individualCustomer );
        when( convertClassDTOService.convertIndividualCustomerToCustomerResponseDTO( individualCustomer ) )
                                                                                   .thenReturn( customerResponseDTO );

        CustomerResponseDTO result = customerService.createIndividualCustomer( individualCustomerDTO );

        verify( addressRepository ).save( any( Address.class ) );
        verify( individualCustomerRepository ).save( individualCustomer );
        verify( convertClassDTOService ).convertIndividualCustomerDTOToEntite( individualCustomerDTO );
        verify( convertClassDTOService ).convertIndividualCustomerToCustomerResponseDTO( individualCustomer );

        assertNotNull( result );
        assertEquals( individualCustomer.getId(), result.getId() );
        assertEquals( individualCustomer.getCpf(), result.getCpfOrCnpj() );
        assertEquals( individualCustomer.getPrincipalEmail(), result.getPrincipalEmail() );
        assertEquals( individualCustomer.getPhoneNumber(), result.getPhoneNumber() );
        assertEquals( individualCustomer.getWhatsapp(), result.getWhatsapp() );
        assertEquals( individualCustomer.getResponsibleEmployee(), result.getResponsibleEmployee() );
        assertEquals( individualCustomer.getCustomerName(), result.getCustomerName() );
    }

    private void startEntities() {

        individualCustomer = oneIndividualCustomer().now();
        individualCustomerDTO = oneIndividualCustomer().nowDTO();
        customerResponseDTO = new CustomerResponseDTO();
        customerResponseDTO.setId( individualCustomer.getId() );
        customerResponseDTO.setPrincipalEmail( individualCustomer.getPrincipalEmail() );
        customerResponseDTO.setPhoneNumber( individualCustomer.getPhoneNumber() );
        customerResponseDTO.setWhatsapp( individualCustomer.getWhatsapp() );
        customerResponseDTO.setResponsibleEmployee( individualCustomer.getResponsibleEmployee() );
        customerResponseDTO.setCustomerName( individualCustomer.getCustomerName() );
        customerResponseDTO.setCpfOrCnpj( individualCustomer.getCpf() );

    }

}