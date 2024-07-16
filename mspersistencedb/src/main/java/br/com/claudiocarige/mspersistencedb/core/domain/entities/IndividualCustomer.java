package br.com.claudiocarige.mspersistencedb.core.domain.entities;

import br.com.claudiocarige.mspersistencedb.core.exceptions.IllegalArgumentException;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.*;

import java.util.Objects;


@Getter
@Setter
@NoArgsConstructor
@ToString
@Entity
public class IndividualCustomer extends Customers {

    @Column( unique = true, length = 14 )
    private String cpf;

    public IndividualCustomer( String cpf ) {

        super();
        if( cpf == null || cpf.length() != 14 ) {
            throw new IllegalArgumentException( "Invalid CPF" );
        }
        this.cpf = cpf;
    }

    @Override
    public boolean equals( Object o ) {

        if( this == o ) return true;
        if( o == null || getClass() != o.getClass() ) return false;
        if( ! super.equals( o ) ) return false;
        IndividualCustomer that = ( IndividualCustomer ) o;
        return Objects.equals( cpf, that.cpf );
    }

    @Override
    public int hashCode() {

        return Objects.hash( super.hashCode(), cpf );
    }

}
