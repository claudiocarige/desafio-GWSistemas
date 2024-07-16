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
public class CompanyCustomer extends Customers {

    @Column( unique = true, length = 18 )
    private String cnpj;

    private String corporateName;

    public CompanyCustomer( String cnpj, String corporateName ) {

        super();
        if( cnpj == null || cnpj.length() != 18 ) {
            throw new IllegalArgumentException( "Invalid CNPJ" );
        }
        this.cnpj = cnpj;
        this.corporateName = corporateName;
    }

    @Override
    public boolean equals( Object o ) {

        if( this == o ) return true;
        if( o == null || getClass() != o.getClass() ) return false;
        if( ! super.equals( o ) ) return false;
        CompanyCustomer that = ( CompanyCustomer ) o;
        return Objects.equals( cnpj, that.cnpj );
    }

    @Override
    public int hashCode() {

        return Objects.hash( super.hashCode(), cnpj );
    }

}
