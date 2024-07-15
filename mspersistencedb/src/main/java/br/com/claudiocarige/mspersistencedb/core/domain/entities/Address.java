package br.com.claudiocarige.mspersistencedb.core.domain.entities;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;


@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Address implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    @Column( nullable = false )
    private Long id;

    private String street;

    private String number;

    private String city;

    private String state;

    private String country;

    private String postalCode;

    private String latitudeCoordinate;

    private String longitudeCoordinate;

    @Override
    public boolean equals( Object o ) {

        if( this == o ) return true;
        if( o == null || getClass() != o.getClass() ) return false;
        Address address = ( Address ) o;
        return Objects.equals( id, address.id ) && Objects.equals( street, address.street ) && Objects.equals( number, address.number ) && Objects.equals( city, address.city );
    }

    @Override
    public int hashCode() {

        return Objects.hash( id, street, number, city );
    }

}
