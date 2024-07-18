package br.com.claudiocarige.mspersistencedb.core.dtos;


import br.com.claudiocarige.mspersistencedb.core.domain.entities.Address;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.br.CPF;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class IndividualCustomerDTO implements Serializable, CustomerDTO {

    private Long id;

    @NotNull( message = "Name is required" )
    private String customerName;

    @CPF( message = "CPF format is invalid" )
    @NotNull( message = "CPF is required" )
    @Size( min = 14, max = 14, message = "CPF must have 14 characters" )
    private String cpf;

    @NotNull( message = "Address Registration is required" )
    private Address address;

    @NotNull( message = "Phone number is required" )
    private String phoneNumber;

    @NotNull( message = "Whatsapp is required" )
    private String whatsapp;

    @NotNull( message = "Email is required" )
    @Pattern( regexp = "^[a-zA-Z0-9._+-]+@[a-z]+\\.[a-z]{2,}(?:\\.[a-z]{2,3})?$", message = "Email format is invalid" )
    private String principalEmail;

    private String responsibleEmployee;

    private List< String > emailList = new ArrayList<>();

    @Override
    public boolean equals( Object o ) {

        if( this == o ) return true;
        if( o == null || getClass() != o.getClass() ) return false;
        IndividualCustomerDTO that = ( IndividualCustomerDTO ) o;
        return Objects.equals( id, that.id ) && Objects.equals( cpf, that.cpf ) && Objects.equals( principalEmail, that.principalEmail );
    }

    @Override
    public int hashCode() {

        return Objects.hash( id, cpf, principalEmail );
    }

    @Override
    public String getCnpj() {

        return "";
    }

}
