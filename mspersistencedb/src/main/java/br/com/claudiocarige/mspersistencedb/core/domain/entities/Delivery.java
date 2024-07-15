package br.com.claudiocarige.mspersistencedb.core.domain.entities;


import br.com.claudiocarige.mspersistencedb.core.domain.enums.DeliveryStatus;
import jakarta.persistence.*;
import lombok.*;

import java.util.Objects;


@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Delivery {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private Long id;

    @ManyToOne
    @JoinColumn( name = "sender_id" )
    private Customers sender;

    @ManyToOne
    @JoinColumn( name = "recipient_id" )
    private Customers recipient;

    @ManyToOne
    @JoinColumn( name = "product_id" )
    private Product product;

    @Enumerated( EnumType.STRING )
    private DeliveryStatus isDelivered;

    @Override
    public boolean equals( Object o ) {

        if( this == o ) return true;
        if( o == null || getClass() != o.getClass() ) return false;
        Delivery delivery = ( Delivery ) o;
        return Objects.equals( id, delivery.id );
    }

    @Override
    public int hashCode() {

        return Objects.hashCode( id );
    }

}
