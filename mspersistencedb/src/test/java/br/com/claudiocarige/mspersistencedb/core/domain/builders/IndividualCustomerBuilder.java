package br.com.claudiocarige.mspersistencedb.core.domain.builders;

import java.util.List;

import br.com.claudiocarige.mspersistencedb.core.domain.entities.Address;

import java.util.Arrays;

import br.com.claudiocarige.mspersistencedb.core.domain.entities.IndividualCustomer;
import br.com.claudiocarige.mspersistencedb.core.dtos.IndividualCustomerDTO;

import static br.com.claudiocarige.mspersistencedb.core.domain.builders.AddressBuilder.oneAddress;


public class IndividualCustomerBuilder {

    private String cpf;

    private Long id;

    private String customerName;

    private Address address;

    private String phoneNumber;

    private String whatsapp;

    private String principalEmail;

    private String responsibleEmployee;

    private List< String > emailList;

    private IndividualCustomerBuilder() { }

    public static IndividualCustomerBuilder oneIndividualCustomer() {

        IndividualCustomerBuilder builder = new IndividualCustomerBuilder();
        initializeDefaultData( builder );
        return builder;
    }

    private static void initializeDefaultData( IndividualCustomerBuilder builder ) {

        builder.cpf = "123.456.789-00";
        builder.id = 1L;
        builder.customerName = "Claudio carige";
        builder.address = oneAddress().now();
        builder.phoneNumber = "71991125697";
        builder.whatsapp = "71991125697";
        builder.principalEmail = "ccarige@gmail.com";
        builder.responsibleEmployee = "Claudio Carige";
        builder.emailList = Arrays.asList( "carige@gmail.com", "pimentel@gmail.com" );
    }

    public IndividualCustomerBuilder withCpf( String cpf ) {

        this.cpf = cpf;
        return this;
    }

    public IndividualCustomerBuilder withId( Long id ) {

        this.id = id;
        return this;
    }

    public IndividualCustomerBuilder withCustomerName( String customerName ) {

        this.customerName = customerName;
        return this;
    }

    public IndividualCustomerBuilder withAddress( Address address ) {

        this.address = address;
        return this;
    }

    public IndividualCustomerBuilder withPhoneNumber( String phoneNumber ) {

        this.phoneNumber = phoneNumber;
        return this;
    }

    public IndividualCustomerBuilder withWhatsapp( String whatsapp ) {

        this.whatsapp = whatsapp;
        return this;
    }

    public IndividualCustomerBuilder withPrincipalEmail( String principalEmail ) {

        this.principalEmail = principalEmail;
        return this;
    }

    public IndividualCustomerBuilder withResponsibleEmployee( String responsibleEmployee ) {

        this.responsibleEmployee = responsibleEmployee;
        return this;
    }

    public IndividualCustomerBuilder withListEmailList( String... emailList ) {

        this.emailList = Arrays.asList( emailList );
        return this;
    }

    public IndividualCustomer now() {

        return new IndividualCustomer( cpf, id, customerName, address,
                                       phoneNumber, whatsapp, principalEmail,
                                       responsibleEmployee, emailList );
    }

    public IndividualCustomerDTO nowDTO() {

        return new IndividualCustomerDTO( id, cpf, customerName, address,
                                          phoneNumber, whatsapp, principalEmail,
                                          responsibleEmployee, emailList );
    }

}
