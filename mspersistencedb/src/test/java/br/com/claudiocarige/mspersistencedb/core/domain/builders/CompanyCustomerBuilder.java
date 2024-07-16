package br.com.claudiocarige.mspersistencedb.core.domain.builders;

import java.util.List;

import br.com.claudiocarige.mspersistencedb.core.domain.entities.Address;

import java.util.Arrays;

import br.com.claudiocarige.mspersistencedb.core.domain.entities.CompanyCustomer;
import br.com.claudiocarige.mspersistencedb.core.dtos.CompanyCustomerDTO;

import static br.com.claudiocarige.mspersistencedb.core.domain.builders.AddressBuilder.oneAddress;


public class CompanyCustomerBuilder {

    private String cnpj;

    private String corporateName;

    private Long id;

    private String customerName;

    private Address address;

    private String phoneNumber;

    private String whatsapp;

    private String principalEmail;

    private String responsibleEmployee;

    private List< String > emailList;

    private CompanyCustomerBuilder() { }

    public static CompanyCustomerBuilder oneCompanyCustomer() {

        CompanyCustomerBuilder builder = new CompanyCustomerBuilder();
        initializeDefaultData( builder );
        return builder;
    }

    private static void initializeDefaultData( CompanyCustomerBuilder builder ) {

        builder.cnpj = "34.852.547/0001-37";
        builder.corporateName = "Star Future";
        builder.id = 1L;
        builder.customerName = "Claudio LTDA";
        builder.address = oneAddress().now();
        builder.phoneNumber = "71991125697";
        builder.whatsapp = "71999900000";
        builder.principalEmail = "starfuture@mail.com";
        builder.responsibleEmployee = "Claudio Pimentel";
        builder.emailList = Arrays.asList( "ccp@gmail.com", "claudio@gmail.com" );
    }

    public CompanyCustomerBuilder withCnpj( String cnpj ) {

        this.cnpj = cnpj;
        return this;
    }

    public CompanyCustomerBuilder withCorporateName( String corporateName ) {

        this.corporateName = corporateName;
        return this;
    }

    public CompanyCustomerBuilder withId( Long id ) {

        this.id = id;
        return this;
    }

    public CompanyCustomerBuilder withCustomerName( String customerName ) {

        this.customerName = customerName;
        return this;
    }

    public CompanyCustomerBuilder withAddress( Address address ) {

        this.address = address;
        return this;
    }

    public CompanyCustomerBuilder withPhoneNumber( String phoneNumber ) {

        this.phoneNumber = phoneNumber;
        return this;
    }

    public CompanyCustomerBuilder withWhatsapp( String whatsapp ) {

        this.whatsapp = whatsapp;
        return this;
    }

    public CompanyCustomerBuilder withPrincipalEmail( String principalEmail ) {

        this.principalEmail = principalEmail;
        return this;
    }

    public CompanyCustomerBuilder withResponsibleEmployee( String responsibleEmployee ) {

        this.responsibleEmployee = responsibleEmployee;
        return this;
    }

    public CompanyCustomerBuilder withListEmailList( String... emailList ) {

        this.emailList = Arrays.asList( emailList );
        return this;
    }

    public CompanyCustomer now() {

        return new CompanyCustomer( id, cnpj, corporateName, customerName,
                                    address, phoneNumber, whatsapp,
                                    principalEmail, responsibleEmployee, emailList );
    }

    public CompanyCustomerDTO nowDTO() {

        return new CompanyCustomerDTO( id, cnpj, corporateName, customerName,
                                       address, phoneNumber, whatsapp,
                                       principalEmail, responsibleEmployee, emailList );
    }

}
