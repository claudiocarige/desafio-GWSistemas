package br.com.claudiocarige.mspersistencedb.core.domain.entities;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.Objects;


@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Product {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private Long id;

    private String name;

    private double weight;

    private double volume;

    //TODO inserir atributo posição do produto para busca no armazem
    //TODO inserir atributo de quantidade em estoque

    @Column( precision = 10, scale = 2 )
    private BigDecimal value;

    @Override
    public boolean equals( Object o ) {

        if( this == o ) return true;
        if( o == null || getClass() != o.getClass() ) return false;
        Product product = ( Product ) o;
        return Objects.equals( id, product.id ) && Objects.equals( name, product.name );
    }

    @Override
    public int hashCode() {

        return Objects.hash( id, name );
    }

}
