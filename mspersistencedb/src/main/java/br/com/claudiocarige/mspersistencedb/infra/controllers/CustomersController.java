package br.com.claudiocarige.mspersistencedb.infra.controllers;


import br.com.claudiocarige.mspersistencedb.core.domain.entities.Customers;
import br.com.claudiocarige.mspersistencedb.core.dtos.CompanyCustomerDTO;
import br.com.claudiocarige.mspersistencedb.core.dtos.CustomerResponseDTO;
import br.com.claudiocarige.mspersistencedb.core.usecases.CustomerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;


@Slf4j
@RestController
@RequestMapping("/api/v1/customers")
@Tag(name = "Customers", description = "Endpoints for managing customer data including creation of company customers.")
public class CustomersController {

    private final CustomerService customerService;

    @Autowired
    public CustomersController( CustomerService customerService ) { this.customerService = customerService; }


    @PostMapping("/create/pj")
    @Operation(summary = "Add a new CompanyCustomer", description = "Creating Company customer",
            tags = {"Customers"},
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200",
                                 content = @Content(mediaType = "application/json",
                                 schema = @Schema(implementation = Customers.class))
                    ),
                    @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content),
            })
    public ResponseEntity< HttpStatus > createCustomerPJ( @RequestBody CompanyCustomerDTO companyCustomerDTO) {
        log.info( "Creating Company customer: {}", companyCustomerDTO );
        CustomerResponseDTO customerResponseDTO = customerService.createCompanyCustomer( companyCustomerDTO );
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                                                               .buildAndExpand( customerResponseDTO.getId() ).toUri();
        return ResponseEntity.created( uri ).build();
    }

    @CrossOrigin
    @GetMapping("/list")
    @Operation(summary = "List All Customers", description = "List All Customers",
            tags = {"Customers"},
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = Customers.class))
                    ),
                    @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content),
            })
    public ResponseEntity< List< CustomerResponseDTO> > listAllCustomers() {
        List< CustomerResponseDTO > customers = customerService.listAllCustomers();
        return ResponseEntity.ok( customers );
    }

}
