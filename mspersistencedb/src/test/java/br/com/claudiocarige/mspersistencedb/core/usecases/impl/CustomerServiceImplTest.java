package br.com.claudiocarige.mspersistencedb.core.usecases.impl;

import br.com.claudiocarige.mspersistencedb.core.domain.entities.Address;
import br.com.claudiocarige.mspersistencedb.core.domain.entities.CompanyCustomer;
import br.com.claudiocarige.mspersistencedb.core.domain.entities.Customers;
import br.com.claudiocarige.mspersistencedb.core.domain.entities.IndividualCustomer;
import br.com.claudiocarige.mspersistencedb.core.dtos.CompanyCustomerDTO;
import br.com.claudiocarige.mspersistencedb.core.dtos.CustomerResponseDTO;
import br.com.claudiocarige.mspersistencedb.core.dtos.IndividualCustomerDTO;
import br.com.claudiocarige.mspersistencedb.core.exceptions.NoSuchElementException;
import br.com.claudiocarige.mspersistencedb.infra.persistence.repositories.postgresrepository.AddressRepository;
import br.com.claudiocarige.mspersistencedb.infra.persistence.repositories.postgresrepository.CompanyCustomerRepository;
import br.com.claudiocarige.mspersistencedb.infra.persistence.repositories.postgresrepository.CustomerRepository;
import br.com.claudiocarige.mspersistencedb.infra.persistence.repositories.postgresrepository.IndividualCustomerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static br.com.claudiocarige.mspersistencedb.core.domain.builders.CompanyCustomerBuilder.oneCompanyCustomer;
import static br.com.claudiocarige.mspersistencedb.core.domain.builders.IndividualCustomerBuilder.oneIndividualCustomer;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


@ExtendWith( MockitoExtension.class )
class CustomerServiceImplTest {


    private Address address;

    private IndividualCustomer individualCustomer;

    private IndividualCustomerDTO individualCustomerDTO;

    private CompanyCustomer companyCustomer;

    private CompanyCustomerDTO companyCustomerDTO;

    private CustomerResponseDTO customerResponseIndividualDTO;

    private CustomerResponseDTO customerResponseCompanyDTO;

    @Mock
    private IndividualCustomerRepository individualCustomerRepository;

    @Mock
    private CompanyCustomerRepository companyCustomerRepository;

    @Mock
    private AddressRepository addressRepository;

    @Mock
    private CustomerRepository customerRepository;

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
                .thenReturn( customerResponseIndividualDTO );

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

    @Test
    @DisplayName( "Should create a new CompanyCustomer." )
    void shouldCreateANewCompanyCustomer() {

        when( addressRepository.save( any( Address.class ) ) ).thenReturn( address );
        when( companyCustomerRepository.save( companyCustomer ) ).thenReturn( companyCustomer );

        when( convertClassDTOService.convertCompanyCustomerDTOToEntite( companyCustomerDTO ) )
                .thenReturn( companyCustomer );
        when( convertClassDTOService.convertCompanyCustomerToCustomerResponseDTO( companyCustomer ) )
                .thenReturn( customerResponseCompanyDTO );

        CustomerResponseDTO result = customerService.createCompanyCustomer( companyCustomerDTO );

        verify( addressRepository ).save( any( Address.class ) );
        verify( companyCustomerRepository ).save( companyCustomer );
        verify( convertClassDTOService ).convertCompanyCustomerDTOToEntite( companyCustomerDTO );
        verify( convertClassDTOService ).convertCompanyCustomerToCustomerResponseDTO( companyCustomer );

        assertNotNull( result );
        assertEquals( companyCustomer.getId(), result.getId() );
        assertEquals( companyCustomer.getCnpj(), result.getCpfOrCnpj() );
        assertEquals( companyCustomer.getPrincipalEmail(), result.getPrincipalEmail() );
        assertEquals( companyCustomer.getPhoneNumber(), result.getPhoneNumber() );
        assertEquals( companyCustomer.getWhatsapp(), result.getWhatsapp() );
        assertEquals( companyCustomer.getResponsibleEmployee(), result.getResponsibleEmployee() );
        assertEquals( companyCustomer.getCustomerName(), result.getCustomerName() );
    }

    @Test
    @DisplayName( "Should return a IndviIndividualCustomer By Id" )
    public void shouldReturnAIndividualCustomerById() {

        Customers customer = oneIndividualCustomer().now();
        when( customerRepository.findById( 1L ) ).thenReturn( Optional.of( customer ) );
        when( convertClassDTOService.convertIndividualCustomerToCustomerResponseDTO( individualCustomer ) )
                .thenReturn( customerResponseIndividualDTO );

        CustomerResponseDTO result = customerService.findCustomerById( 1L );

        assertNotNull( result );
        assertEquals( customerResponseIndividualDTO, result );
        verify( customerRepository, times( 1 ) ).findById( 1L );
        verify( convertClassDTOService, times( 1 ) ).convertIndividualCustomerToCustomerResponseDTO( individualCustomer );
        verify( convertClassDTOService, never() ).convertCompanyCustomerToCustomerResponseDTO( any() );

    }

    @Test
    @DisplayName( "Should return a CompanyCustomer By Id" )
    public void shouldReturnACompanyCustomerById() {

        Customers customer = oneCompanyCustomer().now();
        when( customerRepository.findById( 1L ) ).thenReturn( Optional.of( companyCustomer ) );
        when( convertClassDTOService.convertCompanyCustomerToCustomerResponseDTO( companyCustomer ) )
                .thenReturn( customerResponseCompanyDTO );

        CustomerResponseDTO result = customerService.findCustomerById( 1L );

        assertNotNull( result );
        assertEquals( customerResponseCompanyDTO, result );
        verify( customerRepository, times( 1 ) ).findById( 1L );
        verify( convertClassDTOService, times( 1 ) ).convertCompanyCustomerToCustomerResponseDTO( companyCustomer );
        verify( convertClassDTOService, never() ).convertIndividualCustomerToCustomerResponseDTO( any() );
    }

