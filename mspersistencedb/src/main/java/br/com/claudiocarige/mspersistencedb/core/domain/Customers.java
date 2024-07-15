package br.com.claudiocarige.mspersistencedb.core.domain;

import jakarta.persistence.*;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


@Entity
public abstract class Customers implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private Long id;

    private String customerName;

    @OneToOne
    @JoinColumn( name = "address_id" )
    private Address address;

    private String phoneNumber;

    private String whatsapp;

    private String principalEmail;

    private String responsibleEmployee;

    @ElementCollection( fetch = FetchType.EAGER )
    private List< String > emailList = new ArrayList<>();

}
