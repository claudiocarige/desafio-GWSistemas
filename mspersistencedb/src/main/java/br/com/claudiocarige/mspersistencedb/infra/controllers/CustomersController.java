package br.com.claudiocarige.mspersistencedb.infra.controllers;


import br.com.claudiocarige.mspersistencedb.core.dtos.CompanyCustomerDTO;
import br.com.claudiocarige.mspersistencedb.core.dtos.CustomerResponseDTO;
import br.com.claudiocarige.mspersistencedb.core.usecases.CustomerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;


@Slf4j
@RestController
@RequestMapping("/api/v1/customers")
public class CustomersController {

    private final CustomerService customerService;

    @Autowired
    public CustomersController( CustomerService customerService ) { this.customerService = customerService; }


    @PostMapping("/create/pj")
    public ResponseEntity< HttpStatus > createCustomerPJ( @RequestBody CompanyCustomerDTO companyCustomerDTO) {
        log.info( "Creating company customer: {}", companyCustomerDTO );
        CustomerResponseDTO customerResponseDTO = customerService.createCompanyCustomer( companyCustomerDTO );
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                                                               .buildAndExpand( customerResponseDTO.getId() ).toUri();
        return ResponseEntity.created( uri ).build();
    }

}