    @Test
    @DisplayName( "Should return a NoSuchElementException" )
    public void shouldReturnANoSuchElementException() {

        when( customerRepository.findById( 1L ) ).thenReturn( Optional.empty() );

        NoSuchElementException exception = assertThrows( NoSuchElementException.class, () ->
                customerService.findCustomerById( 1L ) );

        assertEquals( "Customer not found.", exception.getMessage() );
        verify( customerRepository, times( 1 ) ).findById( 1L );
        verify( convertClassDTOService, never() ).convertIndividualCustomerToCustomerResponseDTO( any() );
        verify( convertClassDTOService, never() ).convertCompanyCustomerToCustomerResponseDTO( any() );
    }


    @Test
    @DisplayName( "Should return a list of CustomerResponseDTO" )
    void shouldReturnAListOfCustomerResponseDTO() {

        when( customerRepository.findAll() ).thenReturn( Arrays.asList( individualCustomer, companyCustomer ) );
        when( convertClassDTOService.convertIndividualCustomerToCustomerResponseDTO( individualCustomer ) )
                .thenReturn( customerResponseIndividualDTO );
        when( convertClassDTOService.convertCompanyCustomerToCustomerResponseDTO( companyCustomer ) )
                .thenReturn( customerResponseCompanyDTO );

        List< CustomerResponseDTO > result = customerService.listAllCustomers();

        assertEquals( 2, result.size() );
        assertEquals( CustomerResponseDTO.class, result.get( 0 ).getClass() );
        assertEquals( CustomerResponseDTO.class, result.get( 1 ).getClass() );
        assertEquals( companyCustomer.getCnpj(), result.get( 1 ).getCpfOrCnpj() );
        verify( customerRepository, times( 1 ) ).findAll();
        verify( convertClassDTOService, times( 1 ) ).convertIndividualCustomerToCustomerResponseDTO( individualCustomer );
        verify( convertClassDTOService, times( 1 ) ).convertCompanyCustomerToCustomerResponseDTO( companyCustomer );
    }

    @Test
    @DisplayName( "Should update a IndividualCustomer" )
    void shouldUpdateAIndividualCustomer(){

        when( individualCustomerRepository.save( individualCustomer ) ).thenReturn( individualCustomer );

        when( convertClassDTOService.convertIndividualCustomerDTOToEntite( individualCustomerDTO ) )
                .thenReturn( individualCustomer );
        when( convertClassDTOService.convertIndividualCustomerToCustomerResponseDTO( individualCustomer ) )
                .thenReturn( customerResponseIndividualDTO );

        CustomerResponseDTO result = customerService.updateIndividualCustomer( individualCustomerDTO );

        assertNotNull( result );
        assertEquals( individualCustomer.getCpf(), result.getCpfOrCnpj() );
        assertEquals( individualCustomer.getPrincipalEmail(), result.getPrincipalEmail() );

    }

    @Test
    @DisplayName( "Should update a CompanyCustomer" )
    void shouldUpdateACompanyCustomer(){

        when( companyCustomerRepository.save( companyCustomer ) ).thenReturn( companyCustomer );

        when( convertClassDTOService.convertCompanyCustomerDTOToEntite( companyCustomerDTO ) )
                .thenReturn( companyCustomer );
        when( convertClassDTOService.convertCompanyCustomerToCustomerResponseDTO( companyCustomer ) )
                .thenReturn( customerResponseIndividualDTO );

        CustomerResponseDTO result = customerService.updateCompanyCustomer( companyCustomerDTO );

        assertNotNull( result );
        assertEquals( companyCustomer.getCnpj(), result.getCpfOrCnpj() );
        assertEquals( companyCustomer.getPrincipalEmail(), result.getPrincipalEmail() );

    }

    private void startEntities() {

        individualCustomer = oneIndividualCustomer().now();
        individualCustomerDTO = oneIndividualCustomer().nowDTO();
        companyCustomer = oneCompanyCustomer().now();
        companyCustomerDTO = oneCompanyCustomer().nowDTO();
        customerResponseIndividualDTO = new CustomerResponseDTO();
        customerResponseIndividualDTO.setId( individualCustomer.getId() );
        customerResponseIndividualDTO.setPrincipalEmail( individualCustomer.getPrincipalEmail() );
        customerResponseIndividualDTO.setPhoneNumber( individualCustomer.getPhoneNumber() );
        customerResponseIndividualDTO.setWhatsapp( individualCustomer.getWhatsapp() );
        customerResponseIndividualDTO.setResponsibleEmployee( individualCustomer.getResponsibleEmployee() );
        customerResponseIndividualDTO.setCustomerName( individualCustomer.getCustomerName() );
        customerResponseIndividualDTO.setCpfOrCnpj( individualCustomer.getCpf() );

        customerResponseCompanyDTO = new CustomerResponseDTO();
        customerResponseCompanyDTO.setId( companyCustomer.getId() );
        customerResponseCompanyDTO.setPrincipalEmail( companyCustomer.getPrincipalEmail() );
        customerResponseCompanyDTO.setPhoneNumber( companyCustomer.getPhoneNumber() );
        customerResponseCompanyDTO.setWhatsapp( companyCustomer.getWhatsapp() );
        customerResponseCompanyDTO.setResponsibleEmployee( companyCustomer.getResponsibleEmployee() );
        customerResponseCompanyDTO.setCustomerName( companyCustomer.getCustomerName() );
        customerResponseCompanyDTO.setCpfOrCnpj( companyCustomer.getCnpj() );

    }

}