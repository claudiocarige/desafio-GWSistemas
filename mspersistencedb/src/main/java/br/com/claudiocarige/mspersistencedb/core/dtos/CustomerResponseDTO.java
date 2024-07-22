package br.com.claudiocarige.mspersistencedb.core.dtos;

import br.com.claudiocarige.mspersistencedb.core.domain.entities.Address;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CustomerResponseDTO {

    private Long id;

    private String customerName;

    private String cpfOrCnpj;

    private Address address;

    private String principalEmail;

    private String phoneNumber;

    private String whatsapp;

    private String responsibleEmployee;

    private List< String > emailList = new ArrayList<>();

}
