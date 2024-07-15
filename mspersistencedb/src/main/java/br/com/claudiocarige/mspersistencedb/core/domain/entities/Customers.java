package br.com.claudiocarige.mspersistencedb.core.domain.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public abstract class Customers implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private Long id;

    @Column( unique = true )
    private String customerName;

    @OneToOne
    @JoinColumn( name = "address_id" )
    private Address address;

    @Column( unique = true )
    private String phoneNumber;

    private String whatsapp;

    @Column( unique = true )
    private String principalEmail;

    private String responsibleEmployee;

    @ElementCollection( fetch = FetchType.EAGER )
    private List< String > emailList = new ArrayList<>();


    public void addEmailInList( String email ) {

        this.emailList.add( email );
    }

    public void removeEmailInList( String email ) {

        this.emailList.remove( email );
    }

    @Override
    public boolean equals( Object o ) {

        if( this == o ) return true;
        if( o == null || getClass() != o.getClass() ) return false;
        Customers customers = ( Customers ) o;
        return Objects.equals( id, customers.id ) && Objects.equals( customerName, customers.customerName ) && Objects.equals( principalEmail, customers.principalEmail );
    }

    @Override
    public int hashCode() {

        return Objects.hash( id, customerName, principalEmail );
    }

}
